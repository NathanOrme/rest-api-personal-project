# Personal Details Common Module

<!-- TOC -->

* [Personal Details Common Module](#personal-details-common-module)
    * [Overview](#overview)
    * [PersonalDetailsDTO](#personaldetailsdto)
        * [Overview](#overview-1)
        * [Annotations](#annotations)
        * [Fields:](#fields)
        * [Usage](#usage)

<!-- TOC -->

## Overview

This Maven module is designed to encapsulate common elements that can be shared across multiple services or applications
within the project. The primary goal is to promote code reuse, maintain consistency, and reduce duplication by
centralizing frequently used components.

Currently, this module includes Data Transfer Objects (DTOs), with the following DTO being available:

## PersonalDetailsDTO

### Overview

The PersonalDetailsDTO class is a data transfer object that encapsulates personal details such as customer reference,
name, address, and postcode. It is used to transfer personal details between different layers of the application or
between services.

### Annotations

@JsonInclude(JsonInclude.Include.NON_NULL): Ensures that null fields are not included in the serialized JSON output.

@NotBlank: Enforces that certain fields (e.g., customerRef, customerName, addressLine1, town, and postcode) must not be
null or empty.

### Fields:

String customerRef: The customer reference identifier. Must be supplied.

String customerName: The customer's name. Must be supplied.

String addressLine1: The first line of the customer's address. Must be supplied.

String addressLine2: The second line of the customer's address. Optional.

String town: The town or city of the customer's address. Must be supplied.

String county: The county or region of the customer's address. Optional.

String country: The country of the customer's address. Optional.

String postcode: The postal code of the customer's address. Must be supplied.

### Usage

To use the PersonalDetailsDTO in your application, add the following dependency to your pom.xml:

```xml

<dependency>
    <groupId>org.personal.details</groupId>
    <artifactId>common</artifactId>
    <version>VERSION_HERE</version>
</dependency>
```