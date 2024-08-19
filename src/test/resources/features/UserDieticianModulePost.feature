
Feature: POST (create dietician )
  
 
  Background:
  Given User creates Post request with request body
    When User send POST HTTP request with endpoint	
    Then User recieves 200 created with response body
 
 #Get by Id Positive
 Scenario: Check admin able to retrieve dietician by ID
  Given Given Admin create GET request by the stored dietician Id
  When Admin send GET http request with endpoint by Id
  Then Admin recieves 200 ok with details of the dietician id

#Get by Id Negative 
#405
	Scenario: Check admin able to retrieve dietician by id with invalid method
	 Given Admin create POST request by the stored dietician Id for negative
 	 When Admin send Post http request with endpoint by Id for negative
 	 Then Admin recieves 405 method not allowed
#404 	 
 	#Scenario: Check admin able to retrieve dietician by invalid id
	 #Given Admin create Get request by the stored dietician Id for negative by invalid id
 	 #When Admin send Post http request with endpoint by Id for negative by invalid id
 	 #Then Admin recieves 404 method not allowed by invalid id
#404 
 	#Scenario: Check admin able to retrieve dietician by endpoint
	 #Given Admin create Get request by the stored dietician Id for negative by endpoint
 	 #When Admin send Post http request with endpoint by Id for negative by endpoint
 	 #Then Admin recieves 404 method not allowed by endpoint
 	  
 	 

 #Put By Id  Positive
 Scenario: Admin updates dietitian information
   Given Admin creates PUT request with valid data "DieticianPUT",3
   When Admin sends PUT "Put_UpdateDietitian" HTTP request with endpoint
   Then Admin receives 200 OK and with updated response body for dietitian
   
   #Delete By Id  Positive
# Scenario: Check admin able to delete dietician by ID
 #   Given Admin create DELETE request 
  #	When Admin send DELETE http request with endpoint
   # Then Admin recieves 200 ok with details of the dietician id by deleting it
    
 #Delete By Id  Negative
 
 
 # Scenario: Check admin able to delete dietician by id with invalid method
  #  Given Admin create POST request for delete Dietician
  	#When Admin send DELETE http request with endpoint for delete Dietician
    #Then Admin recieves 405 method not allowed for delete Dietician
 
# Scenario: Check admin able to delete dietician by invalid id
 #   Given Admin create DELETE request for delete Dietician by invalid Id
  #	When Admin send DELETE http request with endpoint for delete Dietician by invalid Id
   # Then Admin recieves 404 method not allowed for delete Dietician by invalid Id
 
 #Scenario: Check admin able to delete dietician by id with invalid endpoint
  #  Given Admin create DELETE request for delete Dietician by invalid endpoint
  	#When Admin send DELETE http request with invalid endpoint by invalid endpoint
    #Then Admin recieves 404 method not allowed for delete Dietician by invalid endpoint
 
 
 
 