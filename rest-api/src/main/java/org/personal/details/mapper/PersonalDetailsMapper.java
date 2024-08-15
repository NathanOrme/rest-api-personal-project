package org.personal.details.mapper;

import org.personal.details.domain.PersonalDetails;
import org.personal.details.model.PersonalDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonalDetailsMapper {

    public PersonalDetails convertDTOToEntity(final PersonalDetailsDTO personalDetailsDTO) {
        return PersonalDetails.builder()
                .customerRef(personalDetailsDTO.getCustomerRef())
                .customerName(personalDetailsDTO.getCustomerName())
                .addressLine1(personalDetailsDTO.getAddressLine1())
                .addressLine2(personalDetailsDTO.getAddressLine2())
                .town(personalDetailsDTO.getTown())
                .county(personalDetailsDTO.getCounty())
                .country(personalDetailsDTO.getCountry())
                .postcode(personalDetailsDTO.getPostcode())
                .build();
    }

    public PersonalDetailsDTO convertEntityToDTO(final PersonalDetails personalDetails) {
        return PersonalDetailsDTO.builder()
                .customerRef(personalDetails.getCustomerRef())
                .customerName(personalDetails.getCustomerName())
                .addressLine1(personalDetails.getAddressLine1())
                .addressLine2(personalDetails.getAddressLine2())
                .town(personalDetails.getTown())
                .county(personalDetails.getCounty())
                .country(personalDetails.getCountry())
                .postcode(personalDetails.getPostcode())
                .build();
    }
}
