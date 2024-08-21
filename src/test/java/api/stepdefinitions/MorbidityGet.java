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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MorbidityGet extends RestUtils {
	Logger logger = LogManager.getLogger("MorbidityGet.java");
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	
	//-----------------------Get all morbidities and  Morbidity condition by Test name with admin token with positive cases--------------------//
	@Given("admin create GET request with all morbidities details admin token")
	public void  admin_create_get_request_with_all_morbidities_details_admin_token() throws FileNotFoundException, IOException {	    
	    request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Admintoken);
	}
	

	@When("admin send GET http request with all morbidities details endpoint")
	public void  admin_send_get_http_request_with_all_morbidities_details_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response();
	       List<Map<String, Object>> morbidities = response.jsonPath().getList("");
	       IdHolder.testName = (String) morbidities.get(1).get("morbidityTestName");
	       logger.info("----------admin gets all morbidity details-----------");
	    
	}
    @Then("admin recieves {int} ok with all morbidities details patient id")
    public void admin_recieves_ok_with_all_morbidities_details_patient_id(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/Morbidityallschema.json"));
	
	}
	@Given("admin create GET request with morbidity condition by test name with admin token")
	public void admin_create_get_request_with_morbidity_condition_by_test_name_with_admin_token() throws FileNotFoundException, IOException {
	    request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Admintoken);
	}

	@When("admin send GET http request with morbidity condition by test name endpoint")
	public void admin_send_get_http_request_with_morbidity_condition_by_test_name_endpoint() {
		String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname")
                .replace("morbidityName", IdHolder.testName);
             response = request.when().get(endpoint).then().log().all().extract().response();
             logger.info("----------admin gets all condition bytest name-----------");
	}

	@Then("admin recieves {int} ok with morbidity condition by test name endpoint details of the patient id")
	public void admin_recieves_ok_with_morbidity_condition_by_test_name_endpoint_details_of_the_patient_id(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/Morbidityschema.json"));
	}
	//----------------Get all morbidities and  Morbidity condition by Test name with Dietician token with positive cases---------------
	
	@Given("Dietician create GET request with dietician token to retrieve all morbidities details")
	public void dietician_create_get_request_with_dietician_token_to_retrieve_all_morbidities_details() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.dietitianToken);
	}

	@When("Dietician send GET http request with endpoint to retrieve all morbidities details")
	public void dietician_send_get_http_request_with_endpoint_to_retrieve_all_morbidities_details() {
response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response();
	    
	    List<Map<String, Object>> morbidities = response.jsonPath().getList("");
	      IdHolder.testName = (String) morbidities.get(0).get("morbidityTestName"); 
	      logger.info("----------Dietician  gets all morbidity details-----------");
	    
	}
	@Then("admin recieves {int} ok with details of the patient id to retrieve all morbidities details")
	public void admin_recieves_ok_with_details_of_the_patient_id_to_retrieve_all_morbidities_details(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		
	}
	@Given("Dietician create GET request with dietician token to retrieve morbidity condition by test name")
	public void dietician_create_get_request_with_dietician_token_to_retrieve_morbidity_condition_by_test_name()  throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.dietitianToken);
	}
	

	@When("Dietician send GET http request with morbidity condition by test name to retrieve morbidity condition by test name")
	public void dietician_send_get_http_request_with_morbidity_condition_by_test_name_to_retrieve_morbidity_condition_by_test_name() {
		String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname").replace("morbidityName", IdHolder.testName);
        response = request.when().get(endpoint).then().log().all().extract().response();
        logger.info("----------Dietician  gets all morbidity details by test name-----------");
	    
	}

	@Then("Dietician recieves {int} ok with morbidity condition by test name endpoint details of the patient id")
	public void dietician_recieves_ok_with_morbidity_condition_by_test_name_endpoint_details_of_the_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/Morbidityschema.json"));
	}

