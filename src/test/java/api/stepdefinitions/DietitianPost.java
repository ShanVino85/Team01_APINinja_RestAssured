package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import api.resourses.DietitianPostdata;
import api.resourses.DietitianTokendata;
import api.resourses.UserAdminLogindata;
import api.utils.ExcelReader;
import api.utils.IdHolder;
import api.utils.RestUtils;
import freemarker.template.utility.StringUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DietitianPost extends RestUtils {

	RequestSpecification request;
	RequestSpecification requestToken;
	RequestSpecification reqNegative;
	RequestSpecification reqNegative1;
	RequestSpecification resGet1;
	RequestSpecification requestPut;
	RequestSpecification reqDelete;
	
	public static Response response;
	public static Response responseDietitianToken;
	public static Response responseNegative;
	public static Response responseGet;
	public static Response responsePut;
	public static Response responseDelete;
	
	static Response resbody;
	static Response resbodyToken;
	static Response resbodyNegative;
	
	static ExcelReader er = new ExcelReader();
	static Map<String, String> dietitian= new HashMap();	
	
	static String userId;
	static String FirstName;

	//-----------------------Positive_01_Post (creation of Dietitian)-----------------------------------//
	@Given("Admin creates POST request with valid data {string}, {int}")
	public void admin_creates_post_request_with_valid_data(String sheetname, int rownum) throws FileNotFoundException, IOException {
    
		request=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.token);
	}

	//-----------------------Positive_02_Post (Generating Dietitian Token)--------------------------//
	
	@Given("Admin creates POST request and get the token")
	public void admin_creates_post_request_and_get_the_token() throws FileNotFoundException, IOException {
		requestToken=given().spec(requestSpecification()).body( DietitianTokendata.dataTokenBuild());
	}

	@Then("Admin  receives dietician token")
	public void admin_receives_dietician_token() {
		
		//Response body of Dietitian Token
		resbodyToken=responseDietitianToken.then().log().all().extract().response();
		
		IdHolder.dietitianToken=  UserKeyJson(resbodyToken,"token");
		System.out.println("DietitianToken ="  +IdHolder.dietitianToken);
		
	}
	
	//---------------------------Negative_01_Post(Invalid data)-------------------------//
	
	@Given("Admin creates POST request with invalid additional details {string}, {int}")
	public void admin_creates_post_request_with_invalid_additional_details(String sheetname, int rownum) throws FileNotFoundException, IOException {
	reqNegative=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.token);
	}
	//--------------------------Negative_04_Post(Invalid Content type)-----------------------------//
	
	@Given("Admin creates POST request with valid data and invalid content type {string}, {int}")
	public void admin_creates_post_request_with_valid_data_and_invalid_content_type(String sheetname, int rownum) throws FileNotFoundException, IOException {
	  
		reqNegative1=given().spec(requestSpecificationInvaliddata()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.token);
	}
	
	//--------------------------Negative_05_Post(Create POST request without token)-----------------------------//
	
	@Given("Check admin able to create dietician with valid data with no auth {string}, {int}")
	public void check_admin_able_to_create_dietician_with_valid_data_with_no_auth(String sheetname, int rownum) throws FileNotFoundException, IOException {
		request=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata(sheetname, rownum));				
	}
	
	//--------------------------Negative_06Ppost(Create POST request with dietitian token)-----------------------------//
	
	@Given("Check admin able to create dietician with valid data with dietitian token {string}, {int}")
	public void check_admin_able_to_create_dietician_with_valid_data_with_dietitian_token(String sheetname, int rownum) throws FileNotFoundException, IOException {
		request=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.dietitianToken);
	}
	
	//--------------------------Negative_07_Post(Create POST request with patient token)-----------------------------//
	
	/*
	@Given("Admin creates POST request with valid data with patient token {string}, {int}")
	public void admin_creates_post_request_with_valid_data_with_patient_token(String sheetname, int rownum) throws FileNotFoundException, IOException {
		request=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.patientToken);

	}*/

	
	
	//---------------Sending different endpoints for POST operations----------------//
	
	@When("Admin send POST {string} request with endpoint")
	public void admin_send_post_request_with_endpoint(String endpoint) {

		if(endpoint.equalsIgnoreCase("Post_CreateDietitian"))
		
			{
				if(request!=null) {
				response = request.when().post(routes.getString("Post_CreateDietitian"));
				}
				//---------------Negative_04_post----------------//
				else if(reqNegative1!=null) {
					response = reqNegative1.when().post(routes.getString("Post_CreateDietitian"));
					
				}
			}
		else if(endpoint.equalsIgnoreCase("Post_UserDieticianLoginurl")) {
				
				responseDietitianToken = requestToken.when().post(routes.getString("Post_UserDieticianLoginurl"));
						
				}
		else if(endpoint.equalsIgnoreCase("Post_CreateDietitianInvalidData")) {
			
			responseNegative = reqNegative.when().post(routes.getString("Post_CreateDietitianInvalidData"));
					
			}
		
		//----------------------------Negative_03_Post(Invalid endpoint)----------------------------------//
		
		else if(endpoint.equalsIgnoreCase("Post_CreateDietitianInvalidEndpoint")) {
			
			response = request.when().post(routes.getString("Post_CreateDietitianInvalidEndpoint"));
				
		}
		
	}

		//----------------------------Negative_02_Post(Invalid Request type PUT)----------------------------------//
	
	@When("Admin send PUT {string} request with endpoint")
	public void admin_send_put_request_with_endpoint(String string) {
	response = request.when().put(routes.getString("Post_CreateDietitian"));
	}

	
	
	//-----------------------------Response body validation based on status code_POST Request--------------------------//

		@Then("Admin recieves {int} created and with response body")
		public void admin_recieves_created_and_with_response_body(Integer statusCode) throws IOException {
	   
			
			if(statusCode==201)
			{
				assertEquals(response.getStatusCode(),201);
				
				response.then().assertThat().contentType(ContentType.JSON).body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/DietitianModule/DietitianPostRequest.json"));
				resbody=response.then().log().all().extract().response();
				
				
				IdHolder.dietitianEmail=  UserKeyJson(resbody,"Email");
				IdHolder.dietitianpass= UserKeyJson(resbody,"loginPassword");
				 
				 userId=  UserKeyJson(resbody,"id");
				 FirstName= UserKeyJson(resbody,"Firstname");
				
				dietitian.put(FirstName, userId);
				
				if(FirstName.equalsIgnoreCase("Jojo")) {
					IdHolder.dietitianID=userId;
				}
				else if(FirstName.equalsIgnoreCase("Jhon"))
				{
					IdHolder.dietitianID1=userId;
				}
				
				System.out.println("Email ="  +IdHolder.dietitianEmail);
				System.out.println("DietitianPassword ="  +IdHolder.dietitianpass);
				System.out.println("DietitianID ="  +IdHolder.dietitianID);
				
				
				
				
				//String actualEmail= er.getCellData("DietitianPost", 1, 3);
				//assertEquals(IdHolder.dietitianEmail,actualEmail);
				
			}
			
			else if(statusCode==400)
			{
				resbodyNegative=responseNegative.then().log().all().extract().response();
				assertEquals(responseNegative.getStatusCode(),400);
				System.out.println(UserKeyJson(response,"error"));
				
				if (UserKeyJson(resbodyNegative,"contact")!= null && UserKeyJson(resbodyNegative,"contact").isEmpty() == false)
				{
					//ContactNumber
					String erroMsg=  UserKeyJson(resbodyNegative,"contact");
					System.out.println(erroMsg);
					assertEquals(erroMsg,"Contact number should contain 10 digits");
					
				} 
				
				else if (UserKeyJson(resbodyNegative,"dateOfBirth")!= null && UserKeyJson(resbodyNegative,"dateOfBirth").isEmpty() == false)
				{
					String erroMsg=  UserKeyJson(resbodyNegative,"dateOfBirth");
					
					assertEquals(erroMsg,"Date of Birth is Mandatory!");
				}
				else if (UserKeyJson(resbodyNegative,"dEducation")!= null && UserKeyJson(resbodyNegative,"dEducation").isEmpty() == false)
				{
					//Education
					String erroMsg=  UserKeyJson(resbodyNegative,"dEducation");
					System.out.println(erroMsg);
					assertEquals(erroMsg,"Dietician's education is required!");
					
				} else if (UserKeyJson(resbodyNegative,"userLoginEmail")!= null && UserKeyJson(resbodyNegative,"userLoginEmail").isEmpty() == false)
				{
					String erroMsg=  UserKeyJson(resbodyNegative,"userLoginEmail");
					//assertEquals(erroMsg2,"Invalid Email Id!");
					assertEquals(erroMsg,"Dietician's login email is required!");
				} 
				else if (UserKeyJson(resbodyNegative,"firstName")!= null && UserKeyJson(resbodyNegative,"firstName").isEmpty() == false)
				{
					String erroMsg=  UserKeyJson(resbodyNegative,"firstName");
					assertEquals(erroMsg,"FirstName should only contain Alphabets");
				} 
				else if (UserKeyJson(resbodyNegative,"dHospitalCityName")!= null && UserKeyJson(resbodyNegative,"dHospitalCityName").isEmpty() == false)
				{
					String erroMsg=  UserKeyJson(resbodyNegative,"dHospitalCityName");
					assertEquals(erroMsg,"Dietician's hospital city name is required!");
				}
				else if (UserKeyJson(resbodyNegative,"dHospitalPinCode")!= null && UserKeyJson(resbodyNegative,"dHospitalPinCode").isEmpty() == false)
				{
					String erroMsg=  UserKeyJson(resbodyNegative,"dHospitalPinCode");
					assertEquals(erroMsg,"Dietician's hospital pincode is required!");
				}
				else if (UserKeyJson(resbodyNegative,"dHospitalStreetName")!= null && UserKeyJson(resbodyNegative,"dHospitalStreetName").isEmpty() == false)
				{
					String erroMsg=  UserKeyJson(resbodyNegative,"dHospitalStreetName");
					assertEquals(erroMsg,"Dietician's hospital street name is required!");
				}
				
				
			 }
			else if(statusCode==405)
			{
				assertEquals(response.getStatusCode(),405);
				System.out.println(UserKeyJson(response,"error"));
			}
			else if(statusCode==404)
			{
				assertEquals(response.getStatusCode(),404);
				System.out.println(UserKeyJson(response,"error"));
			}
			else if(statusCode==415)
			{
				assertEquals(response.getStatusCode(),415);
				System.out.println(UserKeyJson(response,"error"));
			}
			else if(statusCode==401)
			{
				assertEquals(response.getStatusCode(),401);
				System.out.println(UserKeyJson(response,"error"));
			}
			else if(statusCode==403)
			{
				assertEquals(response.getStatusCode(),403);
				System.out.println(UserKeyJson(response,"errorMessage"));
			}
			
			}
	
	//----------------------------Positive_03_Get(Get dietitian)----------------------------------//
	@Given("Admin create GET request")
	public void admin_create_get_request() throws FileNotFoundException {
		resGet1=given().log().all().spec(requestSpecification()).header("Authorization","Bearer "+ IdHolder.token);
		
	}
	
	//----------------------------Negative 12,13_Get(Get dietitian)----------------------------------//
	@Given("Admin create GET request with no auth")
	public void admin_create_get_request_with_no_auth() throws FileNotFoundException {
		resGet1=given().log().all().spec(requestSpecification());
	}

		
	//---------------Sending different endpoints for GET operations----------------//
	
	@When("Admin send GET {string} request with endpoint")
	public void admin_send_get_request_with_endpoint(String endpoint) {
		
		if(endpoint.equalsIgnoreCase("Get_AllDietitian")) {
			
			responseGet=resGet1.when().get(routes.getString("Get_AllDietitian"));
		}
		else if (endpoint.equalsIgnoreCase("Get_AllDietitianByID")) {
			
			responseGet=resGet1.when().get(routes.getString("Get_AllDietitian")+ "/" + IdHolder.dietitianID);
		}
		
		
	}
	
	//----------------------------Negative_08 to 11 (Get dietitian)----------------------------------//
	
	@When("Admin send PUT {string} request with GetRequest endpoint")
	public void admin_send_put_request_with_get_request_endpoint(String string) {
		responseGet=resGet1.when().put(routes.getString("Get_AllDietitian"));
	}
	
	@When("Admin send POST {string} request with GetRequest endpoint")
	public void admin_send_post_request_with_get_request_endpoint(String string) {
		responseGet=resGet1.when().post(routes.getString("Get_AllDietitian")+ "/" + IdHolder.dietitianID);
	}
	
	@When("Admin send GET {string} request with {string}")
	public void admin_send_get_request_with(String endpoint, String Id) {
		responseGet=resGet1.when().get(routes.getString("Get_AllDietitian")+"/"+ Id);
	}
	
	@When("Admin send GET {string} request with invalid endpoint")
	public void admin_send_get_request_with_invalid_endpoint(String string) {
		responseGet=resGet1.when().get(routes.getString("Get_ALLDietitianInvalidEndpoint")+"/"+ IdHolder.dietitianID);

	}
	
   
	
	 //-----------------------------Response body validation based on status code_GET Request--------------------------//

	
	@Then("Admin recieves {int} afterGet and with response body")
	public void admin_recieves_after_get_and_with_response_body(Integer statusCode) {
	 if(statusCode==200) 
		{
			assertEquals(responseGet.getStatusCode(),200);
			
		}
	 else if(statusCode==404) {
		 assertEquals(responseGet.getStatusCode(),404);
		 System.out.println(UserKeyJson(response,"error"));
	 }
	 else if(statusCode==415)
		{
			assertEquals(responseGet.getStatusCode(),415);
			System.out.println(UserKeyJson(response,"error"));
		}
	 else if(statusCode==405)
		{
			assertEquals(responseGet.getStatusCode(),405);
			System.out.println(UserKeyJson(response,"error"));
		}
	 else if(statusCode==401)
		{
			assertEquals(responseGet.getStatusCode(),401);
			System.out.println(UserKeyJson(response,"error"));
		}
	}

