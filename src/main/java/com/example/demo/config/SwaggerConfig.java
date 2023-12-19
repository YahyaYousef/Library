package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI reachCustomerAppOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(getSecurityRequirement())
                .components(getComponent())
                .info(new Info().title("Demo Application API")
                        .description("Demo Application API")
                        .version("v1.0.0"));
    }

    private Components getComponent() {
        return new Components().addSecuritySchemes
                ("Bearer Authentication", createAPIKeyScheme());
    }

    private SecurityRequirement getSecurityRequirement() {
        return new SecurityRequirement().
                addList("Bearer Authentication");
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
