package org.personal.details.console.service;

import org.personal.details.console.exception.CsvParsingException;
import org.personal.details.console.model.PersonalDetails;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CsvReaderService {

    public static final String COMMA_DELIMITER = ",";
    public static final int CSV_LENGTH = 8;
    private static final String CSV_FILENAME = "/details.csv";

    public List<PersonalDetails> readContentsOfCSV() throws Exception {
        URL resource = CsvReaderService.class.getResource(CSV_FILENAME);
        if (resource == null) {
            throw new FileNotFoundException("CSV File not found");
        }
        Path csvFilePath = Paths.get(resource.toURI());
        try (Stream<String> lines = Files.lines(csvFilePath).skip(1)) {
            return lines.map(this::convertLineToPersonalDetailsObject)
                    .toList();
        }

    }

    private PersonalDetails convertLineToPersonalDetailsObject(final String line) {
        String[] contents = line.split(COMMA_DELIMITER);
        if (contents.length == CSV_LENGTH) {
            return new PersonalDetails(contents[0], contents[1], contents[2], contents[3], contents[4], contents[5], contents[6], contents[7]);
        }
        throw new CsvParsingException("Unexpected error occurred parsing CSV - Content length of string does not equal %s".formatted(CSV_LENGTH));
    }
}
