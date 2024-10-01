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

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/getOneEnigma") 
public class GetStepApiController implements GetStepApi {

    private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);

    private final ResolveEnigmaService resolveEnigmaService;

    public GetStepApiController(ResolveEnigmaService resolveEnigmaService) {
        this.resolveEnigmaService = resolveEnigmaService; 
    }

    @Override
    public Mono<ResponseEntity<JsonApiBodyResponseSuccess>> getStepsPost(@Valid @RequestBody JsonApiBodyRequest body) {
        return resolveEnigmaService.resolveEnigma(body)
            .doOnNext(response -> {
                log.info("Enigma resolved successfully: {}", response);
            })
            .doOnError(e -> {
                // Manejo del error
                log.error("Error while resolving enigma: {}", e.getMessage());
            })
            .map(response -> ResponseEntity.ok(response))
            .onErrorResume(e -> {
                JsonApiBodyResponseSuccess errorResponse = new JsonApiBodyResponseSuccess();
                return Mono.just(ResponseEntity.status(424).body(errorResponse)); 
            });
    }
}
