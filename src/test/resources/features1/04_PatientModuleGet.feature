 Feature:Get Get Operation [Get Patients Morbidity Details ] with Dietician Token
 
 
  Scenario: Check dietician able to retrieve patients morbidity details by patient Endpoint with Dietician Token
    Given Dietician create GET request patients morbidity details by endpoint as patient
    When Dietician send GET http request with endpoint by endpoint as patient 
    Then Dietician recieves 200 ok with details morbidity details by endpoint as patient
    
    
     
    Scenario: Check dietician able to retrieve patients by file Id with Dietician Token
    Given Dietician create GET request by field Id with Dietician Token
    When Dietician create GET request patients morbidity details by endpoint as patient File id
    Then Dietician recieves 200 ok with details of the patient id by field with Dietician Token
    
    Scenario: Check Patient able to retrieve patients morbidity details by patient Endpoint with Patient Token
    Given Patient create GET request retrieve patients morbidity details by patient Endpoint with Patient Token
    When Patient send GET http request with retrieve patients morbidity details by patient Endpoint with Patient Token
    Then Patient recieves 200 ok with retrieve patients morbidity details by patient Endpoint with Patient Token
    
    
    
    Scenario: Check Patient able to retrieve patients by field Id with Patient Token
    Given Check patient is able to retrieve patients morbidity details by File ID with Patient Token
    When Patient create GET request patients morbidity details by endpoint as patient 
    Then Patient recieves 200 ok with details of the patient id by field with Patient Token
    
  