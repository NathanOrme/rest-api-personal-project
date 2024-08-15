package org.personal.details.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonalDetailsDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void validation_WithEmptyObject_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO = PersonalDetailsDTO.builder()
                .build();
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(personalDetailsDTO);
        assertFalse(violations.isEmpty());
        assertEquals(5, violations.size());
    }

    @Test
    void validation_WithMinimumObjects_DoesNotHaveViolations() {
        PersonalDetailsDTO personalDetailsDTO = PersonalDetailsDTO.builder()
                .customerRef("Customer_Ref")
                .customerName("Namey McNameFace")
                .addressLine1("AL1")
                .town("Town")
                .postcode("TF1 1AA")
                .build();
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(personalDetailsDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void validation_WithMaximumObjects_DoesNotHaveViolations() {
        PersonalDetailsDTO personalDetailsDTO = PersonalDetailsDTO.builder()
                .customerRef("Customer_Ref")
                .customerName("Namey McNameFace")
                .addressLine1("AL1")
                .addressLine2("AL2")
                .town("Town")
                .county("County")
                .country("Country")
                .postcode("TF1 1AA")
                .build();
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(personalDetailsDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void validation_WithMissingRef_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO = PersonalDetailsDTO.builder()
                .customerRef(null)
                .customerName("Namey McNameFace")
                .addressLine1("AL1")
                .town("Town")
                .postcode("TF1 1AA")
                .build();
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }

    @Test
    void validation_WithMissingName_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO = PersonalDetailsDTO.builder()
                .customerRef("Ref")
                .customerName(null)
                .addressLine1("AL1")
                .town("Town")
                .postcode("TF1 1AA")
                .build();
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }

    @Test
    void validation_WithMissingAddressLine1_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO = PersonalDetailsDTO.builder()
                .customerRef("Ref")
                .customerName("Name")
                .addressLine1(null)
                .town("Town")
                .postcode("TF1 1AA")
                .build();
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }

    @Test
    void validation_WithMissingTown_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO = PersonalDetailsDTO.builder()
                .customerRef("Ref")
                .customerName("Name")
                .addressLine1("AL1")
                .town(null)
                .postcode("TF1 1AA")
                .build();
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }

    @Test
    void validation_WithMissingPostCode_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO = PersonalDetailsDTO.builder()
                .customerRef("Ref")
                .customerName("Name")
                .addressLine1("AddressLine1")
                .town("Town")
                .postcode(null)
                .build();
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }


    private void validatePersonalDetailsDTOHasViolations(final PersonalDetailsDTO personalDetailsDTO) {
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(personalDetailsDTO);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }

}
