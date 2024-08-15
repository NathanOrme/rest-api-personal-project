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

        assertEquals(personalDetailsDTO.getCustomerRef(), personalDetails.getCustomerRef());
        assertEquals(personalDetailsDTO.getCustomerName(), personalDetails.getCustomerName());
        assertEquals(personalDetailsDTO.getAddressLine1(), personalDetails.getAddressLine1());
        assertEquals(personalDetailsDTO.getAddressLine2(), personalDetails.getAddressLine2());
        assertEquals(personalDetailsDTO.getTown(), personalDetails.getTown());
        assertEquals(personalDetailsDTO.getCounty(), personalDetails.getCounty());
        assertEquals(personalDetailsDTO.getCountry(), personalDetails.getCountry());
        assertEquals(personalDetailsDTO.getPostcode(), personalDetails.getPostcode());
    }

    @Test
    void convertEntityToDTO_WithPopulatedEntity_ConvertsEntity() {
        PersonalDetails personalDetails = TestUtils.generatePersonDetails();
        PersonalDetailsDTO personalDetailsDTO = mapper.convertEntityToDTO(personalDetails);

        assertEquals(personalDetails.getCustomerRef(), personalDetailsDTO.getCustomerRef());
        assertEquals(personalDetails.getCustomerName(), personalDetailsDTO.getCustomerName());
        assertEquals(personalDetails.getAddressLine1(), personalDetailsDTO.getAddressLine1());
        assertEquals(personalDetails.getAddressLine2(), personalDetailsDTO.getAddressLine2());
        assertEquals(personalDetails.getTown(), personalDetailsDTO.getTown());
        assertEquals(personalDetails.getCounty(), personalDetailsDTO.getCounty());
        assertEquals(personalDetails.getCountry(), personalDetailsDTO.getCountry());
        assertEquals(personalDetails.getPostcode(), personalDetailsDTO.getPostcode());
    }

}
