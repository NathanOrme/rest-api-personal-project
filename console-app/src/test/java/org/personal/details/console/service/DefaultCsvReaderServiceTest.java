package org.personal.details.console.service;

import org.junit.jupiter.api.Test;
import org.personal.details.common.model.PersonalDetailsDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultCsvReaderServiceTest {

    private final DefaultCsvReaderService csvReaderService = new DefaultCsvReaderService();

    @Test
    void readContentsOfCSV_WithPopulatedCSV_ReturnsPersonalDetails() {
        List<PersonalDetailsDTO> personalDetailsDTOList = assertDoesNotThrow(csvReaderService::readContentsOfCSV);
        assertNotNull(personalDetailsDTOList);
        assertFalse(personalDetailsDTOList.isEmpty());
    }

}
