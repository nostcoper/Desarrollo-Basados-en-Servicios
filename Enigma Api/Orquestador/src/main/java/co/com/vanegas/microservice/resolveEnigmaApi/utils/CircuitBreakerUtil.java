package co.com.vanegas.microservice.resolveEnigmaApi.utils;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CircuitBreakerUtil {

    private final CircuitBreaker circuitBreaker;

    public CircuitBreakerUtil() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(30)) // Aumento del tiempo en estado abierto
            .permittedNumberOfCallsInHalfOpenState(10) // Más llamadas permitidas en estado semi-abierto
            .slidingWindowSize(10)
            .build();
        
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        this.circuitBreaker = registry.circuitBreaker("stepOneCircuitBreaker");
    }

    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }
}

