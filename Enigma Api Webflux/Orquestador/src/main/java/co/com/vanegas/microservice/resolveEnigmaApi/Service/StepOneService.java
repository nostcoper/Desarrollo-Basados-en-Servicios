package co.com.vanegas.microservice.resolveEnigmaApi.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientJsonApiBodyResponseSuccess;
import reactor.core.publisher.Mono;

@Service
public class StepOneService {

    private final StepServiceHelper stepServiceHelper;

    public StepOneService(@Qualifier("stepOneWebClient") WebClient webClient) {
        this.stepServiceHelper = new StepServiceHelper(webClient, "stepOneService");
    }

    public Mono<ClientJsonApiBodyResponseSuccess> getStep() {
        return stepServiceHelper.getStep(this::createRequestJson);
    }

    private String createRequestJson() {
        return "{ \"data\": [ { \"header\": { \"id\": \"12345\", \"type\": \"StepsGiraffeRefrigerator\" }, \"step\": \"1\" } ] }";
    }
}
