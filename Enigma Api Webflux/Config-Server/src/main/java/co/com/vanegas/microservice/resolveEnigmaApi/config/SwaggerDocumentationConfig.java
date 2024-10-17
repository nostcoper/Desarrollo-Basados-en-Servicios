package co.com.vanegas.microservice.resolveEnigmaApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T19:20:23.716-05:00[America/Bogota]")
@Configuration
class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI customImplementation() {
        return new OpenAPI()
            .info(new Info()
                .title("Get One Enigma Step API")
                .description("API for get one step for resolve the enigma how put a giraffe into a refrigerator")
                .version("1.0.0")
                .termsOfService("")
                .contact(new Contact()
                    .name("")
                    .email("afortega@bancolombia.com"))
                .license(new io.swagger.v3.oas.models.info.License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}