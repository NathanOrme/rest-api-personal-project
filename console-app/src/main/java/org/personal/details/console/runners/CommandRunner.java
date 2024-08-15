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

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {

    private final CsvReaderService csvReaderService;

    private final RequestService requestService;

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