//---------------------Get all morbidities Negative  cases--------------------//

	@Given("Dietician create GET request with no auth to retrieve all morbidities details")
	public void dietician_create_get_request_with_no_auth_to_retrieve_all_morbidities_details() throws FileNotFoundException, IOException {
	    request = given().spec(requestSpecification());
	}

	@When("Dietician send GET http request with no auth to retrieve all morbidities details")
	public void dietician_send_get_http_request_with_no_auth_to_retrieve_all_morbidities_details() {
	    response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response();
	    logger.info("----------Dietician send get request with no auth-----------");
	}

	@Then("Dietician recieves 401 unauthorized with no auth all morbidities details")
	public void dietician_recieves_401_unauthorized_with_no_auth_all_morbidities_details() {
	    assertEquals(response.getStatusCode(), 401);
	}

	@Given("Patient create GET request with patient token to retrieve all morbidities details")
	public void patient_create_get_request_with_patient_token_to_retrieve_all_morbidities_details() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Patienttoken);
	    
	}

	@When("Patient send GET http request with to retrieve all morbidities details endpoint")
	public void patient_send_get_http_request_with_to_retrieve_all_morbidities_details_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbidities")).then().log().all().extract().response(); 
		logger.info("----------Patient  send get request to retrive all morbidities details-----------");
	}

	@Then("Patient recieves {int} Forbidden to retrieve all morbidities details")
	public void patient_recieves_forbidden_to_retrieve_all_morbidities_details(Integer int1) {
		assertEquals(response.getStatusCode(),403);
	}
	@Given("admin create POST request to retrieve all morbidities details with invalid method")
	public void admin_create_post_request_to_retrieve_all_morbidities_details_with_invalid_method()throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Admintoken);
	    
	}

	@When("admin send POST http request with endpoint to retrieve all morbidities details with invalid method")
	public void admin_send_post_http_request_with_endpoint_to_retrieve_all_morbidities_details_with_invalid_method() {
		String endpoint = routes.getString("Get_GetallMorbidities");
        response = request.when().post(endpoint).then().log().all().extract().response();
        logger.info("----------Admin  send onvalid method-----------");
	    
	    
	}

	@Then("admin recieves {int} method not allowed to retrieve all morbidities details with invalid method")
	public void admin_recieves_method_not_allowed_to_retrieve_all_morbidities_details_with_invalid_method(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}
		   
	@Given("admin create GET request to retrieve all morbidities details with invalid endpoint")
	public void admin_create_get_request_to_retrieve_all_morbidities_details_with_invalid_endpoint() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Admintoken);
	}
	    

	@When("admin send GET http request to retrieve all morbidities details with invalid endpoint")
	public void admin_send_get_http_request_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbiditiesinvalid")).then().log().all().extract().response(); 
		logger.info("----------Admin  send request with invalid endpoint-----------");
	}

	@Then("admin recieves {int} not found to retrieve all morbidities details with invalid endpoint")
	public void admin_recieves_not_found_to_retrieve_all_morbidities_details_with_invalid_endpoint(Integer int1)  {
		assertEquals(response.getStatusCode(),404);
	}
	@Given("Dietician create POST request to retrieve all morbidities details with invalid method")
	public void dietician_create_post_request_to_retrieve_all_morbidities_details_with_invalid_method() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.dietitianToken);
	}

	@When("Dietician send POST http requestto retrieve all morbidities details with invalid method")
	public void dietician_send_post_http_requestto_retrieve_all_morbidities_details_with_invalid_method() {
		String endpoint = routes.getString("Get_GetallMorbidities");
        response = request.when().post(endpoint).then().log().all().extract().response();
        logger.info("----------Dietician  send request with invalid method-----------");
	    
	}

	@Then("Dietician recieves {int} method not allowed to retrieve all morbidities details with invalid method")
	public void dietician_recieves_method_not_allowed_to_retrieve_all_morbidities_details_with_invalid_method(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("Dietician create GET request to retrieve all morbidities details with invalid endpoint")
	public void dietician_create_get_request_to_retrieve_all_morbidities_details_with_invalid_endpoint() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.dietitianToken);
	}

	@When("Dietician send GET http request to retrieve all morbidities details with invalid endpoint")
	public void dietician_send_get_http_request_to_retrieve_all_morbidities_details_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Get_GetallMorbiditiesinvalid")).then().log().all().extract().response(); 
		logger.info("----------Dietician  send get request with invalid endpint-----------");
		
	    
	}

	@Then("Dietician recieves {int} not found to retrieve all morbidities details with invalid endpoint")
	public void dietician_recieves_not_found_to_retrieve_all_morbidities_details_with_invalid_endpoint(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	    
	}
	
	@Given("Dietician create GET request with no auth to morbidity condition by test name")
	public void dietician_create_get_request_with_no_auth_to_morbidity_condition_by_test_name()throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification());
		
	}
	@When("Dietician send GET http request with morbidity condition by test name endpoint endpoint")
	public void dietician_send_get_http_request_with_morbidity_condition_by_test_name_endpoint_endpoint() {
		String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname").replace("morbidityName", IdHolder.testName);
        response = request.when().get(endpoint).then().log().all().extract().response();
        logger.info("----------Dietician  send request with no auth -----------");
	}
	
	@Then("Dietician recieves 401 unauthorized with morbidity condition by test name")
	public void dietician_recieves_401_unauthorized_with_morbidity_condition_by_test_name() {
	    assertEquals(response.getStatusCode(), 401);
	}
	
