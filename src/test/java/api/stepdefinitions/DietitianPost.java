package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import api.resourses.DietitianPostdata;
import api.resourses.DietitianTokendata;
import api.resourses.UserAdminLogindata;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DietitianPost extends RestUtils {

	RequestSpecification request;
	RequestSpecification requestToken;
	public static Response response;
	static Response resbody;
	
	@Given("Admin creates POST request with valid data")
	public void admin_creates_post_request_with_valid_data() throws IOException {
	    
		request=given().spec(requestSpecification()).body( DietitianPostdata.createdietitiandata())
				.header("Authorization","Bearer "+ IdHolder.token);
	}

	@Given("Admin creates POST request and get the token")
	public void admin_creates_post_request_and_get_the_token() throws FileNotFoundException, IOException {
		requestToken=given().spec(requestSpecification()).body( DietitianTokendata.dataTokenBuild());
	}

	// Post Request
	@When("Admin send POST {string} request with endpoint")
	public void admin_send_post_request_with_endpoint(String endpoint) {

		if(endpoint.equalsIgnoreCase("Post_CreateDietitian"))
		
			{
				response = request.when().post(routes.getString("Post_CreateDietitian"));
			
			}
		else if(endpoint.equalsIgnoreCase("Post_UserAdminLoginurl")) {
				
				response = requestToken.when().post(routes.getString("Post_UserAdminLoginurl"));
				IdHolder.dietitianToken=  UserKeyJson(response,"token");
				System.out.println("DietitianToken ="  +IdHolder.dietitianToken);
				
				
			}
			
			
	 
	}

	// Response body validation based on status code
	
	@Then("Admin recieves {int} created and with response body")
	public void admin_recieves_created_and_with_response_body(Integer status) {
   
		resbody=response.then().log().all().extract().response();
		
		if(response.getStatusCode()==201)
		{
			assertEquals(response.getStatusCode(),201);
			IdHolder.dietitianEmail=  UserKeyJson(resbody,"Email");
			//IdHolder.dietitianID= UserKeyJson(response,"id");
			IdHolder.dietitianpass= UserKeyJson(resbody,"loginPassword");
			
			System.out.println("Email ="  +IdHolder.dietitianEmail);
			System.out.println("DietitianPassword ="  +IdHolder.dietitianpass);
			
			
			
		}
	}

}
