package org.personal.details.model;

import jakarta.validation.constraints.NotBlank;
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
public class PersonalDetailsDTO {

    @NotBlank(message = "Customer Ref must be supplied")
    private String customerRef;

    @NotBlank(message = "Customer Name must be supplied")
    @ToString.Exclude
    private String customerName;

    @NotBlank(message = "Address Line 1 must be supplied")
    @ToString.Exclude
    private String addressLine1;

    @ToString.Exclude
    private String addressLine2;

    @NotBlank(message = "Address Line 1 must be supplied")
    @ToString.Exclude
    private String town;

    @ToString.Exclude
    private String county;

    private String country;

    @NotBlank(message = "Address Line 1 must be supplied")
    @ToString.Exclude
    private String postcode;

}
