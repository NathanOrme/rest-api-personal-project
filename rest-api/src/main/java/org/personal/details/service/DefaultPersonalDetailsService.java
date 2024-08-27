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

/**
 * Service class responsible for managing personal details operations.
 */
@Component
@RequiredArgsConstructor
public class DefaultPersonalDetailsService implements PersonalDetailsService {

    private final PersonalDetailsMapper personalDetailsMapper;

    private final PersonalDetailsRepository personalDetailsRepository;

    /**
     * Saves the personal details of a customer. If a customer with the same reference already exists,
     * a {@link CustomerRefExistsException} is thrown.
     *
     * @param personalDetailsDTO The personal details data transfer object containing customer details.
     * @throws CustomerRefExistsException if a customer with the given reference already exists.
     */
    public void saveDetails(final PersonalDetailsDTO personalDetailsDTO) {
        boolean isCustomerRefPresent = isCustomerRefPresent(personalDetailsDTO.customerRef());
        if (isCustomerRefPresent) {
            throw new CustomerRefExistsException(
                    "The record for customer ref %s could not be processed"
                            .formatted(personalDetailsDTO.customerRef()));
        }
        PersonalDetails personalDetails = personalDetailsMapper.convertDTOToEntity(personalDetailsDTO);
        personalDetailsRepository.save(personalDetails);
    }

    /**
     * Retrieves personal details of a customer by customer reference. If no matching customer is found,
     * a {@link CustomerDoesNotExistException} is thrown.
     *
     * @param customerReference The unique reference for the customer.
     * @return PersonalDetailsDTO The personal details data transfer object for the customer.
     * @throws CustomerDoesNotExistException if no customer with the given reference is found.
     */
    public PersonalDetailsDTO getCustomerFromRef(final String customerReference) {
        Optional<PersonalDetails> personalDetails = personalDetailsRepository
                .findOptionalPersonalDetailsByCustomerRef(customerReference);
        if (personalDetails.isPresent()) {
            return personalDetailsMapper.convertEntityToDTO(personalDetails.get());
        }
        throw new CustomerDoesNotExistException
                ("There is no matching customer for reference %s"
                        .formatted(customerReference));
    }

    /**
     * Checks if a customer reference already exists in the repository.
     *
     * @param customerRef The customer reference to check.
     * @return boolean True if the customer reference exists, false otherwise.
     */
    private boolean isCustomerRefPresent(final String customerRef) {
        return personalDetailsRepository.findOptionalPersonalDetailsByCustomerRef(customerRef).isPresent();
    }
}