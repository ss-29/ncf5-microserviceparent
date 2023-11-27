package dev.newbcoder_f5.ncf5productservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.newbcoder_f5.ncf5productservice.dto.ProductRequest;
import dev.newbcoder_f5.ncf5productservice.repository.ProductRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class NCf5ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	/*
	 * in this way at the time of starting the integration test, the test will start the mongodb
	 * container by downloading the mongodb latest container image and after starting the container
	 * it will get the replica set URL and add it to the spring.data.mongodb.uri dynamically
	 * at the time of creating the test
	*/
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	@Order(1)
	void shouldCreateProducts() throws Exception {
		ProductRequest productRequest = getProductRequest("iPhone 13");
		String productRequestStr = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestStr)
				)
				.andExpect(MockMvcResultMatchers.status().isCreated());

		productRequest = getProductRequest("Galaxy S23");
		productRequestStr = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestStr)
				)
				.andExpect(MockMvcResultMatchers.status().isCreated());
		
		Assertions.assertEquals(2, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest(String product) {
		if (product.equalsIgnoreCase("iPhone 13")) {
			return ProductRequest.builder()
					.name("iPhone 13")
					.description("The iPhone 13 is Apple's latest smartphone, featuring a powerful A15 Bionic chip, stunning Super Retina XDR display, and advanced camera capabilities for capturing high-quality photos and videos. It offers impressive performance and a sleek design, making it a top choice for mobile enthusiasts.")
					.price(BigDecimal.valueOf(51999))
					.build();
		}
		else if (product.equalsIgnoreCase("Galaxy S23")) {
			return ProductRequest.builder()
					.name("Galaxy S23")
					.description("The Samsung Galaxy S23 is a cutting-edge smartphone with a powerful Exynos or Snapdragon chip (model-dependent) and a vibrant AMOLED display, offering a premium Android experience. Its advanced camera system, 5G connectivity, and sleek design make it a top-tier choice for those seeking a feature-packed device.")
					.price(BigDecimal.valueOf(79999))
					.build();
		}
		else return null;
	}

}
