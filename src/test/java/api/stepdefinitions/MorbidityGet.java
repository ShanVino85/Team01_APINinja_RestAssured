	package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import api.utils.IdHolder;
import java.util.List;
import java.util.Map;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class MorbidityGet extends RestUtils {
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	
	
	@Given("admin create GET request with admin token")
	public void admin_create_get_request_with_admin_token() throws FileNotFoundException, IOException {	    
	    request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token);
        response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response();
       List<Map<String, Object>> morbidities = response.jsonPath().getList("");
	    if (morbidities != null && !morbidities.isEmpty()) {
       IdHolder.testName = (String) morbidities.get(1).get("morbidityTestName");
	         } else
	         {
	        throw new RuntimeException("No morbidities found in the response.");
	    }
	}

	@When("admin send GET http request with endpoint")
	public void admin_send_get_http_request_with_endpoint() {
	    response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all() .extract().response();
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/Morbidityallschema.json"));
	}
    @Then("admin recieves {int} ok with details of the patient id")
	public void admin_recieves_ok_with_details_of_the_patient_id(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	}
	@Given("admin create GET request with morbidity condition by test name with admin token")
	public void admin_create_get_request_with_morbidity_condition_by_test_name_with_admin_token() throws FileNotFoundException, IOException {
	    request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token);
	}

	@When("admin send GET http request with morbidity condition by test name endpoint")
	public void admin_send_get_http_request_with_morbidity_condition_by_test_name_endpoint() {
	    String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname").replace("{testName}", IdHolder.testName);
        response = request.when().get(endpoint).then().log().all().extract().response();
	}

	@Then("admin recieves {int} ok with morbidity condition by test name endpoint details of the patient id")
	public void admin_recieves_ok_with_morbidity_condition_by_test_name_endpoint_details_of_the_patient_id(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/Morbidityschema.json"));
	}
//positivetc with DIETICIAN TOKEN
	@Given("Dietician create GET request with dietician token")
	public void dietician_create_get_request_with_dietician_token() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
	    response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response();
	    
	    List<Map<String, Object>> morbidities = response.jsonPath().getList("");
	    
	    if (morbidities != null && !morbidities.isEmpty()) {
	        IdHolder.testName = (String) morbidities.get(0).get("morbidityTestName"); 
	    } else {
	        throw new RuntimeException("No morbidities found in the response.");
	    }
	}

	@When("Dietician send GET http request with endpoint")
	public void dietician_send_get_http_request_with_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response();
	    
	}
	
	@Given("Dietician create GET request with dietician token for test name")
	public void dietician_create_get_request_with_dietician_token_for_test_name() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/Morbidityallschema.json"));
	}
	

	@When("Dietician send GET http request with morbidity condition by test name endpoint endpoint")
	public void dietician_send_get_http_request_with_morbidity_condition_by_test_name_endpoint_endpoint() {
		String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname").replace("{testName}", IdHolder.testName);
        response = request.when().get(endpoint).then().log().all().extract().response();
	    
	}

	@Then("Dietician recieves {int} ok with morbidity condition by test name endpoint details of the patient id")
	public void dietician_recieves_ok_with_morbidity_condition_by_test_name_endpoint_details_of_the_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/Morbidityschema.json"));
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
	@Given("Patient create GET request with patient token")
	public void patient_create_get_request_with_patient_token() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Patienttoken);
	    
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
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
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
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
	}

	@When("Dietician send GET http request with invalid endpoint")
	public void dietician_send_get_http_request_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbiditiesinvalid")).then().log().all().extract().response(); 
	    
	}

	@Then("Dietician recieves {int} not found")
	public void dietician_recieves_not_found(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	    
	}

	@Given("Patient create GET request")
	public void patient_create_get_request() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Patienttoken);
	    
	}

	@When("Patient send GET http request with morbidity condition by test name  endpoint")
	public void patient_send_get_http_request_with_morbidity_condition_by_test_name_endpoint() {
		String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname").replace("{testName}", IdHolder.testName);
        response = request.when().get(endpoint).then().log().all().extract().response();
	}
	@When("admin send POST http request morbidity condition by test name endpoint")
	public void admin_send_post_http_request_morbidity_condition_by_test_name_endpoint() {
		response = request.when().post(routes.getString("Invalid_Post_RetrieveMorbidityconditionbyTestname")).then().log().all().extract().response();
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
		response = request.when().post(routes.getString("Invalid_Post_RetrieveMorbidityconditionbyTestname")).then().log().all().extract().response();
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
