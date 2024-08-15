package org.personal.details.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.personal.details.model.PersonalDetailsDTO;
import org.personal.details.service.PersonalDetailsService;
import org.personal.details.utils.TestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonalDetailsControllerTest {

    @Mock
    private PersonalDetailsService personalDetailsService;

    @InjectMocks
    private PersonalDetailsController personalDetailsController;

    @Test
    void savePersonalDetails_WithValidDTO_ReturnsCreated() {
        PersonalDetailsDTO personalDetailsDTO = TestUtils.generatePersonDetailsDTO();

        ResponseEntity<Void> responseEntity = personalDetailsController.savePersonalDetails(personalDetailsDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void getCustomer_WithValidReference_ReturnsDTO() {
        PersonalDetailsDTO personalDetailsDTO = TestUtils.generatePersonDetailsDTO();

        when(personalDetailsService.getCustomerFromRef(personalDetailsDTO.getCustomerRef())).thenReturn(personalDetailsDTO);

        ResponseEntity<PersonalDetailsDTO> responseEntity = personalDetailsController.getCustomerFromReference(personalDetailsDTO.getCustomerRef());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(personalDetailsDTO, responseEntity.getBody());
    }

}
