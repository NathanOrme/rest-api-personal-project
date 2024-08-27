package org.personal.details.console.service;

import org.personal.details.console.domain.PersonalDetailsDTO;

import java.util.List;

/**
 * Interface for reading the contents of a CSV file and converting them into a list of {@link PersonalDetailsDTO} objects.
 */
public interface CsvReaderService {

    /**
     * Reads the contents of a CSV file and converts each line into a {@link PersonalDetailsDTO} object.
     *
     * @return A list of {@link PersonalDetailsDTO} objects read from the CSV file.
     * @throws Exception If an error occurs while reading or parsing the CSV file.
     */
    List<PersonalDetailsDTO> readContentsOfCSV() throws Exception;
}