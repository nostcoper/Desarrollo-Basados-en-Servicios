package co.com.vanegas.microservice.resolveEnigmaApi.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientGetEnigmaStepResponse;
import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientHeader;
import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientJsonApiBodyResponseSuccess;

import reactor.core.publisher.Mono;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.reactor.retry.RetryOperator;

import java.time.Duration;
import java.util.Collections;
import java.util.function.Supplier;

public class StepServiceHelper {

    private final WebClient webClient;
    private final Logger logger = LoggerFactory.getLogger(StepServiceHelper.class);
    private final CircuitBreaker circuitBreaker;
    private final Retry retry;

    public StepServiceHelper(WebClient webClient, String serviceName) {
        this.webClient = webClient;

        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .permittedNumberOfCallsInHalfOpenState(3) 
                .minimumNumberOfCalls(5) 
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .automaticTransitionFromOpenToHalfOpenEnabled(true) 
                .build();

        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker(serviceName);

        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(5) 
                .waitDuration(Duration.ofSeconds(2))
                .retryOnResult(response -> response instanceof ClientJsonApiBodyResponseSuccess
                    && (((ClientJsonApiBodyResponseSuccess) response).getData() == null
                    || ((ClientJsonApiBodyResponseSuccess) response).getData().isEmpty()))
                .build();

        RetryRegistry retryRegistry = RetryRegistry.of(retryConfig);
        this.retry = retryRegistry.retry(serviceName);

        this.circuitBreaker.getEventPublisher()
                .onStateTransition(event -> logger.info("CircuitBreaker State Changed: {}", event.getStateTransition()));

        this.retry.getEventPublisher()
                .onRetry(event -> logger.info("Retry attempt {} for {}", event.getNumberOfRetryAttempts(), serviceName))
                .onError(event -> logger.error("Retry exhausted after {} attempts for {}", event.getNumberOfRetryAttempts(), serviceName));
    }

    public Mono<ClientJsonApiBodyResponseSuccess> getStep(Supplier<String> requestJsonSupplier) {
        logger.info("Attempting to get step...");

        return Mono.defer(() -> {
            if (circuitBreaker.getState() == CircuitBreaker.State.OPEN) {
                logger.warn("Circuit is OPEN. Using fallback.");
                return fallbackGetStep(new Exception("Circuit is OPEN"));
            }

            return webClient.post()
                    .uri("/v1/getOneEnigma/getStep")
                    .header("Content-Type", "application/json")
                    .bodyValue(requestJsonSupplier.get())
                    .retrieve()
                    .bodyToMono(ClientJsonApiBodyResponseSuccess.class)
                    .doOnNext(response -> {
                        if (response.getData() == null || response.getData().isEmpty()) {
                            throw new RuntimeException("Empty or null response data");
                        }
                    })
                    .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
                    .doOnError(e -> logger.error("Error in getStep: {}", e.getMessage()));
        })
        .transformDeferred(RetryOperator.of(retry))
        .onErrorResume(this::fallbackGetStep);
    }

    public Mono<ClientJsonApiBodyResponseSuccess> fallbackGetStep(Throwable e) {
        logger.warn("Fallback method called due to: {}", e.getMessage());

        ClientJsonApiBodyResponseSuccess fallbackResponse = new ClientJsonApiBodyResponseSuccess();
        ClientGetEnigmaStepResponse errorStepResponse = new ClientGetEnigmaStepResponse();
        ClientHeader fallbackHeader = new ClientHeader();
        fallbackHeader.setId("unknown");
        fallbackHeader.setType("error");

        errorStepResponse.setHeader(fallbackHeader);
        errorStepResponse.setStep("error");
        errorStepResponse.setStepDescription(e.getMessage());

        fallbackResponse.setData(Collections.singletonList(errorStepResponse));

        logger.info("Fallback response: {}", fallbackResponse);
        return Mono.just(fallbackResponse);
    }
}
