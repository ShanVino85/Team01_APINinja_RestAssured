@tag03
Feature: Patient Module Negative

  Background: 
    Given Set dietician bearer token

  #Patient Module Postrequest Negative
  @Test01
  Scenario: Check dietician able to create patient only with valid additional details
    Given Dietician creates POST request only by valid additional details into the form-data key and value fields.
    When Dietician send POST http request with endpoint only for valid additional details
    Then Dietician recieves 400 Bad request

  @Test02
  Scenario: Check dietician able to create patient with invalid data (mandatory details)
    Given Dietician creates POST request only by invalid mandatory details into the form-data key and value fields.
    When Dietician send POST http request with endpoint only for invalid mandatory details
    Then Dietician recieves 400 Bad request

  @Test03
  Scenario: Check dietician able to create patient with valid mandatory fields and invalid data (additional details)
    Given Dietician creates POST request only by invalid additional details into the form-data key and value fields.
    When Dietician send POST http request with endpoint for valid mandatory fields and invalid additional details
    Then Dietician recieves 400 Bad request

  @Test04
  Scenario: Check dietician able to create patient with valid data and invalid method
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields.
    When Dietician send PUT http request with endpoint
    Then Dietician recieves 405 method not allowed

  @Test05
  Scenario: Check dietician able to create patient with valid data and invalid endpoint
    Given Dietician creates POST request by entering valid data into the form-data key and value fields.
    When Dietician sent POST http request with invalid endpoint
    Then Dietician recieves 404 not found

  @Test06
  Scenario: Check dietician able to create patient with valid data and invalid content type
    Given Dietician creates POST request by entering valid data into the form-data key and value fields and invalid content type
    When Dietician send POST http request with endpoint and invalid content type
    Then Dietician recieves 415 unsupported media type

  @Test07
  Scenario: Check dietician able to create patient with Existing data
    Given Dietician creates POST request by entering Existing data into the form-data key and value fields
    When Dietician send POST http request with endpoint
    Then Dietician recieves 400 Bad request

  #Set No Auth
  @Test08
  Scenario: Check dietician able to create patient with valid data
    Given Dietician creates POST request by entering valid data into the form-data key and value fields and sets no auth
    When Dietician send POST http request with endpoint
    Then Dietician recieves 401 unauthorized

  #Set Admin Bearer token
  @Test09
  Scenario: Check admin able to create patient with valid data and admin token
    Given Admin creates POST request by entering valid data into the form-data key and value fields.
    When Admin send POST http request with endpoint
    Then Admin recieves 403 forbidden

  #Set Patient Bearer token
  @Test10
  Scenario: Check patient able to create patient with valid data and patient token
    Given Patient creates POST request by entering valid data into the form-data key and value fields.
    When Patient send POST http request with endpoint
    Then Patient recieves 403 forbidden

  #Patient Module Putrequest Negative
  @Test11
  Scenario: Check dietician able to update patient with mandatory fields empty and only with valid additional details
    Given Dietician creates PUT request by entering only valid additional details into the form-data key and value fields.
    When Dietician send PUT http request with endpoint by mandatory fields empty  only valid additional details
    Then Dietician recieves 400 Bad request

  @Test12
  Scenario: Check dietician able to update patient with invalid data
    Given Dietician creates PUT request by entering only invalid additional details into the form-data key and value fields.
    When Dietician send PUT http request with endpoint by only invalid additional details
    Then Dietician recieves 400 Bad request

  @Test13
  Scenario: Check dietician able to update patient with valid data and invalid patient id in path parameter
    Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields with invalid patient id
    When Dietician send PUT http request with endpoint by valid data and invalid patient id
    Then Dietician recieves 400 Bad request

  @Test14
  Scenario: Check dietician able to update patient with valid data and invalid method
    Given Dietician create PUT request by entering valid data into the form-data key and value fields
    When Dietician send POST http request with endpoint entering valid data invalid method
    Then Dietician recieves 405 method not allowed

  @Test15
  Scenario: Check dietician able to update patient with valid data and invalid endpoint
    Given Dietician create PUT request by entering valid data into the form-data key and value fields
    When Dietician sent PUT http request with invalid endpoint
    Then Dietician recieves 404 not found

  @Test16
  Scenario: Check dietician able to update patient with valid data, patient id and invalid content type
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type
    When Dietician send PUT http request with endpoint and invalid content type
    Then Dietician recieves 415 unsupported media type

  #Set No auth
  @Test17
  Scenario: Check dietician able to update patient with valid data
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields and sets no auth
    When Dietician send PUT http request with endpoint for sets no auth
    Then Dietician recieves 401 unauthorized

  #Sets Admin token
  @Test18
  Scenario: Check admin able to update patient with valid data and admin token
    Given Admin creates PUT request by entering valid data into the form-data key and value fields.
    When Admin send PUT http request with endpoint
    Then Admin recieves 403 forbidden

  #Sets Patient Token
  @Test19
  Scenario: Check patient able to update patient with valid data and patient token
    Given Patient creates PUT request by entering valid data into the form-data key and value fields.
    When Patient send PUT http request with endpoint
    Then Patient recieves 403 forbidden

  #Negative TestCase for Patient token creation
  @Test20
  Scenario: Check Patient able to login as admin with invalid credential
    Given Patient creates Post request with invalid credential
    When Patient send POST HTTP request with invalid credential and endpoint
    Then Patient recieves 401 unauthorized

  @Test21
  Scenario: Check Patient able to login as admin with valid credential and invalid method
    Given Patient creates GET request with request body.
    When Patient send GET HTTP request with endpoint
    Then Patient recieves 405 method not allowed

  @Test22
  Scenario: Check Patient able to login as admin with valid credential and invalid endpoint
    Given Patient creates Post request with request body.
    When Patient send POST HTTP request with invalid endpoint
    Then Patient recieves 404 notfound

  @Test23
  Scenario: Check Patient able to login as admin with valid credential and invalid content type
    Given Patient creates Post request with request body and invalid content type.
    When Patient send POST HTTP request with invalid content type endpoint
    Then Patient recieves 415 unsupported media type
