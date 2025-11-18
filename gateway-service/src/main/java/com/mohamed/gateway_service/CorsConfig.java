package com.mohamed.gateway_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // 1. Autoriser l'origine (Frontend)
        // Utilisez setAllowedOriginPatterns("*") pour autoriser tout en gardant les credentials
        corsConfig.setAllowedOriginPatterns(Arrays.asList("*"));
        // Pour plus de sécurité, remplacez "*" par "http://localhost:4200" ou "http://localhost:3000"

        // 2. Méthodes autorisées
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // 3. Headers autorisés
        corsConfig.addAllowedHeader("*");

        // 4. Autoriser les cookies/tokens
        corsConfig.setAllowCredentials(true);

        // 5. Appliquer cette config à toutes les routes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}