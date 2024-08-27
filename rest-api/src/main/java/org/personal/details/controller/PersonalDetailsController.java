package org.personal.details.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.personal.details.model.PersonalDetailsDTO;
import org.personal.details.service.PersonalDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping("personal-details")
public class PersonalDetailsController {

    private final PersonalDetailsService personalDetailsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> savePersonalDetails(@RequestBody final PersonalDetailsDTO personalDetailsDTO) {
        log.info("Received request for customer ref {}", personalDetailsDTO.customerRef());
        personalDetailsService.saveDetails(personalDetailsDTO);
        log.info("Details saved for customer ref {}", personalDetailsDTO.customerRef());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get/{customerRef}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonalDetailsDTO> getCustomerFromReference(@PathVariable final String customerRef) {
        log.info("Received request, attempting to retrieve customer");
        PersonalDetailsDTO personalDetailsDTO = personalDetailsService.getCustomerFromRef(customerRef);
        log.info("Customer received");
        return ResponseEntity.status(HttpStatus.OK).body(personalDetailsDTO);
    }

}
