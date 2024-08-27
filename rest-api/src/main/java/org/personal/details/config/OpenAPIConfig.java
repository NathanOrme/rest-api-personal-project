package org.personal.details.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up OpenAPI documentation for the Personal Details REST API.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Configures and returns an OpenAPI instance with metadata for the Personal Details API.
     * This metadata includes the API's title, description, and version.
     *
     * @return OpenAPI instance with API metadata.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Personal Details Rest API")
                .description("REST API to store some personal details")
                .version("v0.0.1"));
    }
}