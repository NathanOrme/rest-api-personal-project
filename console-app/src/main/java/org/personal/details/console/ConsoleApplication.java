package org.personal.details.console;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
@RequiredArgsConstructor
public class ConsoleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ConsoleApplication.class, args).close();

    }


}
