@Negaive
Feature: Dietitian Negative Flow

  
  @Negative_01_Post
  Scenario Outline: Check admin able to create dietician with invalid data
  Given Admin creates POST request with invalid additional details "<sheetname>", <rownum>
  When Admin send POST "Post_CreateDietitianInvalidData" request with endpoint
  Then Admin recieves 400 created and with response body
  
  Examples:
  						|sheetname|rownum|
  		  			|DietitianPost|3|
  						|DietitianPost|4|
  						|DietitianPost|5|
  						|DietitianPost|6|
  						|DietitianPost|7|
  						|DietitianPost|8|
  
  
  	@Negative_02_Post
  Scenario Outline: Check admin able to create dietician with valid data
  Given Admin creates POST request with valid data "<sheetname>", <rownum>
  When Admin send PUT "Post_CreateDietitian" request with endpoint
  Then Admin recieves 405 created and with response body
  
  Examples:
  						|sheetname|rownum|
  						|DietitianPost|9|
  
  
  @Negative_03_Post
  Scenario Outline: Check admin able to create dietician with valid data
  Given Admin creates POST request with valid data "<sheetname>", <rownum>
  When Admin send POST "Post_CreateDietitianInvalidEndpoint" request with endpoint
  Then Admin recieves 404 created and with response body
  
  Examples:
  						|sheetname|rownum|
  						|DietitianPost|10|
  #
  #	@Negative_04_Post
  #Scenario Outline: Check admin able to create dietician with valid data
  #Given Admin creates POST request with valid data and invalid content type "<sheetname>", <rownum>
  #When Admin send POST "Post_CreateDietitian" request with endpoint
  #Then Admin recieves 415 created and with response body
  #
  #Examples:
  #						|sheetname|rownum|
  #						|DietitianPost|11|
 
  @Negative_05_Post
  Scenario Outline: Check admin able to create dietician with valid data
  Given Check admin able to create dietician with valid data with no auth "<sheetname>", <rownum>
  When Admin send POST "Post_CreateDietitian" request with endpoint
  Then Admin recieves 401 created and with response body
  
  Examples:
  | sheetname     | rownum |
  | DietitianPost |     12 |
 
  @Negative_06_Post
  Scenario Outline: Check admin able to create dietician with valid data and dietician token
  Given Check admin able to create dietician with valid data with dietitian token "<sheetname>", <rownum>
  When Admin send POST "Post_CreateDietitian" request with endpoint
  Then Admin recieves 403 created and with response body
  
  Examples:
  | sheetname     | rownum |
  | DietitianPost |     12 |
  
  #@Negative_07
  #Scenario Outline: Check admin able to create dietician with valid data and patient token
  #Given Admin creates POST request with valid data with patient token "<sheetname>", <rownum>
  #When Admin send POST "Post_CreateDietitian" request with endpoint
  #Then Admin recieves 403 created and with response body
  #
  #Examples:
  #| sheetname     | rownum |
  #| DietitianPost |     12 |
  
  
  @Negative_08_GET
  Scenario: Check admin able to retrieve all dietician with invalid method
    Given Admin create GET request
    When Admin send PUT "Get_AllDietitian" request with GetRequest endpoint
    Then Admin recieves 405 afterGet and with response body

  @Negative_09_GET
  Scenario: Check admin able to retrieve dietician by id with invalid method
    Given Admin create GET request
    When Admin send POST "Get_AllDietitianByID" request with GetRequest endpoint
    Then Admin recieves 405 afterGet and with response body

  @Negative_10_GET
  Scenario Outline: Check admin able to retrieve dietician by invalid id
    Given Admin create GET request
    When Admin send GET "Get_AllDietitianByID" request with "<invalidID>"
    Then Admin recieves 404 afterGet and with response body

    Examples: 
      | invalidID |
      | r4567     |

  @Negative_11_GET
  Scenario: Check admin able to retrieve dietician by id with invalid endpoint
    Given Admin create GET request
    When Admin send GET "Get_ALLDietitianInvalidEndpoint" request with invalid endpoint
    Then Admin recieves 404 afterGet and with response body
   
   @Negative_12_GET  
  Scenario: Check admin able to retrieve all dietician with no auth
    Given Admin create GET request with no auth
    When Admin send GET "Get_AllDietitian" request with endpoint
    Then Admin recieves 401 afterGet and with response body

   @Negative_13_GET
  Scenario: Check admin able to retrieve dietician by ID with no auth
    Given Admin create GET request with no auth
    When Admin send GET "Get_AllDietitianByID" request with endpoint
    Then Admin recieves 401 afterGet and with response body
    
