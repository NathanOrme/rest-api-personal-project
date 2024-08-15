package org.personal.details.console.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {

    private String customerRef;

    @ToString.Exclude
    private String customerName;

    @ToString.Exclude
    private String addressLine1;

    @ToString.Exclude
    private String addressLine2;

    @ToString.Exclude
    private String town;

    @ToString.Exclude
    private String county;

    private String country;

    @ToString.Exclude
    private String postcode;

}
