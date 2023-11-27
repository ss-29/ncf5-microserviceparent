package dev.newbcoder_f5.ncf5productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NCf5ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NCf5ProductServiceApplication.class, args);
	}

}
