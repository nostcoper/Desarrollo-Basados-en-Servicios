package co.com.vanegas.microservice.resolveEnigmaApi.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import co.com.vanegas.microservice.resolveEnigmaApi.Model.EnigmaRequest;
import reactor.core.publisher.Mono;

@Service
public class BatchService {

    private static final Logger logger = LoggerFactory.getLogger(BatchService.class);
    private final WebClient webClient;

    public BatchService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://orquestador:8080").build(); // URL base del orquestador
    }

    @Scheduled(fixedRate = 5000)
    public void executeBatchProcess() {
        logger.info("Iniciando proceso batch...");
        callOrchestratorService()
                .doOnSuccess(response -> logger.info("Respuesta del orquestador: {}", response))
                .doOnError(error -> logger.error("Error llamando al orquestador: {}", error.getMessage()))
                .subscribe();
    }

    private Mono<String> callOrchestratorService() {
        EnigmaRequest requestBody = new EnigmaRequest();
        EnigmaRequest.EnigmaData enigmaData = new EnigmaRequest.EnigmaData();
        EnigmaRequest.Header header = new EnigmaRequest.Header();

        header.setId("12345");
        header.setType("TestGiraffeRefrigerator");
        enigmaData.setHeader(header);
        enigmaData.setEnigma("How to put a giraffe into a refrigerator?");

        requestBody.setData(List.of(enigmaData)); 

        return webClient.post()
                .uri("/v1/getOneEnigma/getStep")
                .bodyValue(requestBody) 
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(error -> {
                    logger.error("Error en la llamada al orquestador: {}", error.getMessage());
                    return Mono.just("Error");
                });
    }
}
