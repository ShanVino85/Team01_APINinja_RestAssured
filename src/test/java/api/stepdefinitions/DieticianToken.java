package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import api.resourses.UserAdminLogindata;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DieticianToken extends RestUtils{
	
	
		
		
		RequestSpecification request;
		ResponseSpecification responseSpec;
		Response response;
		public static String token;
		
		@Given("User creates Post request with dietician data request body")
		public void user_creates_post_request_with_dietician_data_request_body() throws FileNotFoundException, IOException {
			String dieticianData = "{\n" +
	                "  \"password\": \"" + PostDietician.password + "\",\n" +
	                "  \"userLoginEmail\": \"" + PostDietician.emailId + "\"\n" +
	                "}";

	        request = given().spec(requestSpecification()).body(dieticianData);
		}

		@When("User send POST HTTP request for Dietician token with endpoint")
		public void user_send_post_http_request_for_with_Dietician_token_with_endpoint() {
			
			 response = request.when().post(routes.getString("Post_UserAdminLoginurl")).then().log().all().extract().response();
				
			 IdHolder.Dieticiantoken =  UserKeyJson(response,"token");
			  System.out.println("Dieticiantoken ="  +IdHolder.Dieticiantoken);
		  
			  
		}

		@Then("User recieves {int} created with response body with Dietician token")
		public void user_recieves_created_with_response_body_with_Dietician_token(Integer int1) {
			assertEquals(response.getStatusCode(),200);
		}



	}



