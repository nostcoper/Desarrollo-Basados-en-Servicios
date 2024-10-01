package co.com.vanegas.microservice.resolveEnigmaApi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.*;

@Validated
public class Header {
  @JsonProperty("id")
  @Schema(required = true, description = "Unique identifier for the header")
  @NotNull
  private String id;

  @JsonProperty("type")
  @Schema(required = true, description = "Type of the header")
  @NotNull
  private String type;

  // Constructor sin parámetros para deserialización
  public Header() {}

  public Header(String id, String type) {
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Header id(String id) {
    this.id = id;
    return this;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Header type(String type) {
    this.type = type;
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
    Header header = (Header) o;
    return Objects.equals(id, header.id) &&
        Objects.equals(type, header.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "Header{" +
           "id='" + id + '\'' +
           ", type='" + type + '\'' +
           '}';
  }
}
