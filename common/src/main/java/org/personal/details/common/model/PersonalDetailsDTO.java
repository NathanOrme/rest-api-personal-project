package org.personal.details.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PersonalDetailsDTO(
        @NotBlank(message = "Customer Ref must be supplied")
        String customerRef,
        @NotBlank(message = "Customer Name must be supplied")
        String customerName,
        @NotBlank(message = "Address Line 1 must be supplied")
        String addressLine1,
        String addressLine2,
        @NotBlank(message = "Town must be supplied")
        String town,
        String county,
        String country,
        @NotBlank(message = "Postcode must be supplied")
        String postcode) {

    @Override
    public String toString() {
        String redacted = "REDACTED";
        return "PersonalDetails: [" +
                "customerRef=" + customerRef
                + ", customerName=" + redacted
                + ", addressLine1=" + redacted
                + ", addressLine2=" + redacted
                + ", town=" + redacted
                + ", county=" + redacted
                + ", country=" + country
                + ", postcode=" + redacted
                + "]";
    }
}
