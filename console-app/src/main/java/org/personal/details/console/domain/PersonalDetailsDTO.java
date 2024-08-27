package org.personal.details.console.domain;

import lombok.ToString;

public record PersonalDetailsDTO(
        String customerRef,
        @ToString.Exclude
        String customerName,
        @ToString.Exclude
        String addressLine1,
        @ToString.Exclude
        String addressLine2,
        @ToString.Exclude
        String town,
        @ToString.Exclude
        String county,
        String country,
        @ToString.Exclude
        String postcode) {
}
