package co.com.vanegas.microservice.resolveEnigmaApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import co.com.vanegas.microservice.resolveEnigmaApi.Service.BatchService;

@SpringBootApplication
@EnableScheduling
public class BatchApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BatchApplication.class, args);
    }


    @Bean
    CommandLineRunner runBatch(BatchService batchService) {
        return args -> {
            batchService.executeBatchProcess();
        };
    }
}
