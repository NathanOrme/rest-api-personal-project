Feature: Saving Personal Details

  Background:
    * url 'https://localhost:' + karate.properties['karate.server.port']
    * configure headers = { Authorization: 'Basic dXNlcm5hbWU6cGFzc3dvcmQ=' }

  Scenario: Testing Save

    Given path '/personal-details/save/'
    And request { customerRef: '212' , customerName: 'customer name' , addressLine1: 'AL1' , town: 'town', postcode: 'AL1 AL2' }
    When method POST
    Then status 201

    Given path '/personal-details/save/'
    And request { customerRef: '213' , customerName: 'customer name' , addressLine1: 'AL1' , addressLine2: 'AL2' , town: 'town',  county: 'county' , country:'country' , postcode: 'AL1 AL2' }
    When method POST
    Then status 201

    Given path '/personal-details/save/'
    And request { customerRef: '212' , customerName: 'customer name' , addressLine1: 'AL1' , town: 'town', postcode: 'AL1 AL2' }
    When method POST
    Then status 422
    And match $ == { errorMessage: 'The record for customer ref 212 could not be processed' }

    Given path '/personal-details/save/'
    And request { customerRef: '213' , customerName: 'customer name' , addressLine1: 'AL1' , addressLine2: 'AL2' , town: 'town',  county: 'county' , country:'country' , postcode: 'AL1 AL2' }
    When method POST
    Then status 422
    And match $ == { errorMessage: 'The record for customer ref 213 could not be processed' }