//-----------------------------------Morbidity condition by Test name Negative  cases---------------------------
	
	@Given("Patient create GET request to retrieve morbidity condition by test name")
	public void patient_create_get_request_to_retrieve_morbidity_condition_by_test_name() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Patienttoken);
	    
	}

	@When("Patient send GET http request with morbidity condition by test name  endpoint")
	public void patient_send_get_http_request_with_morbidity_condition_by_test_name_endpoint() {
		String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname").replace("morbidityName", IdHolder.testName);
        response = request.when().get(endpoint).then().log().all().extract().response();
        logger.info("----------patient send get request with test name-----------");
        
	}
	@Then("Patient receives 403 Forbidden to retrieve morbidity condition by test name")
	public void patient_recieves_403_forbidden_to_retrieve_morbidity_condition_by_test_name(Integer int1) {
		assertEquals(response.getStatusCode(), 403);
	}
	
	@Given("admin create POST request to retrieve morbidity condition by test name  with invalid method")
	public void admin_create_post_request_to_retrieve_morbidity_condition_by_test_name_with_invalid_method() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()) .header("Authorization", "Bearer " + IdHolder.Admintoken);
	    		
	}

	
	@When("admin send POST http request morbidity condition by test name endpoint")
	public void admin_send_post_http_request_morbidity_condition_by_test_name_endpoint() {
		String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname").replace("morbidityName", IdHolder.testName);
        response = request.when().post(endpoint).then().log().all().extract().response();
        logger.info("----------admin  send request with invalid method-----------");
        }
	@Then("admin recieves {int} method not allowed to retrieve morbidity condition by test name  with invalid method")
	public void admin_recieves_method_not_allowed_to_retrieve_morbidity_condition_by_test_name_with_invalid_method(Integer int1) {
		assertEquals(response.getStatusCode(), 405);
	}


	@When("admin send GET http request with invalid test name endpoint")
	public void admin_send_get_http_request_with_invalid_test_name_endpoint() {
		response = request.when().get(routes.getString("Get_RetrieveMorbidityconditionbyInvalidTestname")).then().log().all().extract().response();
		logger.info("----------admin  send request with invalid testname-----------");
 
	}
	@When("admin send GET http request morbidity condition by test name with invalid endpoint")
	public void admin_send_get_http_request_morbidity_condition_by_test_name_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Get_RetrieveMorbidityconditionbyinvalidTestname")).then().log().all().extract().response();
		 
	    
	}

	@When("Dietician send POST http request morbidity condition by test name  with invalid method endpoint")
	public void dietician_send_post_http_request_morbidity_condition_by_test_name_with_invalid_method_endpoint() {
		String endpoint = routes.getString("Get_RetrieveMorbidityconditionbyTestname").replace("morbidityName", IdHolder.testName);
        response = request.when().post(endpoint).then().log().all().extract().response();
        logger.info("----------Dietician  send request with invalid method-----------");
	    
	}
	
	@When("Dietician send GET http request with morbidity condition by invalid test name  endpoint")
	public void dietician_send_get_http_request_with_morbidity_condition_by_invalid_test_name_endpoint() {
		response = request.when().get(routes.getString("Invalid_Morbidity_Test_name")).then().log().all().extract().response();
		logger.info("----------Dietician  send request with invalid test name-----------");

	}

	@When("Dietician send GET http request morbidity condition by test name with invalid endpoint")
	public void dietician_send_get_http_request_morbidity_condition_by_test_name_with_invalid_endpoint() {
		response = request.when().get(routes.getString("Invalid_Morbidity_Endpoint")).then().log().all().extract().response();
		logger.info("----------Dietician  send request with invalid endpoind-----------");

	}


















	












}
