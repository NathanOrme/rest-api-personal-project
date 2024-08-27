package org.personal.details.console.runners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.personal.details.console.exception.ConsoleAppException;
import org.personal.details.console.model.PersonalDetails;
import org.personal.details.console.service.CsvReaderService;
import org.personal.details.console.service.RequestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * CommandLineRunner implementation for executing tasks on application startup.
 * This runner reads personal details from a CSV file, sends each record to a remote API,
 * and logs the results.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {

    private final CsvReaderService csvReaderService;

    private final RequestService requestService;

    /**
     * Executes the tasks when the application starts.
     * This method reads personal details from a CSV file, attempts to send each record to a remote API,
     * and logs the results. Records that fail to be saved are collected and reported.
     *
     * @param args Command line arguments (not used in this implementation).
     * @throws ConsoleAppException If any error occurs during the execution of tasks.
     */
    @Override
    public void run(final String... args) {
        try {
            log.info("Reading src/main/resource/details.csv");
            List<PersonalDetails> personalDetails = csvReaderService.readContentsOfCSV();
            List<String> failedRefs = new ArrayList<>();
            log.info("Contents read, sending request");
            for (final PersonalDetails personalDetail : personalDetails) {
                if (!requestService.sendDetailsToEndpoint(personalDetail)) {
                    failedRefs.add(personalDetail.getCustomerRef());
                }
            }
            log.info("All entries processed");
            if (!failedRefs.isEmpty()) {
                log.error("The following contact refs and records could not be saved: {}", failedRefs);
            }
        } catch (final Exception e) {
            throw new ConsoleAppException(e);
        }
    }
}