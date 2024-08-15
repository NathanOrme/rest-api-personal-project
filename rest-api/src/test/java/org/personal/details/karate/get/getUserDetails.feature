Feature: Fetching Personal Details

  Background:
    * url 'https://localhost:' + karate.properties['karate.server.port']
    * configure headers = { Authorization: 'Basic dXNlcm5hbWU6cGFzc3dvcmQ=' }

  Scenario: Testing Fetch With No Matching Account, then Saving and fetching

    Given path '/personal-details/get/121'
    When method GET
    Then status 404

    Given path '/personal-details/save/'
    And request { customerRef: '121' , customerName: 'customer name' , addressLine1: 'AL1' , town: 'town', postcode: 'AL1 AL2' }
    When method POST
    Then status 201

    Given path '/personal-details/get/121'
    When method GET
    Then status 200
    And match $ == { customerRef: '121' , customerName: 'customer name' , addressLine1: 'AL1' , town: 'town', postcode: 'AL1 AL2' }