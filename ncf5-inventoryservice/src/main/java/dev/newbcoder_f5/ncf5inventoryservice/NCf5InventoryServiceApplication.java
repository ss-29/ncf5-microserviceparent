package dev.newbcoder_f5.ncf5inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import dev.newbcoder_f5.ncf5inventoryservice.model.Inventory;
import dev.newbcoder_f5.ncf5inventoryservice.repository.InventoryRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class NCf5InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NCf5InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {

		return args -> {
			Inventory inventory01Uno = new Inventory();
			inventory01Uno.setSkuCode("iPhone 15 Blue");
			inventory01Uno.setQuantity(100);

			Inventory inventory02Deux = new Inventory();
			inventory02Deux.setSkuCode("iPhone 15 Pink");
			inventory02Deux.setQuantity(0);

			inventoryRepository.save(inventory01Uno);
			inventoryRepository.save(inventory02Deux);
		};

	}

}
