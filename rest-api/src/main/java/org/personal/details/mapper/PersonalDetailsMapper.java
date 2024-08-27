package org.personal.details.mapper;

import org.personal.details.domain.PersonalDetails;
import org.personal.details.model.PersonalDetailsDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonalDetailsMapper {

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
