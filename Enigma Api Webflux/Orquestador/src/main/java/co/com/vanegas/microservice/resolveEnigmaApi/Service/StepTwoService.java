package co.com.vanegas.microservice.resolveEnigmaApi.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientGetEnigmaStepResponse;
import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientJsonApiBodyResponseSuccess;

import reactor.core.publisher.Mono;

@Service
public class StepTwoService {

    private final WebClient webClient;

    @Autowired
    public StepTwoService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }


    public Mono<ClientJsonApiBodyResponseSuccess> getStep() {

        String requestJson = "{ \"data\": [ { \"header\": { \"id\": \"12345\", \"type\": \"StepsGiraffeRefrigerator\" }, \"step\": \"2\" } ] } }";

        return webClient.post()
            .uri("/v1/getOneEnigma/getStep")
            .header("Content-Type", "application/json")
            .bodyValue(requestJson) 
            .retrieve()
            .bodyToMono(ClientJsonApiBodyResponseSuccess.class)
            .flatMap(stepOneResponse -> {
                if (stepOneResponse != null && stepOneResponse.getData() != null 
                        && !stepOneResponse.getData().isEmpty() 
                        && "2".equalsIgnoreCase(stepOneResponse.getData().get(0).getStep())) {

                    ClientJsonApiBodyResponseSuccess response = new ClientJsonApiBodyResponseSuccess();
                    ClientGetEnigmaStepResponse stepResponse = new ClientGetEnigmaStepResponse();
                    stepResponse.setHeader(stepOneResponse.getData().get(0).getHeader());
                    stepResponse.setStep(stepOneResponse.getData().get(0).getStep());
                    stepResponse.setStepDescription(stepOneResponse.getData().get(0).getStepDescription());
                    response.addDataItem(stepResponse);
                    
                    return Mono.just(response);
                } else {
                    return Mono.error(new IllegalArgumentException("Step one is not valid or missing"));
                }
            })
            .onErrorResume(e -> {
                ClientJsonApiBodyResponseSuccess errorResponse = new ClientJsonApiBodyResponseSuccess();
                ClientGetEnigmaStepResponse errorStepResponse = new ClientGetEnigmaStepResponse();
                errorStepResponse.setStep("error");
                errorStepResponse.setStepDescription("Error: " + e.getMessage());
                errorResponse.addDataItem(errorStepResponse);
                return Mono.just(errorResponse);
            });
    }
}

