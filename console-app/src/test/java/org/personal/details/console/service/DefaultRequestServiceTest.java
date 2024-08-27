package org.personal.details.console.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class DefaultRequestServiceTest {

    @Mock
    RestClient restClient;

    @InjectMocks
    DefaultRequestService requestService;


    @Test
    void isCreatedStatus_WithCreatedStatus_ReturnsTrue() {
        assertTrue(requestService.isCreatedStatus(HttpStatus.CREATED));
    }

    @Test
    void isCreatedStatus_WithNonCreatedStatus_ReturnsFalse() {
        assertFalse(requestService.isCreatedStatus(HttpStatus.NOT_ACCEPTABLE));
    }

    @Test
    void isUnprocessableEntityStatus_WithUnprocessableEntityStatusStatus_ReturnsTrue() {
        assertTrue(requestService.isUnprocessableEntityStatus(HttpStatus.UNPROCESSABLE_ENTITY));
    }

    @Test
    void isUnprocessableEntityStatus_WithNonUnprocessableEntityStatusStatus_ReturnsFalse() {
        assertFalse(requestService.isUnprocessableEntityStatus(HttpStatus.NOT_ACCEPTABLE));
    }

}
