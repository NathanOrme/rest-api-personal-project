package org.personal.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.security.NoSuchAlgorithmException;

@ComponentScan
@SpringBootApplication
public class PersonalDetailsApplication {

    public static void main(final String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(PersonalDetailsApplication.class, args);
    }
}
