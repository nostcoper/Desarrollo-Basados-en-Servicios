package co.com.vanegas.microservice.resolveEnigmaApi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerDocumentationConfig {

    @Value("${service.step-one.url}")
    private String stepOneServiceUrl;

    @Value("${service.step-two.url}")
    private String stepTwoServiceUrl;

    @Value("${service.step-three.url}")
    private String stepThreeServiceUrl;

    @Bean
    OpenAPI customImplementation() {
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

    @Bean(name = "stepOneWebClient")
    WebClient stepOneWebClient() {
        return WebClient.builder()
            .baseUrl(stepOneServiceUrl)
            .build();
    }

    @Bean(name = "stepTwoWebClient")
    WebClient stepTwoWebClient() {
        return WebClient.builder()
            .baseUrl(stepTwoServiceUrl)
            .build();
    }

    @Bean(name = "stepThreeWebClient")
    WebClient stepThreeWebClient() {
        return WebClient.builder()
            .baseUrl(stepThreeServiceUrl)
            .build();
    }
    
    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}