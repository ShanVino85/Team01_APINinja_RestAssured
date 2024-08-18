@Negaive
Feature: Dietitian Negative Flow

  @Negative_01
  Scenario Outline: Check admin able to create dietician with invalid data
    Given Admin creates POST request with invalid additional details "<sheetname>", <rownum>
    When Admin send POST "Post_CreateDietitianInvalidData" request with endpoint
    Then Admin recieves 400 created and with response body

    Examples:
						|sheetname|rownum|
		  			|DietitianPost|2|
						|DietitianPost|3|
						|DietitianPost|4|
						|DietitianPost|5|
						|DietitianPost|6|
						|DietitianPost|7|
						

	@Negative_02
  Scenario Outline: Check admin able to create dietician with valid data 
    Given Admin creates POST request with valid data "<sheetname>", <rownum>
    When Admin send PUT "Post_CreateDietitian" request with endpoint
    Then Admin recieves 405 created and with response body

 Examples:
						|sheetname|rownum|
						|DietitianPost|8|
    
    
    @Negative_03
  Scenario Outline: Check admin able to create dietician with valid data 
    Given Admin creates POST request with valid data "<sheetname>", <rownum>
    When Admin send POST "Post_CreateDietitianInvalidEndpoint" request with endpoint
    Then Admin recieves 404 created and with response body

 Examples:
						|sheetname|rownum|
						|DietitianPost|9|
						
	@Negative_04
  Scenario Outline: Check admin able to create dietician with valid data 
    Given Admin creates POST request with valid data and invalid content type "<sheetname>", <rownum>
    When Admin send POST "Post_CreateDietitian" request with endpoint
    Then Admin recieves 415 created and with response body

 Examples:
						|sheetname|rownum|
						|DietitianPost|10|

    
    
    
    
    