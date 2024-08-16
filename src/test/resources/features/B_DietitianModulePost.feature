
@POST
Feature: Dietitian POST

  @Positive
  Scenario: Check admin able to create dietician with valid data 
    Given Admin creates POST request with valid data
    When Admin send POST "Post_CreateDietitian" request with endpoint
    Then Admin recieves 201 created and with response body

 Scenario: Check admin able to create dietician with valid data and token
     Given Admin creates POST request and get the token
     When Admin send POST "Post_UserAdminLoginurl" request with endpoint
  