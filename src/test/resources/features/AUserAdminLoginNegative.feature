@tag01
Feature: User Admin Post Request Negative

 
#Negative
@Test01
Scenario: Check user able to login as admin with invalid credential
Given User creates Post request with invalid credential
When User send POST HTTP request with invalid credential and endpoint
Then User recieves 401 unauthorized

@Test02
Scenario: Check user able to login as admin with valid credential and invalid method
Given User creates GET request with request body.
When User send GET HTTP request with endpoint
Then User recieves 405 method not allowed

@Test03
Scenario: Check user able to login as admin with valid credential and invalid endpoint
Given User creates Post request with request body
When User send POST HTTP request with invalid endpoint
Then User recieves 401 unauthorized

@Test04
Scenario: Check user able to login as admin with valid credential and invalid content type
Given User creates Post request with request body and invalid content type.
When User send POST HTTP request with invalid content type endpoint
Then User recieves 415 unsupported media type
