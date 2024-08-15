package org.personal.details.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.personal.details.encryption.StringEncryptor;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "customerReference")
    private String customerRef;

    @ToString.Exclude
    @Column(name = "customerName")
    @Convert(converter = StringEncryptor.class)
    private String customerName;

    @ToString.Exclude
    @Column(name = "addressLine1")
    @Convert(converter = StringEncryptor.class)
    private String addressLine1;

    @ToString.Exclude
    @Column(name = "addressLine2")
    @Convert(converter = StringEncryptor.class)
    private String addressLine2;

    @ToString.Exclude
    @Column(name = "town")
    @Convert(converter = StringEncryptor.class)
    private String town;

    @ToString.Exclude
    @Column(name = "county")
    @Convert(converter = StringEncryptor.class)
    private String county;

    @Column(name = "country")
    private String country;

    @ToString.Exclude
    @Column(name = "postcode")
    @Convert(converter = StringEncryptor.class)
    private String postcode;

}
