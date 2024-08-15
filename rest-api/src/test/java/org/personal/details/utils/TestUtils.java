package org.personal.details.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.personal.details.domain.PersonalDetails;
import org.personal.details.model.PersonalDetailsDTO;

public class TestUtils {

    public static PersonalDetailsDTO generatePersonDetailsDTO() {
        return PersonalDetailsDTO.builder()
                .customerRef(RandomStringUtils.randomAlphabetic(20))
                .customerName("John Doe")
                .addressLine1("Address Line 1")
                .addressLine1("Address Line 2")
                .town("Town")
                .county("County")
                .country("Country")
                .postcode("AL1 AL2")
                .build();
    }

    public static PersonalDetails generatePersonDetails() {
        return PersonalDetails.builder()
                .customerRef(RandomStringUtils.randomAlphabetic(20))
                .customerName("John Doe")
                .addressLine1("Address Line 1")
                .addressLine1("Address Line 2")
                .town("Town")
                .county("County")
                .country("Country")
                .postcode("AL1 AL2")
                .build();
    }

}
