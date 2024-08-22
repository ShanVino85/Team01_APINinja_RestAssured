@tag03
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

  # Put Operation [Add New Reports with/without Vitals for existing Patient]
  # Scenario : Dietician Adding Only Vitals without Report
  @Test08
  Scenario: Check dietician able to add only new vitals without reports for existing patient without existing report
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields except file and valid patient ID without reports for existing patient without existing report
    When Dietician sends PUT HTTP request to the endpoint with valid patient ID without reports for existing patient without existing report
    Then Dietician receives 200 OK and the response body contains updated details

  # Scenario : Dietician Adding Reports with Mandatory Details Only
  @Test09
  Scenario: Check dietician able to add new reports with only valid mandatory details for existing patient
    Given Dietician creates PUT request by entering valid data (Mandatory only) into the form-data key and value fields and valid patient ID
    When Dietician sends PUT HTTP request to the endpoint with valid patient ID add new reports with only valid mandatory details
    Then Dietician receives 200 OK and the response body contains updated details

  # Scenario : Dietician Adding Reports with Vitals
  #@Test09
  #Scenario: Check dietician able to add new reports with vitals for existing patient with valid data
  #Given Dietician creates PUT request by entering valid data( Mandatory and additional details) into the form-data key and value fields and valid patient ID
  #When Dietician send PUT http request with endpoint
  #Then Dietician recieves 200 ok and with updated response body
  # Scenario : Dietician Adding Reports without Vitals
  @Test10
  Scenario: Check dietician able to add new reports without vitals for existing patient with valid data
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields except valid vitals data and valid patient ID
    When Dietician send PUT http request  to the endpoint with valid patient ID
    Then Dietician recieves 200 ok and with updated response body contains updated details

  # Scenario : Dietician Adding Only Vitals with Report
  @Test11
  Scenario: Check dietician able to add only new vitals without reports for existing patient with existing report
    Given Dietician creates PUT request by entering valid data into the form-data key and value fields except file and valid patient ID
    When Dietician sends PUT HTTP request to the endpoint with valid patient ID
    Then Dietician receives 200 OK and the response body contains updated details

  # Scenario : Dietician Adding Reports with Additional Details Only
  @Test12
  Scenario: Check dietician able to add new reports with only valid additional details for existing patient
    Given Dietician creates PUT request by entering valid data (Additional details only) into the form-data key and value fields and valid patient ID
    When Dietician sends PUT HTTP request to the endpoint with valid patient ID add new reports with only valid additional details
    Then Dietician receives 200 OK and the response body contains updated details

 
  # Scenario : Dietician Token
  @Test13
  Scenario: Check dietician able to retrieve all patients with valid dietician token
    Given Dietician creates GET request to the endpoint
    When Dietician sends GET HTTP request to the endpoint with dietician bearer token
    Then Dietician receives 200 OK with response body containing patient details

  #Get Patients Morbidity Details with Dietician Token
  @Test14
  Scenario: Check dietician able to retrieve patients morbidity details by patient Endpoint with Dietician Token
    Given Dietician create GET request patients morbidity details by endpoint as patient
    When Dietician send GET http request with endpoint by endpoint as patient
    Then Dietician recieves 200 ok with details morbidity details by endpoint as patient

  #Get Patients file Id with Dietician Token
  @Test15
  Scenario: Check dietician able to retrieve patients by file Id with Dietician Token
    Given Dietician create GET request by field Id with Dietician Token
    When Dietician create GET request patients morbidity details by endpoint as patient File id
    Then Dietician recieves 200 ok with details of the patient id by field with Dietician Token

  #Get Patients Morbidity Details with Patient Token
  @Test16
  Scenario: Check Patient able to retrieve patients morbidity details by patient Endpoint with Patient Token
    Given Patient create GET request retrieve patients morbidity details by patient Endpoint with Patient Token
    When Patient send GET http request with retrieve patients morbidity details by patient Endpoint with Patient Token
    Then Patient recieves 200 ok with retrieve patients morbidity details by patient Endpoint with Patient Token

  #Get Patients file Id with Patient Token
  @Test17
  Scenario: Check Patient able to retrieve patients by field Id with Patient Token
    Given Check patient is able to retrieve patients morbidity details by File ID with Patient Token
    When Patient create GET request patients morbidity details by endpoint as patient
    Then Patient recieves 200 ok with details of the patient id by field with Patient Token
