package org.personal.details.service;

import lombok.RequiredArgsConstructor;
import org.personal.details.domain.PersonalDetails;
import org.personal.details.exceptions.CustomerDoesNotExistException;
import org.personal.details.exceptions.CustomerRefExistsException;
import org.personal.details.mapper.PersonalDetailsMapper;
import org.personal.details.model.PersonalDetailsDTO;
import org.personal.details.repository.PersonalDetailsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonalDetailsService {

    private final PersonalDetailsMapper personalDetailsMapper;

    private final PersonalDetailsRepository personalDetailsRepository;

    public void saveDetails(final PersonalDetailsDTO personalDetailsDTO) {
        boolean isCustomerRefPresent = isCustomerRefPresent(personalDetailsDTO.customerRef());
        if (isCustomerRefPresent) {
            throw new CustomerRefExistsException("The record for customer ref %s could not be processed".formatted(personalDetailsDTO.customerRef()));
        }
        PersonalDetails personalDetails = personalDetailsMapper.convertDTOToEntity(personalDetailsDTO);
        personalDetailsRepository.save(personalDetails);
    }

    public PersonalDetailsDTO getCustomerFromRef(final String customerReference) {
        Optional<PersonalDetails> personalDetails = personalDetailsRepository.findOptionalPersonalDetailsByCustomerRef(customerReference);
        if (personalDetails.isPresent()) {
            return personalDetailsMapper.convertEntityToDTO(personalDetails.get());
        }
        throw new CustomerDoesNotExistException("There is no matching customer for reference %s".formatted(customerReference));


    }

    private boolean isCustomerRefPresent(final String customerRef) {
        return personalDetailsRepository.findOptionalPersonalDetailsByCustomerRef(customerRef).isPresent();
    }

}
