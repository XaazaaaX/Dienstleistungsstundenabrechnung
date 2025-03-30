package de.dlsa.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Klasse zur Konfiguration der Swagger UI
 *
 * @author Benito Ernst
 * @version  01/2024
 */
@Configuration
public class SwaggerConfig {

    /**
     * Erstellt und konfiguriert die API Dokumentation
     *
     * @return Rückgabe der Doku im OpenApi Schema für die UI Generierung
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("Dlsa Game - Rest API")
                        .description("Some custom description of API.")
                        .version("1.0"));
    }

    /**
     * Festlegung des SecurityScheme für die Doku
     *
     * @return Rückgabe des SecurityScheme
     */
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .name("security_auth")
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
