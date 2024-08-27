package org.personal.details.service;

import org.personal.details.common.model.PersonalDetailsDTO;

/**
 * Interface for managing personal details.
 * Provides methods to save and retrieve personal details based on customer reference.
 */
public interface PersonalDetailsService {

    /**
     * Saves the given personal details.
     *
     * @param personalDetailsDTO The {@link PersonalDetailsDTO} object containing the personal details to be saved.
     */
    void saveDetails(PersonalDetailsDTO personalDetailsDTO);

    /**
     * Retrieves personal details for a given customer reference.
     *
     * @param customerReference The customer reference for which details are to be retrieved.
     * @return The {@link PersonalDetailsDTO} object containing the customer's personal details.
     */
    PersonalDetailsDTO getCustomerFromRef(String customerReference);
}