//----------------------------------PUT--------------------------------------------//
	
	@Given("Admin creates PUT request with valid data {string}, {int}")
	public void admin_creates_put_request_with_valid_data(String sheetname, int rownum) throws FileNotFoundException, IOException {
	    
		requestPut=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.token);
	}
	
	
	@When("Admin send valid PUT {string} request with endpoint")
	public void admin_send_valid_put_request_with_endpoint(String endpoint) {
	   
		responsePut= requestPut.when().put(routes.getString("Put_Dietitian")+"/"+ IdHolder.dietitianID1);
					
		}
	
	//-----------------------------Negative-----------------------------//
	@When("Admin send valid PUT {string} request with {string}")
	public void admin_send_valid_put_request_with(String Put_Dietitian, String ID) {
		responsePut= requestPut.when().put(routes.getString("Put_Dietitian")+"/"+ ID);
		
	}
	
	@When("Admin send valid POST {string} request with")
	public void admin_send_valid_post_request_with(String string) {
		responsePut= requestPut.when().post(routes.getString("Put_Dietitian")+"/"+ IdHolder.dietitianID1);
		
	}
	@When("Admin send valid PUT {string} request with")
	public void admin_send_valid_put_request_with(String string) {
	    
		responsePut= requestPut.when().put(routes.getString("Put_DietitianInvalidEndpoint")+"/"+ IdHolder.dietitianID1);
		
	}


	 //-----------------------------Response body validation based on status code_PUT Request--------------------------//

	@Then("Admin recieves {int} afterPost and with response body")
	public void admin_recieves_after_post_and_with_response_body(Integer statusCode) {
		 if(statusCode==200) 
			{
				assertEquals(responsePut.getStatusCode(),200);
				
			}
		 else if(statusCode==404) {
			 assertEquals(responsePut.getStatusCode(),404);
			 System.out.println(UserKeyJson(responsePut,"error"));
		 }
		 else if(statusCode==415)
			{
				assertEquals(responsePut.getStatusCode(),415);
				System.out.println(UserKeyJson(responsePut,"error"));
			}
		 else if(statusCode==405)
			{
				assertEquals(responsePut.getStatusCode(),405);
				System.out.println(UserKeyJson(responsePut,"error"));
			}
		 else if(statusCode==401)
			{
				assertEquals(responsePut.getStatusCode(),401);
				System.out.println(UserKeyJson(responsePut,"error"));
			}
		}

	
	
	//----------------------------------Delete--------------------------------------------//
	
	
	@Given("Admin create Delete request")
	public void admin_create_delete_request() throws FileNotFoundException {
		reqDelete=given().log().all().spec(requestSpecification()).header("Authorization","Bearer "+ IdHolder.token);
		
	}
	
	
	@When("Admin send Delete {string} request with endpoint")
	public void admin_send_delete_request_with_endpoint(String string) {
		responseDelete=reqDelete.when().delete(routes.getString("Delete_Dietitian")+"/"+ IdHolder.dietitianID1);

	}
	
	//-----------------------------Negative-----------------------------//

	@Given("Admin create Delete request with no auth")
	public void admin_create_delete_request_with_no_auth() throws FileNotFoundException {
		reqDelete=given().log().all().spec(requestSpecification());
	}
	
	@When("Admin send POST {string} request with endpoint with DeleteRequest endpoint")
	public void admin_send_post_request_with_endpoint_with_delete_request_endpoint(String string) {
		responseDelete=reqDelete.when().post(routes.getString("Delete_Dietitian")+"/"+ IdHolder.dietitianID1);

	}
	
	@When("Admin send Delete {string} request with {string}")
	public void admin_send_delete_request_with(String string, String Id) {
		responseDelete=reqDelete.when().delete(routes.getString("Delete_Dietitian")+"/"+ Id);

	}
	
	@When("Admin send POST {string} request with Invalidendpoint")
	public void admin_send_post_request_with_invalidendpoint(String string) {
		responseDelete=reqDelete.when().delete(routes.getString("Delete_DietitianInvalidEndpoint")+"/"+ IdHolder.dietitianID1);
	}
	
	
	 //-----------------------------Response body validation based on status code_Delete Request--------------------------//

	@Then("Admin recieves {int} afterDelete and with response body")
	public void admin_recieves_after_delete_and_with_response_body(Integer statusCode) {
		 if(statusCode==200) 
			{
				assertEquals(responseDelete.getStatusCode(),200);
				
			}
		 
		 else if(statusCode==404) {
			 assertEquals(responseDelete.getStatusCode(),404);
			 System.out.println(UserKeyJson(responseDelete,"error"));
		 }
		 else if(statusCode==415)
			{
				assertEquals(responseDelete.getStatusCode(),415);
				System.out.println(UserKeyJson(responseDelete,"error"));
			}
		 else if(statusCode==405)
			{
				assertEquals(responseDelete.getStatusCode(),405);
				System.out.println(UserKeyJson(responseDelete,"error"));
			}
		 else if(statusCode==401)
			{
				assertEquals(responseDelete.getStatusCode(),401);
				System.out.println(UserKeyJson(responseDelete,"error"));
			}
	}
}
