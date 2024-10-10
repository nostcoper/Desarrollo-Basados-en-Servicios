package co.com.vanegas.microservice.resolveEnigmaApi.api;

import co.com.vanegas.microservice.resolveEnigmaApi.Service.ResolveEnigmaService;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/getOneEnigma") 
public class GetStepApiController implements GetStepApi {

    private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);
    private final ResolveEnigmaService resolveEnigmaService;
    private final WebClient webClient;

    public GetStepApiController(ResolveEnigmaService resolveEnigmaService, WebClient.Builder webClientBuilder) {
        this.resolveEnigmaService = resolveEnigmaService; 
        this.webClient = webClientBuilder.baseUrl("http://webhook:8091").build();
    }

    @Override
    public Mono<ResponseEntity<JsonApiBodyResponseSuccess>> getStepsPost(@Valid @RequestBody JsonApiBodyRequest body) {
        return resolveEnigmaService.resolveEnigma(body)
            .doOnNext(response -> {
                log.info("Enigma resolved successfully: {}", response);
                callWebhook(response);
            })
            .doOnError(e -> {
                log.error("Error while resolving enigma: {}", e.getMessage());
            })
            .map(response -> ResponseEntity.ok(response))
            .onErrorResume(e -> {
                JsonApiBodyResponseSuccess errorResponse = new JsonApiBodyResponseSuccess();
                return Mono.just(ResponseEntity.status(424).body(errorResponse)); 
            });
    }

    private void callWebhook(Object message) {
        webClient.post()
            .uri("/webhook") 
            .bodyValue(message)
            .retrieve()
            .bodyToMono(String.class)
            .doOnSuccess(response -> log.info("Webhook llamado exitosamente: {}", response))
            .doOnError(error -> log.error("Error llamando al webhook: {}", error.getMessage()))
            .subscribe();
    }
}
