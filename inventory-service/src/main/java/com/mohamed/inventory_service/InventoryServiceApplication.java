package com.mohamed.inventory_service;

import com.mohamed.inventory_service.entities.Product;
import com.mohamed.inventory_service.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository repository) {
		return args -> {
			Random random = new Random();
			for (int i = 0; i < 10; i++) {
				repository.save(Product.builder().name("Computer" + i).price(1200+Math.random()*10000).quantity(random.nextInt(20)).build());
			}

		};
	}
}
