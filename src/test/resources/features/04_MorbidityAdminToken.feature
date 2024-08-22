@tag04
Feature: Morbidity Module Positive with Admin Token

  #Morbidity Module using Admin token
  @Test01
  Scenario: Check admin able to retrieve all morbidities details with admin token
    Given admin create GET request with all morbidities details admin token
    When admin send GET http request with all morbidities details endpoint
    Then admin recieves 200 ok with all morbidities details patient id

  @Test02
  Scenario: Check admin able to retrieve morbidity condition by test name with admin token
    Given admin create GET request with morbidity condition by test name with admin token
    When admin send GET http request with morbidity condition by test name endpoint
    Then admin recieves 200 ok with morbidity condition by test name endpoint details of the patient id
