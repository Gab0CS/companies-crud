package com.gabo.companies_crud.configs;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Companies CRUD",
        version = "1.0.0",
        description = "Crud for company management"
    )
)
public class OpenApiConfig {
    
}
