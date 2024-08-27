package org.personal.details.console.service;

import org.personal.details.console.domain.PersonalDetailsDTO;

/**
 * Interface for sending personal details to an external endpoint.
 */
public interface RequestService {

    /**
     * Sends the given personal details to an external endpoint.
     *
     * @param personalDetailsDTO The {@link PersonalDetailsDTO} object containing the personal details to be sent.
     * @return A boolean indicating whether the request was successful.
     * Returns {@code true} if the details were successfully sent, {@code false} otherwise.
     */
    boolean sendDetailsToEndpoint(PersonalDetailsDTO personalDetailsDTO);
}