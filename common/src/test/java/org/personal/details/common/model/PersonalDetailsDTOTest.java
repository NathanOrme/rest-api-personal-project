package org.personal.details.common.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

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
        PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO(null, null, null, null, null, null, null, null);
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(personalDetailsDTO);
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(5, violations.size());
    }

    @Test
    void validation_WithMinimumObjects_DoesNotHaveViolations() {
        PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO(
                "Customer_Ref",
                "Namey McNameFace",
                "AL1",
                null,
                "Town",
                null,
                null,
                "TF1 1AA");
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(personalDetailsDTO);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void validation_WithMaximumObjects_DoesNotHaveViolations() {
        PersonalDetailsDTO personalDetailsDTO =
                new PersonalDetailsDTO(
                        "Customer_Ref",
                        "Namey McNameFace",
                        "AL1",
                        "AL2",
                        "Town",
                        "County",
                        "Country",
                        "TF1 1AA");
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(personalDetailsDTO);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void validation_WithMissingRef_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO =
                new PersonalDetailsDTO(
                        null,
                        "Namey McNameFace",
                        "AL1",
                        "AL2",
                        "Town",
                        "County",
                        "Country",
                        "TF1 1AA");
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }

    @Test
    void validation_WithMissingName_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO =
                new PersonalDetailsDTO(
                        "Customer_Ref",
                        null,
                        "AL1",
                        "AL2",
                        "Town",
                        "County",
                        "Country",
                        "TF1 1AA");
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }

    @Test
    void validation_WithMissingAddressLine1_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO =
                new PersonalDetailsDTO(
                        "Customer_Ref",
                        "Namey McNameFace",
                        null,
                        "AL2",
                        "Town",
                        "County",
                        "Country",
                        "TF1 1AA");
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }

    @Test
    void validation_WithMissingTown_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO =
                new PersonalDetailsDTO(
                        "Customer_Ref",
                        "Namey McNameFace",
                        "AL1",
                        "AL2",
                        null,
                        "County",
                        "Country",
                        "TF1 1AA");
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }

    @Test
    void validation_WithMissingPostCode_HasViolations() {
        PersonalDetailsDTO personalDetailsDTO =
                new PersonalDetailsDTO(
                        "Customer_Ref",
                        "Namey McNameFace",
                        "AL1",
                        "AL2",
                        "Town",
                        "County",
                        "Country",
                        null);
        validatePersonalDetailsDTOHasViolations(personalDetailsDTO);
    }


    private void validatePersonalDetailsDTOHasViolations(final PersonalDetailsDTO personalDetailsDTO) {
        Set<ConstraintViolation<PersonalDetailsDTO>> violations = validator.validate(personalDetailsDTO);
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
    }

}
