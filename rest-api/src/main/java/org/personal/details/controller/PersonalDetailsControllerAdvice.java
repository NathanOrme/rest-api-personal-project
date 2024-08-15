package org.personal.details.controller;

import lombok.extern.slf4j.Slf4j;
import org.personal.details.exceptions.CustomerDoesNotExistException;
import org.personal.details.exceptions.CustomerRefExistsException;
import org.personal.details.exceptions.EncryptionException;
import org.personal.details.model.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@SuppressWarnings("unused")
public class PersonalDetailsControllerAdvice {

    private static ResponseEntity<ErrorMessageDTO> generateErrorMessage(final HttpStatus httpStatus, final String errorMessage) {
        log.error(errorMessage);
        ErrorMessageDTO errorMessageDTO = ErrorMessageDTO.builder()
                .errorMessage(errorMessage)
                .build();

        return ResponseEntity.status(httpStatus).body(errorMessageDTO);
    }

    @ExceptionHandler(value = {CustomerRefExistsException.class})
    protected ResponseEntity<ErrorMessageDTO> handleCustomerExistsException(final CustomerRefExistsException exception) {
        return generateErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());

    }

    @ExceptionHandler(value = {CustomerDoesNotExistException.class})
    protected ResponseEntity<ErrorMessageDTO> handleCustomerDoesNotExistException(final CustomerDoesNotExistException exception) {
        return generateErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

    }

    @ExceptionHandler(value = {EncryptionException.class})
    protected ResponseEntity<ErrorMessageDTO> handleEncryptionException(final EncryptionException exception) {
        return generateErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

}
