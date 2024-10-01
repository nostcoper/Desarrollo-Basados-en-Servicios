package co.com.vanegas.microservice.resolveEnigmaApi.Service;

import co.com.vanegas.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.vanegas.microservice.resolveEnigmaApi.model.Header;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ResolveEnigmaService {
	
	private final WebClient webClient; 
    private final StepOneService stepOneService;
    private final StepTwoService stepTwoService;
    private final StepThreeService stepThreeService;

    public ResolveEnigmaService(WebClient.Builder webClientBuilder, StepOneService stepOneService,  StepTwoService stepTwoService, StepThreeService stepThreeService) {
    	this.webClient = webClientBuilder.build();
		this.stepOneService = stepOneService;
		this.stepTwoService = stepTwoService;
		this.stepThreeService = stepThreeService;
    }

    public Mono<JsonApiBodyResponseSuccess> resolveEnigma(JsonApiBodyRequest request) {
        return Mono.zip(
                stepOneService.getStep(),
                stepTwoService.getStep(),
                stepThreeService.getStep()
        ).flatMap(tuple -> {

            var stepOneResponse = tuple.getT1();
            var stepTwoResponse = tuple.getT2();
            var stepThreeResponse = tuple.getT3();

            String id = stepOneResponse.getData().get(0).getHeader().getId();
            String type = stepOneResponse.getData().get(0).getHeader().getType();

            JsonApiBodyResponseSuccess response = new JsonApiBodyResponseSuccess();
            GetEnigmaStepResponse stepResponse = new GetEnigmaStepResponse();
            Header responseHeader = new Header();

            responseHeader.setId(id);
            responseHeader.setType(type);

            String answer = "Step 1: " + stepOneResponse.getData().get(0).getStepDescription() +
                            " - Step 2: " + stepTwoResponse.getData().get(0).getStepDescription() +
                            " - Step 3: " + stepThreeResponse.getData().get(0).getStepDescription();

            stepResponse.setHeader(responseHeader);
            stepResponse.setAnswer(answer);
            response.addDataItem(stepResponse);

            return Mono.just(response);
        })
        .onErrorResume(e -> {
            JsonApiBodyResponseSuccess errorResponse = new JsonApiBodyResponseSuccess();
            return Mono.just(errorResponse);
        });
    }

}
