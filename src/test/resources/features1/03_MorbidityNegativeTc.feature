Feature:Get Operation Get all Morbidities with Negative scenario


  Scenario: Check dietician able to retrieve all morbidities details
    Given Dietician create GET request with no auth
    When Dietician send GET http request with endpoint
    Then Dietician recieves 401 unauthorized
    
   
  Scenario: Check pateint is able to retrieve all morbidities details
    Given Patient create GET request with patient token
    When Patient send GET http request with endpoint
    Then Patient recieves 403 Forbidden
    
     
  Scenario: Check admin able to retrieve all morbidities details with invalid method
    Given admin create POST request  
    When admin send POST http request with endpoint
    Then admin recieves 405 method not allowed
    
    
  Scenario: Check admin able to retrieve all morbidities details with invalid endpoint
    Given admin create GET request   
    When admin send GET http request with invalid endpoint
    Then admin recieves 404 not found
    
     
  Scenario: Check dietician able to retrieve all morbidities details with invalid method
    Given Dietician create POST request    
    When Dietician send POST http request with endpoint
    Then Dietician recieves 405 method not allowed
    
   
  Scenario: Check dietician able to retrieve all morbidities details with invalid endpoint
    Given Dietician create GET request     
    When Dietician send GET http request with invalid endpoint
    Then Dietician recieves 404 not found
    
    
  Scenario: Check dietician able to morbidity condition by test name 
    Given Dietician create GET request with no auth   
    When Dietician send GET http request with morbidity condition by test name endpoint endpoint
    Then Dietician recieves 401 unauthorized
    
     
  Scenario: Check pateint is able to retrieve morbidity condition by test name 
    Given Patient create GET request   
    When Patient send GET http request with morbidity condition by test name  endpoint
    Then Patient recieves 403 Forbidden
    
    
  Scenario: Check admin able to retrieve morbidity condition by test name  with invalid method
    Given admin create POST request       
    When admin send POST http request morbidity condition by test name endpoint
    Then admin recieves 405 method not allowed
    
  
  Scenario: Check admin able to retrieve morbidity condition by invalid test name 
    Given admin create GET request      
    When admin send GET http request with invalid test name endpoint
    Then admin recieves 404 not found
    
    
  Scenario: Check admin able to retrieve morbidity condition by test name with invalid endpoint
    Given admin create GET request     
    When admin send GET http request morbidity condition by test name with invalid endpoint
    Then admin recieves 404 not found
    
   
  Scenario: Check dietician able to retrieve morbidity condition by test name  with invalid method
    Given Dietician create POST request     
    When Dietician send POST http request morbidity condition by test name  with invalid method endpoint
    Then Dietician recieves 405 method not allowed
    
  
    
    Scenario: Check dietician able to retrieve morbidity condition by invalid test name 
    Given Dietician create GET request      
    When Dietician send GET http request with morbidity condition by invalid test name  endpoint
    Then Dietician recieves 404 not found
    
    
  Scenario: Check dietician able to retrieve morbidity condition by test name with invalid endpoint
    Given Dietician create GET request      
    When Dietician send GET http request morbidity condition by test name with invalid endpoint
    Then Dietician recieves 404 not found
    
    
    
    
    
    
    