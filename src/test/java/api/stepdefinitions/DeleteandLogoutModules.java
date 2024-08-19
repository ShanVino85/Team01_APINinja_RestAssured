package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class DeleteandLogoutModules extends RestUtils {
	
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	
	//======================================================Delete PatientId By Dietician=======================================================
	
	@Given("Dietician create DELETE request")
	public void dietician_create_delete_request() throws FileNotFoundException {
		
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send DELETE http request with endpoint")
	public void dietician_send_delete_http_request_with_endpoint() {
	    
		response = request.when().delete(routes.getString("Delete_Deletebypatientid")).then().log().all().extract().response();
		
//		String error =  UserKeyJson(response,"errorCode");
//		  System.out.println("errorMessage ="  + error);
	}

	@Then("Dietician recieves {int} ok with details of the patient id")
	public void dietician_recieves_ok_with_details_of_the_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	@Given("patient creates GET request")
	public void patient_creates_get_request() {
	    
	}

	@When("patient send GET HTTP request with endpoint")
	public void patient_send_get_http_request_with_endpoint() {
	    
	}

	@Then("patient recieves {int} created with Logout successful message")
	public void patient_recieves_created_with_logout_successful_message(Integer int1) {
	    
	}

	@Given("dietician creates GET request")
	public void dietician_creates_get_request() {
	    
	}

	@When("dietician send GET HTTP request with endpoint")
	public void dietician_send_get_http_request_with_endpoint() {
	   
	}

	@Then("dietician recieves {int} created with Logout successful message")
	public void dietician_recieves_created_with_logout_successful_message(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Admin creates GET request")
	public void admin_creates_get_request() {
	    
	}

	@When("Admin send GET HTTP request with endpoint")
	public void admin_send_get_http_request_with_endpoint() {
	   
	}

	@Then("Admin recieves {int} created with Logout successful message")
	public void admin_recieves_created_with_logout_successful_message(Integer int1) {
	   
	}


}
