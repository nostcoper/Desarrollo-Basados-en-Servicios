package co.com.vanegas.microservice.resolveEnigmaApi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T19:20:23.716-05:00[America/Bogota]")
public class JsonApiBodyRequest {
  @JsonProperty("data")
  @Schema(required = true, description = "List of enigma requests")
  @NotNull
  @Valid
  private List<GetEnigmaRequest> data = new ArrayList<>();

  public JsonApiBodyRequest() {}

  public JsonApiBodyRequest(List<GetEnigmaRequest> data) {
    this.data = data;
  }

  public List<GetEnigmaRequest> getData() {
    return data;
  }

  public void setData(List<GetEnigmaRequest> data) {
    this.data = data;
  }

  public JsonApiBodyRequest data(List<GetEnigmaRequest> data) {
    this.data = data;
    return this;
  }

  public JsonApiBodyRequest addDataItem(GetEnigmaRequest dataItem) {
    this.data.add(dataItem);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonApiBodyRequest that = (JsonApiBodyRequest) o;
    return Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    return "JsonApiBodyRequest{" +
           "data=" + data +
           '}';
  }
}
