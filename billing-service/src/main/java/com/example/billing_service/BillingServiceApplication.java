package com.example.billing_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.vault.core.VaultTemplate;

import java.util.Map;

@SpringBootApplication
public class BillingServiceApplication {
	private VaultTemplate vaultTemplate;

    public BillingServiceApplication(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			vaultTemplate.opsForVersionedKeyValue("secret")
					.put("mySecret", Map.of("p1","654321"));
		};

	}
}
