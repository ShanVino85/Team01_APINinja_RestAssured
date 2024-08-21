@tag11
Feature: All Modules Delete and logout Modules Positive

  #Patient Delete Positive
  @Test01
  Scenario: Check dietician able to delete patient by ID
    Given Dietician create DELETE request
    When Dietician send DELETE http request with endpoint
    Then Dietician recieves 200 ok with details of the patient id

  #Patient Logout/Dietician Token Positive
  @Test02
  Scenario: Check patient able to logout
    Given patient creates GET request
    When patient send GET HTTP request with endpoint
    Then patient recieves 200 created with Logout successful message
    
     @Positive_05_Delete
  Scenario: Check admin able to delete dietician by ID
    Given Admin create Delete request
    When Admin send Delete "Delete_Dietitian" request with endpoint
    Then Admin recieves 200 afterDelete and with response body

  #Dietician Logout/AdminToken Positive
  @Test03
  Scenario: Check dietician able to logout
    Given dietician creates GET request
    When dietician send GET HTTP request with endpoint
    Then dietician recieves 200 created with Logout successful message

  #AdminLogout/ Admin token Positive
  @Test04
  Scenario: Check admin able to logout
    Given Admin creates GET request
    When Admin send GET HTTP request with endpoint
    Then Admin recieves 200 created with Logout successful message

    
   
    