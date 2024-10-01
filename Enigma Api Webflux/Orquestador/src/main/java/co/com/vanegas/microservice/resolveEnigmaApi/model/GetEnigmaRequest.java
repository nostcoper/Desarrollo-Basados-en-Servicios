package co.com.vanegas.microservice.resolveEnigmaApi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Validated
public class GetEnigmaRequest {
  @JsonProperty("header")
  @Schema(required = true, description = "Header information for the request")
  @NotNull
  @Valid
  private Header header;

  @JsonProperty("enigma")
  @Schema(required = true, description = "The enigma or puzzle")
  @NotNull
  private String enigma;

  public GetEnigmaRequest() {}

  public GetEnigmaRequest(Header header, String enigma) {
    this.header = header;
    this.enigma = enigma;
  }

  public Header getHeader() {
    return header;
  }

  public void setHeader(Header header) {
    this.header = header;
  }

  public GetEnigmaRequest header(Header header) {
    this.header = header;
    return this;
  }

  public String getEnigma() {
    return enigma;
  }

  public void setEnigma(String enigma) {
    this.enigma = enigma;
  }

  public GetEnigmaRequest enigma(String enigma) {
    this.enigma = enigma;
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
    GetEnigmaRequest that = (GetEnigmaRequest) o;
    return Objects.equals(header, that.header) &&
        Objects.equals(enigma, that.enigma);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, enigma);
  }

  @Override
  public String toString() {
    return "GetEnigmaRequest{" +
           "header=" + header +
           ", enigma='" + enigma + '\'' +
           '}';
  }
}
