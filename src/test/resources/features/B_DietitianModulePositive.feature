@Positive
Feature: Dietitian Positive Flow

  @Positive_01_Post
  Scenario Outline: Check admin able to create dietician with valid data
    Given Admin creates POST request with valid data "<sheetname>", <rownum>
    When Admin send POST "Post_CreateDietitian" request with endpoint
    Then Admin recieves 201 created and with response body

    Examples: 
      | sheetname     | rownum |
      | DietitianPost |      1 |
      | DietitianPost |      2 |

  @Positive_02_Post
  Scenario: Check admin able to create dietician with valid data and token
    Given Admin creates POST request and get the token
    When Admin send POST "Post_UserDieticianLoginurl" request with endpoint
    Then Admin  receives dietician token
#
  #@Positive_03_Get
  #Scenario: Check admin able to retrieve all dietician
    #Given Admin create GET request
    #When Admin send GET "Get_AllDietitian" request with endpoint
    #Then Admin recieves 200 afterGet and with response body
#
  #@Positive_04_Get
  #Scenario: Check admin able to retrieve dietician by ID
    #Given Admin create GET request
    #When Admin send GET "Get_AllDietitianByID" request with endpoint
    #Then Admin recieves 200 afterGet and with response body
    #
    #
  @Positive_05_Put
  Scenario Outline: Check admin able to update dietician with valid data , dietician id and token
    Given Admin creates PUT request with valid data "<sheetname>", <rownum>
    When Admin send valid PUT "Put_Dietitian" request with endpoint
    Then Admin recieves 200 afterPost and with response body

    Examples: 
      | sheetname     | rownum |
      | DietitianPut |      1 |  
    
    
  @Positive_05_Delete
  Scenario: Check admin able to delete dietician by ID
    Given Admin create Delete request
    When Admin send Delete "Delete_Dietitian" request with endpoint
    Then Admin recieves 200 afterDelete and with response body
    
    
    
    
