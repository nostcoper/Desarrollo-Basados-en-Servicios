package co.com.vanegas.microservice.resolveEnigmaApi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEnigmaRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T19:20:23.716-05:00[America/Bogota]")
public class GetEnigmaRequest {
  
  @JsonProperty("header")
  private Header header = null;

  @JsonProperty("step")
  private String step = null; // Agregado

  public GetEnigmaRequest header(Header header) {
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

  public GetEnigmaRequest step(String step) {
    this.step = step;
    return this;
  }

  /**
   * Get step
   * @return step
   **/
  @Schema(description = "The step information")
  public String getStep() {
    return step;
  }

  public void setStep(String step) {
    this.step = step;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetEnigmaRequest getEnigmaRequest = (GetEnigmaRequest) o;
    return Objects.equals(this.header, getEnigmaRequest.header) &&
           Objects.equals(this.step, getEnigmaRequest.step);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, step);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetEnigmaRequest {\n");
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    step: ").append(toIndentedString(step)).append("\n");
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
