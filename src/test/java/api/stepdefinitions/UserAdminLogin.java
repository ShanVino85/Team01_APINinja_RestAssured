package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.resourses.*;



public class UserAdminLogin extends RestUtils {
	
	UserAdminLogindata UserLogindata= new UserAdminLogindata(); 
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	public static String token;
	
	@Given("User creates Post request with request body")
	public void user_creates_post_request_with_request_body() throws FileNotFoundException, IOException {
		
		request=given().spec(requestSpecification()).body( UserAdminLogindata.dataBuild());
	}

	@When("User send POST HTTP request with endpoint")
	public void user_send_post_http_request_with_endpoint() {
		
		 response = request.when().post(routes.getString("Post_UserAdminLoginurl")).then().log().all().extract().response();
			
			IdHolder.token =  UserKeyJson(response,"token");
			  System.out.println("Token ="  +IdHolder.token);
		  
	}

	@Then("User recieves {int} created with response body")
	public void user_recieves_created_with_response_body(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}



}
