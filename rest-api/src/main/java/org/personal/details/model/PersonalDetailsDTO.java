package org.personal.details.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record PersonalDetailsDTO(
        @NotBlank(message = "Customer Ref must be supplied")
        String customerRef,
        @NotBlank(message = "Customer Name must be supplied")
        @ToString.Exclude
        String customerName,
        @NotBlank(message = "Address Line 1 must be supplied")
        @ToString.Exclude
        String addressLine1,
        @ToString.Exclude
        String addressLine2,
        @NotBlank(message = "Address Line 1 must be supplied")
        @ToString.Exclude
        String town,
        @ToString.Exclude
        String county,
        String country,
        @NotBlank(message = "Address Line 1 must be supplied")
        @ToString.Exclude
        String postcode) {
}
