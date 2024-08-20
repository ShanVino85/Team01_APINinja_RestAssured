@tag12
Feature: All Modules Delete and logout Modules Negative

  #Patient Delete Negative
  @Test60
  Scenario: Check dietician able to delete patient by id with invalid method
    Given Dietician create POST request
    When Dietician send POST http to delete patient by id request with endpoint
    Then Dietici recieves 405 method not allowed

  @Test61
  Scenario: Check dietician able to delete patient by invalid id
    Given Dietician create DELETE request by invalid id
    When Dietician send DELETE http request with endpoint by invalid id
    Then Dietici recieves 401 unauthorized

  @Test62
  Scenario: Check dietician able to delete patient by id with invalid endpoint
    Given Dietician create DELETE request
    When Dietician send DELETE http request with invalid endpoint
    Then Dietici recieves 404 not found

  #No Auth
  @Test63
  Scenario: Check dietician able to delete patient by ID
    Given Dietician create DELETE request with no auth
    When Dietician send DELETE http request with endpoint
    Then Dietici recieves 401 unauthorized

  #Patient Logout Negative
  @Test66
  Scenario: Check patient able to logout with invalid method
    Given patient creates POST request
    When patient send POST HTTP request with endpoint
    Then patient recieves 405 method not allowed

  #Patient/Noauth
  @Test67
  Scenario: Check patient able to logout
    Given patient creates GET request with noauth
    When patient send GET HTTP request with endpoint
    Then patient recieves 401 unauthorized

  #Dietician Logout Negative
  @Test68
  Scenario: Check dietician able to logout with invalid method
    Given dietician creates POST request
    When dietician send POST HTTP request with endpoint
    Then dietician recieves 405 method not allowed

  #Dietician/Noauth
  @Test69
  Scenario: Check dietician able to logout
    Given dietician creates GET request with no auth
    When dietician send GET HTTP request with endpoint
    Then dietician recieves 401 unauthorized

  #Admin Logout Negative
  @Test70
  Scenario: Check admin able to logout with invalid method
    Given admin creates POST request
    When admin send POST HTTP request with endpoint
    Then admin recieves 405 method not allowed

  #Admin/Noauth
  @Test71
  Scenario: Check admin able to logout
    Given admin creates GET request with noauth
    When admin send GET HTTP request with noauth endpoint
    Then admin recieves 401 unauthorized
