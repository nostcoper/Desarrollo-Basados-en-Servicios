/**
 * NOTE: This class is auto-generated by the swagger code generator program (3.0.4).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package co.com.vanegas.microservice.resolveEnigmaApi.api;

import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * API interface for resolving enigma steps.
 */
@Tag(name = "Enigma Step API", description = "API for retrieving and resolving enigma steps")
public interface GetStepApi {

    /**z
     * Get one enigma step based on provided criteria.
     * 
     * @param body the request body containing the criteria for fetching the enigma step
     * @return a list of successful response objects or an error response
     */
    @Operation(
        summary = "Retrieve Enigma Step",
        description = "Fetches a specific enigma step based on the provided request body.",
        responses = {
            @ApiResponse(responseCode = "200", 
                         description = "Successfully retrieved the enigma step.",
                         content = @Content(mediaType = "application/json", 
                                            schema = @Schema(implementation = JsonApiBodyResponseSuccess.class))),
            @ApiResponse(responseCode = "424", 
                         description = "Failed to retrieve enigma step due to bad input parameters.",
                         content = @Content(mediaType = "application/json",
                         					schema = @Schema(implementation = JsonApiBodyResponseSuccess.class))),
        }
    )
    @PostMapping(
        value = "/getStep",
        produces = "application/json",
        consumes = "application/json")
    	ResponseEntity<JsonApiBodyResponseSuccess> getStep(
        @Parameter(description = "Request body containing the criteria to fetch the enigma step", required = true)  
        @Valid @RequestBody JsonApiBodyRequest body
    );
}
