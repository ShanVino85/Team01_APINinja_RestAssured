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
    
    #Patient Module Putrequest Negative
  # Scenario 1: No Authorization
  @Test13
  Scenario: Check dietician unable to add new reports with no authorization
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields and valid patient ID
    When Dietician sends PUT HTTP request to the endpoint with no authorization
    Then Dietician receives 401 Unauthorized

  # Scenario 2: Admin Token
  @Test14
  Scenario: Check admin unable to add new reports with admin bearer token
    Given Admin creates PUT request by entering valid data into the form-data key and value fields and valid patient ID
    When Admin sends PUT HTTP request to the endpoint with admin bearer token
    Then Admin receives 403 Forbidden

  # Scenario 3: Patient Token
  @Test03
  Scenario: Check patient unable to add new reports with patient bearer token
    Given Patient creates PUT request by entering valid data into the form-data key and value fields and valid patient ID
    When Patient sends PUT HTTP request to the endpoint with patient bearer token
    Then Patient receives 403 Forbidden

  # Scenario : Dietician Adding Reports with Invalid Data
  @Test10
  Scenario: Check dietician unable to add new reports with invalid data
    Given Dietician creates PUT request by entering invalid data (Additional details only) into the form-data key and value fields and valid patient ID
    When Dietician sends PUT HTTP request to the endpoint with valid patient ID and  with invalid data
    Then Dietician receives 400 Bad Request

  # Scenario 11: Dietician Adding Reports with Invalid Patient ID
  @Test11
  Scenario: Check dietician unable to add new reports with valid data and invalid patient ID as path parameter
    Given Dietician creates PUT request by entering valid data (Additional details only) into the form-data key and value fields and invalid patient ID
    When Dietician sends PUT HTTP request to the endpoint with invalid patient ID as path parameter
    Then Dietician receives 404 Not Found

  # Scenario 12: Dietician Using Invalid Method
  @Test12
  Scenario: Check dietician unable to add new reports using POST method instead of PUT
    Given Dietician creates POST request by entering valid data into the form-data key and value fields and valid patient ID
    When Dietician sends POST HTTP request to the endpoint and add new reports using POST method
    Then Dietician receives 405 Method Not Allowed

  # Scenario 13: Dietician Using Invalid Endpoint
  @Test13
  Scenario: Check dietician unable to add new reports using an invalid endpoint
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields and valid patient ID
    When Dietician sends PUT HTTP request to an invalid endpoint and unable to add new reports
    Then Dietician receives 404 Not Found

  # Scenario : Dietician Using Invalid Content Type
  @Test14
  Scenario: Check dietician unable to add new reports with invalid content type
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields and valid patient ID with invalid content type
    When Dietician sends PUT HTTP request to the endpoint and  invalid content type
    Then Dietician receives 415 Unsupported Media Type

  #Patient Module Getrequest Negative
  #Feature: Get Operation [Get all Patients]
  # Scenario : No Authorization
  @Test01
  Scenario: Check dietician unable to retrieve all patients with no authorization
    Given Dietician creates GET request to the endpoint with no authorization
    When Dietician sends GET HTTP request to the endpoint with no authorization
    Then Dietician receives 401 Unauthorized

  # Scenario : Admin Token
  @Test02
  Scenario: Check admin unable to retrieve patients with admin bearer token
    Given Admin creates GET request to the endpoint
    When Admin sends GET HTTP request to the endpoint with admin bearer token
    Then Admin receives 403 Forbidden

  # Scenario : Patient Token
  @Test03
  Scenario: Check patient unable to retrieve patients with patient bearer token
    Given Patient creates GET request to the endpoint
    When Patient sends GET HTTP request to the endpoint with patient bearer token
    Then Patient receives 403 Forbidden

  # Scenario 5: Invalid Method
  @Test05
  Scenario: Check dietician unable to retrieve all patients with invalid HTTP method
    Given Dietician creates PUT request to the endpoint
    When Dietician sends PUT HTTP request to the endpoint with valid data
    Then Dietician receives 405 Method Not Allowed

  # Scenario 6: Invalid Endpoint
  @Test06
  Scenario: Check dietician unable to retrieve all patients with invalid endpoint
    Given Dietician creates GET request to an invalid endpoint
    When Dietician sends GET HTTP request to the invalid endpoint
    Then Dietician receives 404 Not Found

    # Negative Scenario of Get request with Morbidity details
