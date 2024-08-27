package org.personal.details.console;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The entry point of the console application.
 * This class contains the main method to launch the Spring Boot application.
 */
@ComponentScan
@SpringBootApplication
@RequiredArgsConstructor
public class ConsoleApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(final String[] args) {
        SpringApplication.run(ConsoleApplication.class, args).close();
    }
}