package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

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
	public static Response response;
	public static Response responseDietitianToken;
	public static Response responseNegative;

	static Response resbody;
	static Response resbodyToken;
	static Response resbodyNegative;
	
	static ExcelReader er = new ExcelReader();

//Positive_01
	@Given("Admin creates POST request with valid data {string}, {int}")
	public void admin_creates_post_request_with_valid_data(String sheetname, int rownum) throws FileNotFoundException, IOException {
    
		request=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.token);
	}

//Positive_02
	@Given("Admin creates POST request and get the token")
	public void admin_creates_post_request_and_get_the_token() throws FileNotFoundException, IOException {
		requestToken=given().spec(requestSpecification()).body( DietitianTokendata.dataTokenBuild());
	}

//Negative_01
	
	@Given("Admin creates POST request with invalid additional details {string}, {int}")
	public void admin_creates_post_request_with_invalid_additional_details(String sheetname, int rownum) throws FileNotFoundException, IOException {
	reqNegative=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.token);
	}
//Negative_04
	@Given("Admin creates POST request with valid data and invalid content type {string}, {int}")
	public void admin_creates_post_request_with_valid_data_and_invalid_content_type(String sheetname, int rownum) throws FileNotFoundException, IOException {
	  
		reqNegative1=given().spec(requestSpecificationInvaliddata()).body( DietitianPostdata.createdietitiandata(sheetname, rownum))
				.header("Authorization","Bearer "+ IdHolder.token);
	}
	
	
	@When("Admin send POST {string} request with endpoint")
	public void admin_send_post_request_with_endpoint(String endpoint) {

		if(endpoint.equalsIgnoreCase("Post_CreateDietitian"))
		
			{
				if(request!=null) {
				response = request.when().post(routes.getString("Post_CreateDietitian"));
				}
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
		else if(endpoint.equalsIgnoreCase("Post_CreateDietitianInvalidEndpoint")) {
			
			response = request.when().post(routes.getString("Post_CreateDietitianInvalidEndpoint"));
				
		}
		
	}

	@When("Admin send PUT {string} request with endpoint")
	public void admin_send_put_request_with_endpoint(String string) {
	response = request.when().put(routes.getString("Post_CreateDietitian"));
	}
	
	// Response body validation based on status code
	
	@Then("Admin recieves {int} created and with response body")
	public void admin_recieves_created_and_with_response_body(Integer statusCode) throws IOException {
   
		
		if(statusCode==201)
		{
			assertEquals(response.getStatusCode(),201);
			
			response.then().assertThat().contentType(ContentType.JSON).body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/DietitianModule/DietitianPostRequest.json"));
			resbody=response.then().log().all().extract().response();
			
			
			IdHolder.dietitianEmail=  UserKeyJson(resbody,"Email");
			IdHolder.dietitianpass= UserKeyJson(resbody,"loginPassword");
			
			System.out.println("Email ="  +IdHolder.dietitianEmail);
			System.out.println("DietitianPassword ="  +IdHolder.dietitianpass);
			
			String actualEmail= er.getCellData("DietitianPost", 1, 3);
			assertEquals(IdHolder.dietitianEmail,actualEmail);
			
		}
		else if(statusCode==400)
		{
			resbodyNegative=responseNegative.then().log().all().extract().response();
			assertEquals(responseNegative.getStatusCode(),400);
			
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
		}
		else if(statusCode==404)
		{
			assertEquals(response.getStatusCode(),404);
		}
		else if(statusCode==415)
		{
			assertEquals(response.getStatusCode(),415);
		}
			
		}
	
		


	
	@Then("Admin  receives dietician token")
	public void admin_receives_dietician_token() {
		
		//Response body of Dietitian Token
		resbodyToken=responseDietitianToken.then().log().all().extract().response();
		
		IdHolder.dietitianToken=  UserKeyJson(resbodyToken,"token");
		System.out.println("DietitianToken ="  +IdHolder.dietitianToken);
		
	}



}
