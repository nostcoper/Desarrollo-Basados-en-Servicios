package co.com.vanegas.microservice.resolveEnigmaApi.Service;

import co.com.vanegas.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.vanegas.microservice.resolveEnigmaApi.model.Header;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientGetEnigmaStepResponse;
import co.com.vanegas.microservice.resolveEnigmaApi.model.client.ClientJsonApiBodyResponseSuccess; // Importa esta clase

import java.util.Collections;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ResolveEnigmaService {

    private final StepOneService stepOneService;
    private final StepTwoService stepTwoService;
    private final StepThreeService stepThreeService;

    public ResolveEnigmaService(StepOneService stepOneService, StepTwoService stepTwoService, StepThreeService stepThreeService) {
        this.stepOneService = stepOneService;
        this.stepTwoService = stepTwoService;
        this.stepThreeService = stepThreeService;
    }

    public Mono<JsonApiBodyResponseSuccess> resolveEnigma(JsonApiBodyRequest request) {
        return Mono.zip(
                stepOneService.getStep().onErrorResume(e -> fallbackResponse("Step 1 failed", e)),
                stepTwoService.getStep().onErrorResume(e -> fallbackResponse("Step 2 failed", e)),
                stepThreeService.getStep().onErrorResume(e -> fallbackResponse("Step 3 failed", e))
        ).flatMap(tuple -> {

            var stepOneResponse = tuple.getT1();
            var stepTwoResponse = tuple.getT2();
            var stepThreeResponse = tuple.getT3();

            String id = stepOneResponse.getData() != null && !stepOneResponse.getData().isEmpty()
                    ? stepOneResponse.getData().get(0).getHeader().getId()
                    : "unknown";
            String type = stepOneResponse.getData() != null && !stepOneResponse.getData().isEmpty()
                    ? stepOneResponse.getData().get(0).getHeader().getType()
                    : "unknown";

            JsonApiBodyResponseSuccess response = new JsonApiBodyResponseSuccess();
            GetEnigmaStepResponse stepResponse = new GetEnigmaStepResponse();
            Header responseHeader = new Header();

            responseHeader.setId(id);
            responseHeader.setType(type);

            String answer = "Step 1: " + getStepDescription(stepOneResponse) +
                            " - Step 2: " + getStepDescription(stepTwoResponse) +
                            " - Step 3: " + getStepDescription(stepThreeResponse);

            stepResponse.setHeader(responseHeader);
            stepResponse.setAnswer(answer);
            response.addDataItem(stepResponse);

            return Mono.just(response);
        });
    }

    private String getStepDescription(ClientJsonApiBodyResponseSuccess stepResponse) {
        if (stepResponse.getData() != null && !stepResponse.getData().isEmpty()) {
            return stepResponse.getData().get(0).getStepDescription();
        } else {
            return "Error: Step data is empty";
        }
    }

    private Mono<ClientJsonApiBodyResponseSuccess> fallbackResponse(String errorMessage, Throwable e) {
        ClientJsonApiBodyResponseSuccess fallbackResponse = new ClientJsonApiBodyResponseSuccess();
        ClientGetEnigmaStepResponse errorStepResponse = new ClientGetEnigmaStepResponse();
        errorStepResponse.setStep("error");
        errorStepResponse.setStepDescription(errorMessage + " - " + e.getMessage());
        fallbackResponse.setData(Collections.singletonList(errorStepResponse));
        return Mono.just(fallbackResponse);
    }
}