@Test01
  Scenario: Check dietician able to retrieve patients morbidity details by patient ID
    Given Dietician create GET request with no auth to retrieve patients morbidity details by patient ID
    When Dietician send GET http request with endpoint to retrieve patients morbidity details by patient ID
    Then Dietician recieves 401 unauthorized to retrieve patients morbidity details by patient ID
    
   @Test02
  Scenario: Check admin is able to retrieve patients morbidity details by patient ID 
    Given Admin create GET request with Admin token
    When Admin send GET http request with endpoint to retrieve patients morbidity details by patient ID 
    Then Admin recieves 403 Forbidden to retrieve patients morbidity details by patient ID 
    
  @Test03   
  Scenario: Check dietician able to retrieve patients morbidity details by patient ID with invalid method
    Given Dietician create POST request to retrieve patients morbidity details by patient ID with invalid method  
    When Dietician send POST http request with endpoint to retrieve patients morbidity details by patient ID with invalid method  
    Then Dietician recieves 405 method not allowed to retrieve patients morbidity details by patient ID with invalid method  
    
   @Test04 
  Scenario: Check dietician able to retrieve patients morbidity details by invalid patient ID
    Given Dietician create GET request to retrieve patients morbidity details by invalid patient ID
    When Dietician send GET http request with endpoint to retrieve patients morbidity details by invalid patient ID
    Then Dietician recieves 404 not found to retrieve patients morbidity details by invalid patient ID
    
   @Test05  
  Scenario: Check dietician able to retrieve patients morbidity details by patient ID with invalid endpoint
    Given Dietician create GET request to retrieve patients morbidity details by patient ID with invalid endpoint  
    When Dietician send GET http request with invalid endpoint to retrieve patients morbidity details by patient ID with invalid endpoint
    Then Dietician recieves 404 not found to retrieve patients morbidity details by patient ID with invalid endpoint
    
  @Test06 
  Scenario: Check dietician able to retrieve patients by fielId
    Given Dietician create GET request  with no auth to retrieve patients by fielId
    When Dietician send GET http request with endpoint to retrieve patients by fielId
    Then Dietician recieves 401 unauthorized to retrieve patients by fielId
    
   @Test07 
  Scenario: Check admin is able to retrieve patients by fielId
    Given Admin create GET request to retrieve patients by fielId
    When Admin send GET http request with endpoint to retrieve patients by fielId
    Then Admin recieves 403 Forbidden to retrieve patients by fielId
    
   @Test08  
  Scenario: Check dietician able to retrieve patients by field with invalid method
    Given Dietician create POST request to retrieve patients by field with invalid method
    When Dietician send POST http request with endpoint to retrieve patients by field with invalid method
    Then Dietician recieves 405 method not allowed to retrieve patients by field with invalid method
    
    @Test09
  Scenario: Check dietician able to retrieve patients by invalid fileId
    Given Dietician create GET request to retrieve patients by invalid fileId  
    When Dietician send GET http request with endpoint to retrieve patients by invalid fileId
    Then Dietician recieves 404 not found to retrieve patients by invalid fileId
    
  @Test10
  Scenario: Check dietician able to retrieve patients by field with invalid endpoint
    Given Dietician create GET request to retrieve patients by field with invalid endpoint     
    When Dietician send GET http request to retrieve patients by field with invalid endpoint
    Then Dietician recieves 404 not found to retrieve patients by field with invalid endpoint
    
    
  
    
    
    
