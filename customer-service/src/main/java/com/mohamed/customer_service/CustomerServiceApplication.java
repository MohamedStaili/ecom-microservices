package com.mohamed.customer_service;

import com.mohamed.customer_service.entities.Customer;
import com.mohamed.customer_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner init(CustomerRepository repository, CustomerRepository customerRepository) {
		return args -> {

			repository.saveAll(List.of(
					Customer.builder().name("Mohamed").email("mohamed@gmail.com").build(),
					Customer.builder().name("Hanane").email("hanane@gmail.com").build() ,
					Customer.builder().name("Fatime").email("fatima@gmail.com").build()
			));
			customerRepository.findAll().forEach(System.out::println);
		};

	}
}
