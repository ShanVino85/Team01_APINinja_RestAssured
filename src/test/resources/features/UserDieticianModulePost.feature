
Feature: POST (create dietician )
  
 
  Background:
  Given User creates Post request with request body
    When User send POST HTTP request with endpoint	
    Then User recieves 200 created with response body
  @tagdietician  
 Scenario: Check admin able to create dietician with valid data and token 
    Given Admin creates POST request for Dietician with valid data. ( Mandatory and additional details)  
    When Admin send POST http request with endpoint
    Then Admin recieves 201 created and with response body. (Auto created dietician ID and login password)
  
  Scenario: Check admin able to retrieve Token for Dietician
    Given User creates Post request with request body for Dietician token
    When User send POST HTTP request with endpoint for Dietician token
    Then User recieves 200 created with response body for Dietician token
    
 Scenario: Check admin able to retrieve all dietician
    Given Admin create GET request
    When Admin send GET http request with endpoint
    Then Admin recieves 200 ok with response body
    
 Scenario: Check admin able to retrieve dietician by ID
    Given Given Admin create GET request by the stored dietician Id
    When Admin send GET http request with endpoint by Id
    Then Admin recieves 200 ok with details of the dietician id
    
 Scenario: Check admin able to delete dietician by ID
    Given Admin create DELETE request 
    When Admin send DELETE http request with endpoint
    Then Admin recieves 200 ok with details of the dietician id by deleting it
    
 Scenario: Check admin able to update dietician with valid data , dietician id and token 
    Given Admin creates PUT request with valid data. ( Mandatory and additional details)
    When Admin send PUT http request with endpoint
    Then Admin recieves 200 ok and with updated response body. 
    

 
   
