package co.com.vanegas.microservice.resolveEnigmaApi.api;

import co.com.vanegas.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GetStepApiController implements GetStepApi {

    private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);

    private final HttpServletRequest request;

    public GetStepApiController(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<JsonApiBodyResponseSuccess> getStep(
        @Parameter(description = "request body get enigma step", required = true)
        @Valid @RequestBody JsonApiBodyRequest body) {

        if (body == null || body.getData() == null || body.getData().isEmpty()) {
            log.error("Invalid request body: {}", body);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("Received request body: {}", body);

        JsonApiBodyResponseSuccess response = new JsonApiBodyResponseSuccess();
        try {
            // Procesar la solicitud basándose en el contenido de `body`
            List<GetEnigmaRequest> data = body.getData();
            GetEnigmaRequest firstItem = data.get(0);

            GetEnigmaStepResponse pasoResponse = new GetEnigmaStepResponse();
            pasoResponse.setStep(firstItem.getStep());
            pasoResponse.setHeader(firstItem.getHeader());
            pasoResponse.setStepDescription("Close de door");
            response.addDataItem(pasoResponse);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error processing request: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
