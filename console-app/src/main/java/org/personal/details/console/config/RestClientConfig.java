package org.personal.details.console.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@SuppressWarnings("unused")
public class RestClientConfig {

    @Value("${personal.details.username}")
    String username;

    @Value("${personal.details.password}")
    String password;

    private static final String BASE_URI = "http://localhost:8019";

    @Bean
    private RestClient restClient() {
        return RestClient.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(username, password))
                .baseUrl(BASE_URI)
                .build();
    }
}
