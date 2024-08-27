package org.personal.details.console.domain;

public record PersonalDetailsDTO(
        String customerRef,
        String customerName,
        String addressLine1,
        String addressLine2,
        String town,
        String county,
        String country,
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
