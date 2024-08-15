package org.personal.details.console.service;

import org.junit.jupiter.api.Test;
import org.personal.details.console.model.PersonalDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvReaderServiceTest {

    private final org.personal.details.console.service.CsvReaderService csvReaderService = new org.personal.details.console.service.CsvReaderService();

    @Test
    void readContentsOfCSV_WithPopulatedCSV_ReturnsPersonalDetails() {
        List<PersonalDetails> personalDetailsList = assertDoesNotThrow(csvReaderService::readContentsOfCSV);
        assertNotNull(personalDetailsList);
        assertFalse(personalDetailsList.isEmpty());
    }

}
