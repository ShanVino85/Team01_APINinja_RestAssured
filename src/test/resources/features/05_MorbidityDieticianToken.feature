 Feature: Morbidity Module Dietician Token
 
 @Test01
  Scenario: Check dietician able to retrieve all morbidities details
    Given Dietician create GET request with dietician token to retrieve all morbidities details
    When Dietician send GET http request with endpoint to retrieve all morbidities details
    Then admin recieves 200 ok with details of the patient id to retrieve all morbidities details
    
    @Test02
  Scenario: Check dietician able to retrieve morbidity condition by test name 
    Given Dietician create GET request with dietician token to retrieve morbidity condition by test name 
    When Dietician send GET http request with morbidity condition by test name to retrieve morbidity condition by test name 
    Then Dietician recieves 200 ok with morbidity condition by test name endpoint details of the patient id
    
   
    
    
 
  

  
