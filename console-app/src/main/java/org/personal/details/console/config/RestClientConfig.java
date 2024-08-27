package org.personal.details.console.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Configuration class for setting up the {@link RestClient}.
 * This class provides a Spring Bean that configures a
 * RestClient with authentication and base URL.
 */
@Component
@SuppressWarnings("unused")
public class RestClientConfig {

    @Value("${personal.details.username}")
    private String username;

    @Value("${personal.details.password}")
    private String password;

    private static final String BASE_URI = "http://localhost:8019";

    /**
     * Configures and provides a {@link RestClient} bean with default headers and base URL.
     *
     * @return A {@link RestClient} instance configured with basic authentication and base URL.
     */
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(username, password))
                .baseUrl(BASE_URI)
                .build();
    }
}