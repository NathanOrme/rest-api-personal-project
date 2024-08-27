package org.personal.details.console.service;

import org.personal.details.console.domain.PersonalDetailsDTO;
import org.personal.details.console.exception.CsvParsingException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Service for reading and parsing CSV files containing personal details.
 * This service reads a CSV file from the classpath, processes each line,
 * and converts it into {@link PersonalDetailsDTO} objects.
 */
@Service
public class DefaultCsvReaderService implements CsvReaderService {

    /**
     * Delimiter used in the CSV file to separate values.
     */
    public static final String COMMA_DELIMITER = ",";

    /**
     * Expected number of columns in the CSV file.
     */
    public static final int CSV_LENGTH = 8;

    /**
     * Path to the CSV file relative to the classpath.
     */
    private static final String CSV_FILENAME = "/details.csv";

    /**
     * Reads the contents of the CSV file and converts each line to a {@link PersonalDetailsDTO} object.
     *
     * @return A list of {@link PersonalDetailsDTO} objects parsed from the CSV file.
     * @throws Exception If the CSV file is not found or an error occurs during file processing.
     */
    public List<PersonalDetailsDTO> readContentsOfCSV() throws Exception {
        URL resource = DefaultCsvReaderService.class.getResource(CSV_FILENAME);
        if (resource == null) {
            throw new FileNotFoundException("CSV File not found");
        }
        Path csvFilePath = Paths.get(resource.toURI());
        try (Stream<String> lines = Files.lines(csvFilePath).skip(1)) {
            return lines.map(this::convertLineToPersonalDetailsObject)
                    .toList();
        }
    }

    /**
     * Converts a line from the CSV file to a {@link PersonalDetailsDTO} object.
     *
     * @param line A single line from the CSV file.
     * @return A {@link PersonalDetailsDTO} object representing the data in the line.
     * @throws CsvParsingException If the line does not have the expected number of columns.
     */
    private PersonalDetailsDTO convertLineToPersonalDetailsObject(final String line) {
        String[] contents = line.split(COMMA_DELIMITER);
        if (contents.length == CSV_LENGTH) {
            return new PersonalDetailsDTO(contents[0], contents[1], contents[2],
                    contents[3], contents[4], contents[5], contents[6], contents[7]);
        }
        throw new CsvParsingException(
                "Unexpected error occurred parsing CSV - Content length of string does not equal %s"
                        .formatted(CSV_LENGTH));
    }
}