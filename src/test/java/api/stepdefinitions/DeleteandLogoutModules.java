package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DeleteandLogoutModules extends RestUtils {
	
	Logger logger = LogManager.getLogger("DeleteandLogoutModules.java");
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	public static String token;
	String asString;
	String Json1;
	
	
	//========================================================Patient Logout==================================================================
		@Given("patient creates GET request")
		public void patient_creates_get_request() throws FileNotFoundException {
			request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.dietitianToken);
		}

		@When("patient send GET HTTP request with endpoint")
		public void patient_send_get_http_request_with_endpoint() {
			response = request.when().get(routes.getString("Get_PatientUserLogouturl")).then().log().all().extract().response();
			
			 logger.info("===========Patient Logout=====================  ");
		}

		@Then("patient recieves {int} created with Logout successful message")
		public void patient_recieves_created_with_logout_successful_message(Integer int1) {
			assertEquals(response.getStatusCode(),200);
		}
	
	//======================================================Delete PatientId By Dietician=======================================================
	
	@Given("Dietician create DELETE request")
	public void dietician_create_delete_request() throws FileNotFoundException {
		
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.dietitianToken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send DELETE http request with endpoint")
	public void dietician_send_delete_http_request_with_endpoint() {
	    
		response = request.when().delete(routes.getString("Delete_Deletebypatientid")).then().log().all().extract().response();
		
		logger.info("===========Delete PatientId By Dietician=====================  ");
	}

	@Then("Dietician recieves {int} ok with details of the patient id")
	public void dietician_recieves_ok_with_details_of_the_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

//===========================================================Dietician Logout===========================================================
	@Given("dietician creates GET request")
	public void dietician_creates_get_request() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Admintoken);
	}

	@When("dietician send GET HTTP request with endpoint")
	public void dietician_send_get_http_request_with_endpoint() {
		response = request.when().get(routes.getString("Get_DieticianUserLogouturl")).then().log().all().extract().response();
		
		logger.info("===========Dietician Logout====================  ");
	}

	@Then("dietician recieves {int} created with Logout successful message")
	public void dietician_recieves_created_with_logout_successful_message(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
//=======================================================Admin Logout====================================================================
	@Given("Admin creates GET request")
	public void admin_creates_get_request() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Admintoken);
	}

	@When("Admin send GET HTTP request with endpoint")
	public void admin_send_get_http_request_with_endpoint() {
		response = request.when().get(routes.getString("Get_AdminUserLogouturl")).then().log().all().extract().response();
		
		logger.info("===========Admin Logout====================  ");
	}

	@Then("Admin recieves {int} created with Logout successful message")
	public void admin_recieves_created_with_logout_successful_message(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	//========================================Negative Test cases======================================================================
	
	@Given("Dietician create POST request")
	public void dietician_create_post_request() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.dietitianToken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send POST http to delete patient by id request with endpoint")
	public void dietician_send_post_http_to_delete_patient_by_id_request_with_endpoint() {
		response = request.when().post(routes.getString("Delete_Deletebypatientid")).then().log().all().extract().response();
		
		logger.info("===========Delete patient by id with invalid method====================  ");
	}
	
	@Then("Dietici recieves {int} method not allowed")
	public void dietici_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("Dietician create DELETE request by invalid id")
	public void dietician_create_delete_request_by_invalid_id() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+"DT05").pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send DELETE http request with endpoint by invalid id")
	public void dietician_send_delete_http_request_with_endpoint_by_invalid_id() {
		response = request.when().get(routes.getString("Delete_Deletebypatientid")).then().log().all().extract().response();
		
		logger.info("===========Delete patientid  by invalid id====================  ");
	}
	
	@Then("Dietici recieves {int} not found")
	public void dietici_recieves_not_found(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	}

	@When("Dietician send DELETE http request with invalid endpoint")
	public void dietician_send_delete_http_request_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Delete_invalidDeletebypatientid")).then().log().all().extract().response();
		
		logger.info("===========Delete patientid  by invalid endpoint====================  ");
	}

	@Given("Dietician create DELETE request with no auth")
	public void dietician_create_delete_request_with_no_auth() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer ").pathParam("patientId", IdHolder.patientId1);
		
		logger.info("===========Delete patientid  by No auth====================  ");
	}
	@Then("Dietici recieves {int} unauthorized")
	public void dietici_recieves_unauthorized(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}

	@Given("patient creates POST request")
	public void patient_creates_post_request() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Patienttoken);
		
		logger.info("===========Delete patientid  by Patienttoken===================  ");
	}

	@When("patient send POST HTTP request with endpoint")
	public void patient_send_post_http_request_with_endpoint() {
		response = request.when().post(routes.getString("Get_PatientUserLogouturl")).then().log().all().extract().response();
		
		logger.info("===========Patient Logout with in valid Method===================  ");
	}

	@Then("patient recieves {int} method not allowed")
	public void patient_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("patient creates GET request with noauth")
	public void patient_creates_get_request_with_noauth() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer ");
		
		logger.info("===========Patient Logout with No auth==================  ");
	}

	@Then("patient recieves {int} unauthorized")
	public void patient_recieves_unauthorized(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}

	@Given("dietician creates POST request")
	public void dietician_creates_post_request() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.dietitianToken);
		
		
	}

	@When("dietician send POST HTTP request with endpoint")
	public void dietician_send_post_http_request_with_endpoint() {
		response = request.when().post(routes.getString("Get_PatientUserLogouturl")).then().log().all().extract().response();
		
		logger.info("===========Dietician Logout with invalid method==================  ");
	}

	@Then("dietician recieves {int} method not allowed")
	public void dietician_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("dietician creates GET request with no auth")
	public void dietician_creates_get_request_with_no_auth() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer ");
		
		logger.info("===========Dietician Logout with No auth==================  ");
	}

	@Then("dietician recieves {int} unauthorized")
	public void dietician_recieves_unauthorized(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}

	@Given("admin creates POST request")
	public void admin_creates_post_request() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Admintoken);
	}

	@When("admin send POST HTTP request with endpoint")
	public void admin_send_post_http_request_with_endpoint() {
		response = request.when().post(routes.getString("Get_PatientUserLogouturl")).then().log().all().extract().response();
		
		logger.info("===========Admin Logout with invalid method==================  ");
	}

	@Then("admin recieves {int} method not allowed")
	public void admin_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("admin creates GET request with noauth")
	public void admin_creates_get_request_with_noauth() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer ");
	}

	@When("admin send GET HTTP request with noauth endpoint")
	public void admin_send_get_http_request_with_noauth_endpoint() {
		response = request.when().get(routes.getString("Get_PatientUserLogouturl")).then().log().all().extract().response();
		
		logger.info("===========Admin Logout with No auth==================  ");
	}

	@Then("admin recieves {int} unauthorized")
	public void admin_recieves_unauthorized(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}


}
