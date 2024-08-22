@tag10
Feature: All Modules Delete and logout Modules Negative

  #Patient Delete Negative
  @Test01
  Scenario: Check dietician able to delete patient by id with invalid method
    Given Dietician create POST request
    When Dietician send POST http to delete patient by id request with endpoint
    Then Dietici recieves 405 method not allowed

  @Test02
  Scenario: Check dietician able to delete patient by invalid id
    Given Dietician create DELETE request by invalid id
    When Dietician send DELETE http request with endpoint by invalid id
    Then Dietici recieves 401 unauthorized

  @Test03
  Scenario: Check dietician able to delete patient by id with invalid endpoint
    Given Dietician create DELETE request
    When Dietician send DELETE http request with invalid endpoint
    Then Dietici recieves 404 not found

  #No Auth
  @Test04
  Scenario: Check dietician able to delete patient by ID
    Given Dietician create DELETE request with no auth
    When Dietician send DELETE http request with endpoint
    Then Dietici recieves 401 unauthorized

  #Patient Logout Negative
  @Test05
  Scenario: Check patient able to logout with invalid method
    Given patient creates POST request
    When patient send POST HTTP request with endpoint
    Then patient recieves 405 method not allowed

  #Patient/Noauth
  @Test06
  Scenario: Check patient able to logout
    Given patient creates GET request with noauth
    When patient send GET HTTP request with endpoint
    Then patient recieves 401 unauthorized

  #Dietician Negative
  @Negative_07_Delete
  Scenario: Check admin able to delete dietician by ID
    Given Admin create Delete request with no auth
    When Admin send Delete "Delete_Dietitian" request with endpoint
    Then Admin recieves 401 afterDelete and with response body

  @Negative_08_Delete
  Scenario: Check admin able to delete dietician by id with invalid method
    Given Admin create Delete request
    When Admin send POST "Delete_Dietitian" request with endpoint with DeleteRequest endpoint
    Then Admin recieves 405 afterDelete and with response body

  @Negative_09_Delete
  Scenario Outline: Check admin able to delete dietician by invalid id
    Given Admin create Delete request
    When Admin send Delete "Delete_Dietitian" request with "<invalidID>"
    Then Admin recieves 404 afterDelete and with response body

    Examples: 
      | invalidID |
      | bjbj8     |

  @Negative_10_Delete
  Scenario: Check admin able to delete dietician by id with invalid endpoint
    Given Admin create Delete request
    When Admin send POST "Delete_DietitianInvalidEndpoint" request with Invalidendpoint
    Then Admin recieves 404 afterDelete and with response body

  #Dietician Logout Negative
  @Test11
  Scenario: Check dietician able to logout with invalid method
    Given dietician creates POST request
    When dietician send POST HTTP request with endpoint
    Then dietician recieves 405 method not allowed

  #Dietician/Noauth
  @Test12
  Scenario: Check dietician able to logout
    Given dietician creates GET request with no auth
    When dietician send GET HTTP request with endpoint
    Then dietician recieves 401 unauthorized

  #Admin Logout Negative
  @Test13
  Scenario: Check admin able to logout with invalid method
    Given admin creates POST request
    When admin send POST HTTP request with endpoint
    Then admin recieves 405 method not allowed

  #Admin/Noauth
  @Test14
  Scenario: Check admin able to logout
    Given admin creates GET request with noauth
    When admin send GET HTTP request with noauth endpoint
    Then admin recieves 401 unauthorized
