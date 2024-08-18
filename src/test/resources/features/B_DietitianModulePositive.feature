
@Positive
Feature: Dietitian Positive Flow

  @Positive_01
  Scenario Outline: Check admin able to create dietician with valid data 
    Given Admin creates POST request with valid data "<sheetname>", <rownum>
    When Admin send POST "Post_CreateDietitian" request with endpoint
    Then Admin recieves 201 created and with response body

 Examples:
						|sheetname|rownum|
						|DietitianPost|1|

						
@Positive_02
 Scenario: Check admin able to create dietician with valid data and token
     Given Admin creates POST request and get the token
     When Admin send POST "Post_UserDieticianLoginurl" request with endpoint
  	Then Admin  receives dietician token

