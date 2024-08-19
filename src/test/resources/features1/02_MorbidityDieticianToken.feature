 Feature:Get Operation Get all Morbidities with Dietician Token
 
 @Test01
  Scenario: Check dietician able to retrieve all morbidities details
    Given Dietician create GET request with dietician token 
    When Dietician send GET http request with endpoint
    Then admin recieves 200 ok with details of the patient id
    
    @Test02
  Scenario: Check dietician able to retrieve morbidity condition by test name 
    Given Dietician create GET request with dietician token
    When Dietician send GET http request with morbidity condition by test name endpoint endpoint
    Then Dietician recieves 200 ok with morbidity condition by test name endpoint details of the patient id
    
   
    
    
 
  
