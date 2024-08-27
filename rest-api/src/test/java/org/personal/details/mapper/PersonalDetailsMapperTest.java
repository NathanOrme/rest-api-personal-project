package org.personal.details.mapper;

import org.junit.jupiter.api.Test;
import org.personal.details.domain.PersonalDetails;
import org.personal.details.model.PersonalDetailsDTO;
import org.personal.details.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonalDetailsMapperTest {

    private final PersonalDetailsMapper mapper = new PersonalDetailsMapper();

    @Test
    void convertDTOToEntity_WithPopulatedDTO_ConvertsDTO() {
        PersonalDetailsDTO personalDetailsDTO = TestUtils.generatePersonDetailsDTO();
        PersonalDetails personalDetails = mapper.convertDTOToEntity(personalDetailsDTO);

        assertEquals(personalDetailsDTO.customerRef(), personalDetails.getCustomerRef());
        assertEquals(personalDetailsDTO.customerName(), personalDetails.getCustomerName());
        assertEquals(personalDetailsDTO.addressLine1(), personalDetails.getAddressLine1());
        assertEquals(personalDetailsDTO.addressLine2(), personalDetails.getAddressLine2());
        assertEquals(personalDetailsDTO.town(), personalDetails.getTown());
        assertEquals(personalDetailsDTO.county(), personalDetails.getCounty());
        assertEquals(personalDetailsDTO.country(), personalDetails.getCountry());
        assertEquals(personalDetailsDTO.postcode(), personalDetails.getPostcode());
    }

    @Test
    void convertEntityToDTO_WithPopulatedEntity_ConvertsEntity() {
        PersonalDetails personalDetails = TestUtils.generatePersonDetails();
        PersonalDetailsDTO personalDetailsDTO = mapper.convertEntityToDTO(personalDetails);

        assertEquals(personalDetails.getCustomerRef(), personalDetailsDTO.customerRef());
        assertEquals(personalDetails.getCustomerName(), personalDetailsDTO.customerName());
        assertEquals(personalDetails.getAddressLine1(), personalDetailsDTO.addressLine1());
        assertEquals(personalDetails.getAddressLine2(), personalDetailsDTO.addressLine2());
        assertEquals(personalDetails.getTown(), personalDetailsDTO.town());
        assertEquals(personalDetails.getCounty(), personalDetailsDTO.county());
        assertEquals(personalDetails.getCountry(), personalDetailsDTO.country());
        assertEquals(personalDetails.getPostcode(), personalDetailsDTO.postcode());
    }

}
