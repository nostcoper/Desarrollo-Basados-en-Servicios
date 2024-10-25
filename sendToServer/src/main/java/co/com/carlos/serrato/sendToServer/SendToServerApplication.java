package co.com.carlos.serrato.sendToServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SendToServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendToServerApplication.class, args);
	}

}
