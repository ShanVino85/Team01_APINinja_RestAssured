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

public class Morbidity extends RestUtils {
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	
	//with admin token positive tc
	@Given("admin create GET request with admin token")
	public void admin_create_get_request_with_admin_token() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.token);
	}

	@When("admin send GET http request with endpoint")
	public void admin_send_get_http_request_with_endpoint() {
		 response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response();
    
	}

	@Then("admin recieves {int} ok with details of the patient id")
	public void admin_recieves_ok_with_details_of_the_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	@Given("admin create GET request with morbidity condition by test name with admin token")
	public void admin_create_get_request_with_morbidity_condition_by_test_name_with_admin_token() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.token);
	   
	}

	@When("admin send GET http request with morbidity condition by test name endpoint")
	public void admin_send_get_http_request_with_morbidity_condition_by_test_name_endpoint() {
		 response = request.when().get(routes.getString("Get_RetrieveMorbidityconditionbyTestname")).then().log().all().extract().response();
		    
	}

	@Then("admin recieves {int} ok with morbidity condition by test name endpoint details of the patient id")
	public void admin_recieves_ok_with_morbidity_condition_by_test_name_endpoint_details_of_the_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
//positivetc with DIETICIAN TOKEN(need to add dietician token)
	@Given("Dietician create GET request with dietician token")
	public void dietician_create_get_request_with_dietician_token() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.DieticianToken);
	}

	@When("Dietician send GET http request with endpoint")
	public void dietician_send_get_http_request_with_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response();
	    
	}

	@When("Dietician send GET http request with morbidity condition by test name endpoint endpoint")
	public void dietician_send_get_http_request_with_morbidity_condition_by_test_name_endpoint_endpoint() {
		response = request.when().get(routes.getString("Get_RetrieveMorbidityconditionbyTestname")).then().log().all().extract().response();
	    
	    
	}

	@Then("Dietician recieves {int} ok with morbidity condition by test name endpoint details of the patient id")
	public void dietician_recieves_ok_with_morbidity_condition_by_test_name_endpoint_details_of_the_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

//Negative TC
	@Given("Dietician create GET request with no auth")
	public void dietician_create_get_request_with_no_auth() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification());
	    
	}

	@Then("Dietician recieves {int} unauthorized")
	public void dietician_recieves_unauthorized(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	    
	}
//need to add patient token
	@Given("Patient create GET request with patient token")
	public void patient_create_get_request_with_patient_token() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.token);
	    
	}

	@When("Patient send GET http request with endpoint")
	public void patient_send_get_http_request_with_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response(); 
	}

	@Then("Patient recieves {int} Forbidden")
	public void patient_recieves_forbidden(Integer int1) {
		assertEquals(response.getStatusCode(),403);
	}
	@Given("admin create POST request")
	public void admin_create_post_request()throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.token);
	    
	}

	@When("admin send POST http request with endpoint")
	public void admin_send_post_http_request_with_endpoint() {
		response = request.when().post(routes.getString("Invalid_Post_GetallMorbidities")).then().log().all().extract().response();
	    
	}

	@Then("admin recieves {int} method not allowed")
	public void admin_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}
		   
	@Given("admin create GET request")
	public void admin_create_get_request() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.token);
	}
	    

	@When("admin send GET http request with invalid endpoint")
	public void admin_send_get_http_request_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbiditiesinvalid")).then().log().all().extract().response(); 
	}

	@Then("admin recieves {int} not found")
	public void admin_recieves_not_found(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	}
	@Given("Dietician create POST request")
	public void dietician_create_post_request() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.DieticianToken);
	}

	@When("Dietician send POST http request with endpoint")
	public void dietician_send_post_http_request_with_endpoint() {
		response = request.when().post(routes.getString("Invalid_Post_GetallMorbidities")).then().log().all().extract().response();
	    
	}

	@Then("Dietician recieves {int} method not allowed")
	public void dietician_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("Dietician create GET request")
	public void dietician_create_get_request() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.DieticianToken);
	}

	@When("Dietician send GET http request with invalid endpoint")
	public void dietician_send_get_http_request_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbiditiesinvalid")).then().log().all().extract().response(); 
	    
	}

	@Then("Dietician recieves {int} not found")
	public void dietician_recieves_not_found(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	    
	}
	@When("Dietician send GET http request with morbidity condition by test name  endpoint")
	public void dietician_send_get_http_request_with_morbidity_condition_by_test_name_endpoint() {
		response = request.when().get(routes.getString("Get_RetrieveMorbidityconditionbyTestname")).then().log().all().extract().response();
	    
	    
	}

//have to add patient token
	@Given("Patient create GET request")
	public void patient_create_get_request() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification());
	    
	}

	@When("Patient send GET http request with morbidity condition by test name  endpoint")
	public void patient_send_get_http_request_with_morbidity_condition_by_test_name_endpoint() {
		response = request.when().get(routes.getString("Get_RetrieveMorbidityconditionbyTestname")).then().log().all().extract().response();
	        
	}
	@When("admin send POST http request morbidity condition by test name endpoint")
	public void admin_send_post_http_request_morbidity_condition_by_test_name_endpoint() {
		response = request.when().post(routes.getString("Post_RetrieveMorbidityconditionbyTestname ")).then().log().all().extract().response();
	}

	@When("admin send GET http request with invalid test name endpoint")
	public void admin_send_get_http_request_with_invalid_test_name_endpoint() {
		response = request.when().get(routes.getString("Get_RetrieveMorbidityconditionbyInvalidTestname")).then().log().all().extract().response();
 
	}
	@When("admin send GET http request morbidity condition by test name with invalid endpoint")
	public void admin_send_get_http_request_morbidity_condition_by_test_name_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Get_RetrieveMorbidityconditionbyinvalidTestname")).then().log().all().extract().response();
		 
	    
	}

	@When("Dietician send POST http request morbidity condition by test name  with invalid method endpoint")
	public void dietician_send_post_http_request_morbidity_condition_by_test_name_with_invalid_method_endpoint() {
		response = request.when().post(routes.getString("Post_RetrieveMorbidityconditionbyTestname")).then().log().all().extract().response();
	}
	
	@When("Dietician send GET http request with morbidity condition by invalid test name  endpoint")
	public void dietician_send_get_http_request_with_morbidity_condition_by_invalid_test_name_endpoint() {
		response = request.when().get(routes.getString("Invalid_Morbidity_Test_name")).then().log().all().extract().response();

	}

	@When("Dietician send GET http request morbidity condition by test name with invalid endpoint")
	public void dietician_send_get_http_request_morbidity_condition_by_test_name_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Invalid_Morbidity_Endpoint")).then().log().all().extract().response();

	}


















	












}
