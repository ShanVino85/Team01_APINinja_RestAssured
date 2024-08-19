@tag12
Feature: All Modules Delete and logout Modules Negative

#Patient Delete Negative
Scenario: Check dietician able to delete patient by id with invalid method
Given Dietician create POST request
When Dietician send POST http to delete patient by id request with endpoint
Then Dietician recieves 405 method not allowed

Scenario: Check dietician able to delete patient by invalid id
Given Dietician create DELETE request
When Dietician send DELETE http request with endpoint
Then Dietician recieves 404 not found

Scenario: Check dietician able to delete patient by id with invalid endpoint
Given Dietician create DELETE request
When Dietician send DELETE http request with invalid endpoint
Then Dietician recieves 404 not found

#No Auth
Scenario: Check dietician able to delete patient by ID
Given Dietician create DELETE request
When Dietician send DELETE http request with endpoint
Then Dietician recieves 401 unauthorized

#By admin token
Scenario: Check admin is able to retrieve patients morbidity details by patient ID
Given Admin create GET request
When Admin send GET http request with endpoint
Then Admin recieves 403 forbidden

#By Patient token 
Scenario: Check patient is able to retrieve patients morbidity details by patient ID
Given Patient create GET request
When Patient send GET http request with endpoint
Then Patient recieves 403 forbidden

#Patient Logout Negative
Scenario: Check patient able to logout with invalid method
Given patient creates POST request
When patient send POST HTTP request with endpoint
Then patient recieves 405 method not allowed

#Patient/Noauth
Scenario: Check patient able to logout
Given patient creates GET request
When patient send GET HTTP request with endpoint
Then patient recieves 401 unauthorized

#Dietician Logout Negative 
Scenario: Check dietician able to logout with invalid method
Given dietician creates POST request
When dietician send POST HTTP request with endpoint
Then dietician recieves 405 method not allowed

#Dietician/Noauth
Scenario: Check dietician able to logout
Given dietician creates GET request
When dietician send GET HTTP request with endpoint
Then dietician recieves 401 unauthorized

#Admin Logout Negative 
Scenario: Check admin able to logout with invalid method
Given admin creates POST request
When admin send POST HTTP request with endpoint
Then admin recieves 405 method not allowed

#Admin/Noauth
Scenario: Check admin able to logout
Given admin creates GET request
When admin send GET HTTP request with endpoint
Then admin recieves 401 unauthorized


