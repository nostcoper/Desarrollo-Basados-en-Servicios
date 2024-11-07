package com.co.serrato.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroServicioUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicioUsuarioApplication.class, args);
	}

}
