package co.com.vanegas.microservice.resolveEnigmaApi.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;

import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientJsonApiBodyResponseSuccess;
import reactor.core.publisher.Mono;

@Service
public class StepThreeService {

    private final StepServiceHelper stepServiceHelper;

    public StepThreeService(@Qualifier("stepThreeWebClient") WebClient webClient) {
        this.stepServiceHelper = new StepServiceHelper(webClient, "stepTwoService");
    }

    public Mono<ClientJsonApiBodyResponseSuccess> getStep() {
        return stepServiceHelper.getStep(this::createRequestJson);
    }

    private String createRequestJson() {
        return "{ \"data\": [ { \"header\": { \"id\": \"67890\", \"type\": \"StepsGiraffeRefrigerator\" }, \"step\": \"3\" } ] }";
    }
}
