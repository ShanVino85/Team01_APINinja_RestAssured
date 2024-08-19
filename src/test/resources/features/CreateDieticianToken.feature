Feature: Create Dietecian Token

#Positive
@Test01
  Scenario: Check user able to Create dietician token
    Given User creates Post request with dietician data request body
    When User send POST HTTP request for Dietician token with endpoint	
    Then User recieves 200 created with response body