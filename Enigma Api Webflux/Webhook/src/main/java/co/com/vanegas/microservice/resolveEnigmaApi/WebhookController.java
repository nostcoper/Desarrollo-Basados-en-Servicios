package co.com.vanegas.microservice.resolveEnigmaApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @PostMapping("/webhook")
    public HttpStatus handleWebhook(@RequestBody String message) {
        logger.info("Recibido el mensaje del orquestador: {}", message);
        return HttpStatus.OK; 
    }
}
