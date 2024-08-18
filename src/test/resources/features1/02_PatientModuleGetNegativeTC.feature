Feature:Get Get Operation Get Patients Morbidity Details and Retrive patient fileid


  Scenario: Check dietician able to retrieve patients morbidity details by patient ID
    Given Dietician create GET request with no auth to retrieve patients morbidity details by patient ID
    When Dietician send GET http request with endpoint to retrieve patients morbidity details by patient ID
    Then Dietician recieves 401 unauthorized to retrieve patients morbidity details by patient ID
    
   
  Scenario: Check admin is able to retrieve patients morbidity details by patient ID 
    Given Admin create GET request with Admin token
    When Admin send GET http request with endpoint to retrieve patients morbidity details by patient ID 
    Then Admin recieves 403 Forbidden to retrieve patients morbidity details by patient ID 
    
     
  Scenario: Check dietician able to retrieve patients morbidity details by patient ID with invalid method
    Given Dietician create POST request to retrieve patients morbidity details by patient ID with invalid method  
    When Dietician send POST http request with endpoint to retrieve patients morbidity details by patient ID with invalid method  
    Then Dietician recieves 405 method not allowed to retrieve patients morbidity details by patient ID with invalid method  
    
    
  Scenario: Check dietician able to retrieve patients morbidity details by invalid patient ID
    Given Dietician create GET request to retrieve patients morbidity details by invalid patient ID
    When Dietician send GET http request with endpoint to retrieve patients morbidity details by invalid patient ID
    Then Dietician recieves 404 not found to retrieve patients morbidity details by invalid patient ID
    
     
  Scenario: Check dietician able to retrieve patients morbidity details by patient ID with invalid endpoint
    Given Dietician create GET request to retrieve patients morbidity details by patient ID with invalid endpoint  
    When Dietician send GET http request with invalid endpoint to retrieve patients morbidity details by patient ID with invalid endpoint
    Then Dietician recieves 404 not found to retrieve patients morbidity details by patient ID with invalid endpoint
    
   
  Scenario: Check dietician able to retrieve patients by fielId
    Given Dietician create GET request  with no auth to retrieve patients by fielId
    When Dietician send GET http request with endpoint to retrieve patients by fielId
    Then Dietician recieves 401 unauthorized to retrieve patients by fielId
    
    
  Scenario: Check admin is able to retrieve patients by fielId
    Given Admin create GET request to retrieve patients by fielId
    When Admin send GET http request with endpoint to retrieve patients by fielId
    Then Admin recieves 403 Forbidden to retrieve patients by fielId
    
     
  Scenario: Check dietician able to retrieve patients by field with invalid method
    Given Dietician create POST request to retrieve patients by field with invalid method
    When Dietician send POST http request with endpoint to retrieve patients by field with invalid method
    Then Dietician recieves 405 method not allowed to retrieve patients by field with invalid method
    
    
  Scenario: Check dietician able to retrieve patients by invalid fileId
    Given Dietician create GET request to retrieve patients by invalid fileId  
    When Dietician send GET http request with endpoint to retrieve patients by invalid fileId
    Then Dietician recieves 404 not found to retrieve patients by invalid fileId
    
  
  Scenario: Check dietician able to retrieve patients by field with invalid endpoint
    Given Dietician create GET request to retrieve patients by field with invalid endpoint     
    When Dietician send GET http request to retrieve patients by field with invalid endpoint
    Then Dietician recieves 404 not found to retrieve patients by field with invalid endpoint
    
    
  
    
    
    
    
    #
    