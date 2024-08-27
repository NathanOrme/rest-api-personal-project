package org.personal.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.security.NoSuchAlgorithmException;

/**
 * Main class for the Personal Details application.
 * This class serves as the entry point for the Spring Boot application.
 */
@ComponentScan
@SpringBootApplication
public class PersonalDetailsApplication {

    /**
     * Main method to run the Personal Details application.
     * This method starts the Spring Boot application.
     *
     * @param args Command-line arguments.
     * @throws NoSuchAlgorithmException If a required cryptographic algorithm is not available.
     */
    public static void main(final String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(PersonalDetailsApplication.class, args);
    }
}