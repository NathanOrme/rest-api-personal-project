package org.personal.details.mapper;

import org.personal.details.domain.PersonalDetails;
import org.personal.details.model.PersonalDetailsDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between {@link PersonalDetails} entities
 * and {@link PersonalDetailsDTO} data transfer objects.
 * This class handles the mapping logic to convert
 * data between different layers of the application.
 */
@Component
public class PersonalDetailsMapper {

    /**
     * Converts a {@link PersonalDetailsDTO} to a {@link PersonalDetails} entity.
     *
     * @param personalDetailsDTO The data transfer object containing personal details to be converted.
     * @return A {@link PersonalDetails} entity representing the same data.
     */
    public PersonalDetails convertDTOToEntity(final PersonalDetailsDTO personalDetailsDTO) {
        return PersonalDetails.builder()
                .customerRef(personalDetailsDTO.customerRef())
                .customerName(personalDetailsDTO.customerName())
                .addressLine1(personalDetailsDTO.addressLine1())
                .addressLine2(personalDetailsDTO.addressLine2())
                .town(personalDetailsDTO.town())
                .county(personalDetailsDTO.county())
                .country(personalDetailsDTO.country())
                .postcode(personalDetailsDTO.postcode())
                .build();
    }

    /**
     * Converts a {@link PersonalDetails} entity to a {@link PersonalDetailsDTO}.
     *
     * @param personalDetails The entity containing personal details to be converted.
     * @return A {@link PersonalDetailsDTO} representing the same data.
     */
    public PersonalDetailsDTO convertEntityToDTO(final PersonalDetails personalDetails) {
        return new PersonalDetailsDTO(
                personalDetails.getCustomerRef(),
                personalDetails.getCustomerName(),
                personalDetails.getAddressLine1(),
                personalDetails.getAddressLine2(),
                personalDetails.getTown(),
                personalDetails.getCounty(),
                personalDetails.getCountry(),
                personalDetails.getPostcode());
    }
}