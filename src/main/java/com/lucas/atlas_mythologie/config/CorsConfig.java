package com.lucas.atlas_mythologie.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Autorise uniquement les API qui commencent par /api/
                .allowedOrigins("http://localhost:5173")  // Permet les requêtes provenant du front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Méthodes HTTP autorisées
                .allowedHeaders("*")  // Autorise tous les en-têtes
                .allowCredentials(true);  // Autorise l'envoi de cookies/sessions
    }
}