package org.personal.details.console.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.personal.details.domain.PersonalDetails;
import org.personal.details.exceptions.CustomerDoesNotExistException;
import org.personal.details.exceptions.CustomerRefExistsException;
import org.personal.details.mapper.PersonalDetailsMapper;
import org.personal.details.model.PersonalDetailsDTO;
import org.personal.details.repository.PersonalDetailsRepository;
import org.personal.details.service.PersonalDetailsService;
import org.personal.details.utils.TestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonalDetailsServiceTest {

    @Mock
    private PersonalDetailsRepository personalDetailsRepository;

    @Mock
    private PersonalDetailsMapper personalDetailsMapper;

    @InjectMocks
    private PersonalDetailsService personalDetailsService;

    @Test
    void saveDetails_WithExistingCustomerRef_ThrowsException() {
        PersonalDetailsDTO personalDetailsDTO = TestUtils.generatePersonDetailsDTO();
        PersonalDetails personalDetails = TestUtils.generatePersonDetails();
        when(personalDetailsRepository.findOptionalPersonalDetailsByCustomerRef(personalDetailsDTO.getCustomerRef())).thenReturn(Optional.ofNullable(personalDetails));

        CustomerRefExistsException exception = assertThrows(CustomerRefExistsException.class, () -> personalDetailsService.saveDetails(personalDetailsDTO));
        assertEquals("The record for customer ref %s could not be processed".formatted(personalDetailsDTO.getCustomerRef()), exception.getMessage());
    }


    @Test
    void saveDetails_WithNewCustomerRef_DoesNotThrowException() {
        PersonalDetailsDTO personalDetailsDTO = TestUtils.generatePersonDetailsDTO();
        when(personalDetailsRepository.findOptionalPersonalDetailsByCustomerRef(personalDetailsDTO.getCustomerRef())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> personalDetailsService.saveDetails(personalDetailsDTO));
    }

    @Test
    void getCustomerFromRef_WithNewCustomerRef_ThrowsException() {
        PersonalDetailsDTO personalDetailsDTO = TestUtils.generatePersonDetailsDTO();
        when(personalDetailsRepository.findOptionalPersonalDetailsByCustomerRef(personalDetailsDTO.getCustomerRef())).thenReturn(Optional.empty());

        CustomerDoesNotExistException exception = assertThrows(CustomerDoesNotExistException.class, () -> personalDetailsService.getCustomerFromRef(personalDetailsDTO.getCustomerRef()));
        assertEquals("There is no matching customer for reference %s".formatted(personalDetailsDTO.getCustomerRef()), exception.getMessage());
    }

    @Test
    void getCustomerFromRef_WithExistingCustomerRef_DoesNotThrowException() {
        PersonalDetailsDTO personalDetailsDTO = TestUtils.generatePersonDetailsDTO();
        when(personalDetailsRepository.findOptionalPersonalDetailsByCustomerRef(personalDetailsDTO.getCustomerRef()))
                .thenReturn(Optional.of(TestUtils.generatePersonDetails()));

        assertDoesNotThrow(() -> personalDetailsService.getCustomerFromRef(personalDetailsDTO.getCustomerRef()));
    }

}
