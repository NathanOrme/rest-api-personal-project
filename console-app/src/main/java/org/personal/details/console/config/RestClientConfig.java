package org.personal.details.console.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
@SuppressWarnings("unused")
public class RestClientConfig {

    @Value("${personal.details.baseUrl}")
    private String baseUrl;

    @Value("${personal.details.username}")
    String username;

    @Value("${personal.details.password}")
    String password;

    @Bean
    private RestClient restClient(final RestClientSsl ssl) {

        return RestClient
                .builder()
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(username, password))
                .apply(ssl.fromBundle("springboot"))
                .baseUrl(baseUrl)
                .build();
    }
}
