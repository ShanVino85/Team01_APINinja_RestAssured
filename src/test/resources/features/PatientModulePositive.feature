@tag04
Feature: Patient Module Positive

  Background: 
    Given Set dietician bearer token

  #Patient Module Postrequest
  @Test01
  Scenario: Check dietician able to create patient with valid data and token
    Given Dietician creates POST request by entering valid data. ( Mandatory and additional details) into the form-data key and value fields.
    When Dietician send POST http request with endpoint
    Then Dietician recieves 201 created and with response body. (Auto created dietician ID and login password)

  @Test02
  Scenario: Check dietician able to create patient only with valid mandatory details
    Given Dietician creates POST request only by valid mandatory details into the form-data key and value fields.
    When Dietician send POST http request with endpoint	only with valid mandatory details
    Then Dietician recieves 201 created and with response body. (Auto created dietician ID and login password)

  #Patienttoken creation
  @Test03
  Scenario: Check user able to login as Patient with valid data(Patient token)
    Given User creates Patient Post request with request body
    When User send Patient POST HTTP request with endpoint
    Then Patient recieves 200 created with response body

  #Patient Module Putrequest
  
  @Test04
  Scenario: Check dietician able to update patient with existing file by not attaching new file
    Given Dietician creates PUT request by not attaching any file into the form-data key and value fields.
    When Dietician send PUT http request with endpoint with existing file by not attaching new file
    Then Dietician recieves 200 ok and with updated response body
  
  @Test05
  Scenario: Check dietician able to update patient with valid data, patient id and token
    Given Dietician creates PUT request by entering valid data. ( Mandatory and additional details) into the form-data key and value fields.
    When Dietician send PUT http request with endpoint with valid data
    Then Dietician recieves 200 ok and with updated response body

  @Test06
  Scenario: Check dietician able to update patient only with valid mandatory details
    Given Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields.
    When Dietician send PUT http request with endpoint only valid mandatory details
    Then Dietician recieves 200 ok and with updated response body

@Test07
Scenario: Check dietician able to add new reports with vitals for existing patient with valid data	
Given Dietician creates PUT request by entering valid data( Mandatory and additional details) into the form-data key and value fields and valid patient ID	
When Dietician send PUT http request with endpoint by add new reports with vitals for existing patient with valid data		
Then Dietician recieves 200 ok and with updated response body
  