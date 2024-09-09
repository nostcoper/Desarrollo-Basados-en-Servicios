package co.com.vanegas.microservice.resolveEnigmaApi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEnigmaStepResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T19:20:23.716-05:00[America/Bogota]")
public class GetEnigmaStepResponse {
    @JsonProperty("header")
    private Header header = null;

    @JsonProperty("step")
    private String step = null;

    @JsonProperty("stepDescription")
    private String stepDescription = null; // Agregado

    public GetEnigmaStepResponse header(Header header) {
        this.header = header;
        return this;
    }

    /**
     * Get header
     * @return header
     **/
    @Schema(required = true, description = "")
    @NotNull
    @Valid
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public GetEnigmaStepResponse answer(String answer) {
        this.step = answer;
        return this;
    }

    /**
     * Get answer
     * @return answer
     **/
    @Schema(required = true, description = "")
    @NotNull
    public String getStep() {
        return step;
    }

    public void setStep(String answer) {
        this.step = answer;
    }

    public GetEnigmaStepResponse stepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
        return this;
    }

    /**
     * Get stepDescription
     * @return stepDescription
     **/
    @Schema(description = "Description of the step")
    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetEnigmaStepResponse getEnigmaStepResponse = (GetEnigmaStepResponse) o;
        return Objects.equals(this.header, getEnigmaStepResponse.header) &&
               Objects.equals(this.step, getEnigmaStepResponse.step) &&
               Objects.equals(this.stepDescription, getEnigmaStepResponse.stepDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, step, stepDescription);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetEnigmaStepResponse {\n");
        sb.append("    header: ").append(toIndentedString(header)).append("\n");
        sb.append("    answer: ").append(toIndentedString(step)).append("\n");
        sb.append("    stepDescription: ").append(toIndentedString(stepDescription)).append("\n"); // Agregado
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}