
 Feature:Get Operation Get all Morbidities with Admin Token
 
 
  Scenario: Check admin able to retrieve all morbidities details with admin token
    Given admin create GET request with admin token
    When admin send GET http request with endpoint
    Then admin recieves 200 ok with details of the patient id
    
    
  Scenario: Check admin able to retrieve morbidity condition by test name with admin token
    Given admin create GET request with morbidity condition by test name with admin token
    When admin send GET http request with morbidity condition by test name endpoint
    Then admin recieves 200 ok with morbidity condition by test name endpoint details of the patient id
    
   
    
    
 
  