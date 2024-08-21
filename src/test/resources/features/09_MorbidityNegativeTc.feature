Feature: Morbidity Module Negative feature

@Test01
  Scenario: Check dietician able to retrieve all morbidities details
    Given Dietician create GET request with no auth to retrieve all morbidities details
    When Dietician send GET http request with no auth to retrieve all morbidities details
    Then Dietician recieves 401 unauthorized with no auth all morbidities details
    
   @Test02
  Scenario: Check pateint is able to retrieve all morbidities details
    Given Patient create GET request with patient token to retrieve all morbidities details
    When Patient send GET http request with to retrieve all morbidities details endpoint
    Then Patient recieves 403 Forbidden to retrieve all morbidities details
    
   @Test03  
  Scenario: Check admin able to retrieve all morbidities details with invalid method
    Given admin create POST request to retrieve all morbidities details with invalid method
    When admin send POST http request with endpoint to retrieve all morbidities details with invalid method
    Then admin recieves 405 method not allowed to retrieve all morbidities details with invalid method
    
   @Test04 
  Scenario: Check admin able to retrieve all morbidities details with invalid endpoint
    Given admin create GET request to retrieve all morbidities details with invalid endpoint 
    When admin send GET http request to retrieve all morbidities details with invalid endpoint
    Then admin recieves 404 not found to retrieve all morbidities details with invalid endpoint
    
  @Test05   
  Scenario: Check dietician able to retrieve all morbidities details with invalid method
    Given Dietician create POST request to retrieve all morbidities details with invalid method   
    When Dietician send POST http requestto retrieve all morbidities details with invalid method
    Then Dietician recieves 405 method not allowed to retrieve all morbidities details with invalid method
    
   @Test06
  Scenario: Check dietician able to retrieve all morbidities details with invalid endpoint
    Given Dietician create GET request to retrieve all morbidities details with invalid endpoint     
    When Dietician send GET http request to retrieve all morbidities details with invalid endpoint
    Then Dietician recieves 404 not found to retrieve all morbidities details with invalid endpoint
    
    @Test07
  Scenario: Check dietician able to morbidity condition by test name 
    Given Dietician create GET request with no auth to morbidity condition by test name  
    When Dietician send GET http request with morbidity condition by test name endpoint endpoint
    Then Dietician recieves 401 unauthorized with morbidity condition by test name
    
   @Test08  
  Scenario: Check pateint is able to retrieve morbidity condition by test name 
    Given Patient create GET request to retrieve morbidity condition by test name  
    When Patient send GET http request with morbidity condition by test name  endpoint
    Then  Patient receives 403 Forbidden to retrieve morbidity condition by test name 

    
    @Test09
  Scenario: Check admin able to retrieve morbidity condition by test name  with invalid method
    Given admin create POST request to retrieve morbidity condition by test name  with invalid method    
    When admin send POST http request morbidity condition by test name endpoint
    Then admin recieves 405 method not allowed to retrieve morbidity condition by test name  with invalid method
   
  @Test10
  Scenario: Check admin able to retrieve morbidity condition by invalid test name 
    Given admin create GET request to retrieve all morbidities details with invalid endpoint      
    When admin send GET http request with invalid test name endpoint
    Then admin recieves 404 not found to retrieve all morbidities details with invalid endpoint
    
    @Test11
  Scenario: Check admin able to retrieve morbidity condition by test name with invalid endpoint
    Given admin create GET request to retrieve all morbidities details with invalid endpoint     
    When admin send GET http request morbidity condition by test name with invalid endpoint
    Then admin recieves 404 not found to retrieve all morbidities details with invalid endpoint
    
   @Test12
  Scenario: Check dietician able to retrieve morbidity condition by test name  with invalid method
    Given Dietician create POST request to retrieve all morbidities details with invalid method     
    When Dietician send POST http request morbidity condition by test name  with invalid method endpoint
    Then Dietician recieves 405 method not allowed to retrieve all morbidities details with invalid method
    
  
    @Test13
    Scenario: Check dietician able to retrieve morbidity condition by invalid test name 
    Given Dietician create GET request to retrieve all morbidities details with invalid endpoint     
    When Dietician send GET http request with morbidity condition by invalid test name  endpoint
    Then Dietician recieves 404 not found to retrieve all morbidities details with invalid endpoint
    
    @Test14
  Scenario: Check dietician able to retrieve morbidity condition by test name with invalid endpoint
    Given Dietician create GET request to retrieve all morbidities details with invalid endpoint        
    When Dietician send GET http request morbidity condition by test name with invalid endpoint
    Then Dietician recieves 404 not found to retrieve all morbidities details with invalid endpoint
    
    
    
    
    
