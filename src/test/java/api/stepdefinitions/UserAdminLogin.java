package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.resourses.*;



public class UserAdminLogin extends RestUtils {
	
	Logger logger = LogManager.getLogger("UserAdminLogin.java");
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
			
			IdHolder.Admintoken =  UserKeyJson(response,"token");
			  System.out.println("Admintoken ="  +IdHolder.Admintoken);
			  
			  logger.info("===========Admin token creation=====================  ");
	   
	}

	@Then("User recieves {int} created with response body")
	public void user_recieves_created_with_response_body(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	//==============================================User Admin Moidule Negative============================================================
	
	@Given("User creates Post request with invalid credential")
	public void user_creates_post_request_with_invalid_credential() throws FileNotFoundException, IOException {
		request=given().spec(requestSpecification()).body( UserAdminLogindata.invaliddataBuild());
	}

	@When("User send POST HTTP request with invalid credential and endpoint")
	public void user_send_post_http_request_with_invalid_credential_and_endpoint() {
		response = request.when().post(routes.getString("Post_UserAdminLoginurl")).then().log().all().extract().response();
		String error =  UserKeyJson(response,"errorCode");
		  System.out.println("errorMessage ="  + error);
		  
		  logger.info("===========Admin token invalid credential Negative=====================  ");
	}

	@Then("User recieves {int} unauthorized")
	public void user_recieves_unauthorized(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}

	@Given("User creates GET request with request body.")
	public void user_creates_get_request_with_request_body() throws FileNotFoundException, IOException {
		request=given().spec(requestSpecification()).body( UserAdminLogindata.dataBuild());
	}

	@When("User send GET HTTP request with endpoint")
	public void user_send_get_http_request_with_endpoint() {
		response = request.when().get(routes.getString("Post_UserAdminLoginurl")).then().log().all().extract().response();
		String error =  UserKeyJson(response,"error");
		  System.out.println("errorMessage ="  + error);
		  
		  logger.info("===========Admin token invalid Method Negative=====================  ");
	}

	@Then("User recieves {int} method not allowed")
	public void user_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	

	@When("User send POST HTTP request with invalid endpoint")
	public void user_send_post_http_request_with_invalid_endpoint() {
		response = request.when().post(routes.getString("Post_invalidUserAdminLoginurl")).then().log().all().extract().response();
	}

	@Given("User creates Post request with request body and invalid content type.")
	public void user_creates_post_request_with_request_body_and_invalid_content_type() throws FileNotFoundException, IOException {
		request=given().spec(requestSpecification()).contentType(ContentType.HTML).body( UserAdminLogindata.dataBuild());
	}

	@When("User send POST HTTP request with invalid content type endpoint")
	public void user_send_post_http_request_with_invalid_content_type_endpoint() {
		response = request.when().post(routes.getString("Post_UserAdminLoginurl")).then().log().all().extract().response();
		
		logger.info("===========Admin token invalid content type Negative=====================  ");
	}

	@Then("User recieves {int} unsupported media type")
	public void user_recieves_unsupported_media_type(Integer int1) {
		assertEquals(response.getStatusCode(),415);
	}



}
