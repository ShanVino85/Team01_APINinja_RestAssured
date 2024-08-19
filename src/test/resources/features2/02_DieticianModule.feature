
Feature: POST (create dietician )

@Test02
  Scenario: Check admin able to create dietician with valid data and token 
    Given Admin creates POST request with valid data of Dietician
    When Admin send POST http request with endpoint
    Then Admin recieves 201 created and with response body. 
    
