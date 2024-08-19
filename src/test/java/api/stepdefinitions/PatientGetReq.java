package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import api.pojo.PatientGetResponse;
import api.utils.IdHolder;
import api.utils.RestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PatientGetReq extends RestUtils {
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	
	//Postive Tc with Dietician Token

	@Given("Dietician create GET request patients morbidity details by endpoint as patient")
    public void dietician_create_get_request_patients_morbidity_details_by_endpoint_as_patient() throws FileNotFoundException, IOException {
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
     }

    @When("Dietician send GET http request with endpoint by endpoint as patient")
    public void dietician_send_get_http_request_with_endpoint_by_endpoint_as_patient() {
       String endpoint = routes.getString("Get_GetPatientsMorbidityDetails");
        response = request.when().get(endpoint).then().log().all().extract().response();
        List<PatientGetResponse> patients = response.jsonPath().getList("", PatientGetResponse.class);
          
        String fileId = patients.get(0).getFileId();
            IdHolder.FileID = fileId;        
  }
    @Then("Dietician recieves {int} ok with details morbidity details by endpoint as patient")
    public void dietician_recieves_ok_with_details_morbidity_details_by_endpoint_as_patient(Integer int1) {
    	 assertEquals("Status code should be 200", (int)200, response.getStatusCode());
    	 response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/PatientGetMorbidity.json"));
 
    }
	@Given("Dietician create GET request by field Id with Dietician Token")
	public void dietician_create_get_request_by_field_id_with_dietician_token() throws FileNotFoundException, IOException {
	    request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
	}

	@When("Dietician create GET request patients morbidity details by endpoint as patient File id")
	public void dietician_create_get_request_patients_morbidity_details_by_endpoint_as_patient_file_id() {
response = request.when().get(routes.getString("Get_RetrievePatientfilebyFileId").replace("{fileId}", IdHolder.FileID)).then().log().all().extract().response();
	}


	@Then("Dietician recieves {int} ok with details of the patient id by field with Dietician Token")
	public void dietician_recieves_ok_with_details_of_the_patient_id_by_field_with_dietician_token(Integer int1) {
	    assertEquals(response.getStatusCode(), 200);
	}
	//Postive Tc with Patient Token
	@Given("Patient create GET request retrieve patients morbidity details by patient Endpoint with Patient Token")
	public void patient_create_get_request_retrieve_patients_morbidity_details_by_patient_endpoint_with_patient_token() throws FileNotFoundException, IOException {
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Patienttoken);
        
	}
	@When("Patient send GET http request with retrieve patients morbidity details by patient Endpoint with Patient Token")
	public void patient_send_get_http_request_with_retrieve_patients_morbidity_details_by_patient_endpoint_with_patient_token() {
		String endpoint = routes.getString("Get_GetPatientsMorbidityDetails");
        response = request.when().get(endpoint).then().log().all().extract().response();
        List<PatientGetResponse> patients = response.jsonPath().getList("", PatientGetResponse.class);
        if (patients != null && !patients.isEmpty()) {
            // Extract fileId from the first patient response
            String fileId = patients.get(0).getFileId();
            if (fileId != null) {
                IdHolder.FileID = fileId;
                System.out.println("File ID: " + fileId);
            } else {
                throw new RuntimeException("File ID is null in the response.");
            }
        } else {
            throw new RuntimeException("No patients found in the response.");
        }
	
	}
	@Then("Patient recieves {int} ok with retrieve patients morbidity details by patient Endpoint with Patient Token")
	public void patient_recieves_ok_with_retrieve_patients_morbidity_details_by_patient_endpoint_with_patient_token(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
   	 response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/MorbidityModule/PatientGetMorbidity.json"));

	}
	
	@Given("Check patient is able to retrieve patients morbidity details by File ID with Patient Token")
	public void check_patient_is_able_to_retrieve_patients_morbidity_details_by_file_id_with_patient_token() throws FileNotFoundException, IOException {
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Patienttoken);
        }  
	@When("Patient create GET request patients morbidity details by endpoint as patient")
	public void patient_create_get_request_patients_morbidity_details_by_endpoint_as_patient() {
    response = request.when().get(routes.getString("Get_RetrievePatientfilebyFileId").replace("{fileId}", IdHolder.FileID)).then().log().all().extract().response();
		}
	@Then("Patient recieves {int} ok with details of the patient id by field with Patient Token")
	public void patient_recieves_ok_with_details_of_the_patient_id_by_field_with_patient_token(Integer int1) {
	    assertEquals(response.getStatusCode(), 200);
	}
	
	//Negative Tc
	
	@Given("Dietician create GET request with no auth to retrieve patients morbidity details by patient ID")
	public void dietician_create_get_request_with_no_auth_to_retrieve_patients_morbidity_details_by_patient_id() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification());

	}

	@When("Dietician send GET http request with endpoint to retrieve patients morbidity details by patient ID")
	public void dietician_send_get_http_request_with_endpoint_to_retrieve_patients_morbidity_details_by_patient_id() {
		String endpoint = routes.getString("Get_GetPatientsMorbidityDetails");
        response = request.when().get(endpoint).then().log().all().extract().response();
	}

	@Then("Dietician recieves {int} unauthorized to retrieve patients morbidity details by patient ID")
	public void dietician_recieves_unauthorized_to_retrieve_patients_morbidity_details_by_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}

	@Given("Admin create GET request with Admin token")
	public void admin_create_get_request_with_admin_token() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token);
	}

	@When("Admin send GET http request with endpoint to retrieve patients morbidity details by patient ID")
	public void admin_send_get_http_request_with_endpoint_to_retrieve_patients_morbidity_details_by_patient_id() {
		String endpoint = routes.getString("Get_GetPatientsMorbidityDetails");
        response = request.when().get(endpoint).then().log().all().extract().response();
	}

	@Then("Admin recieves {int} Forbidden to retrieve patients morbidity details by patient ID")
	public void admin_recieves_forbidden_to_retrieve_patients_morbidity_details_by_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),403);
	}
	@Given("Dietician create POST request to retrieve patients morbidity details by patient ID with invalid method")
	public void dietician_create_post_request_to_retrieve_patients_morbidity_details_by_patient_id_with_invalid_method()throws FileNotFoundException, IOException {
		
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);     
	    
	}

	@When("Dietician send POST http request with endpoint to retrieve patients morbidity details by patient ID with invalid method")
	public void dietician_send_post_http_request_with_endpoint_to_retrieve_patients_morbidity_details_by_patient_id_with_invalid_method() {
		String endpoint = routes.getString("Invalid_Post_GetPatientsMorbidityDetails");
        response = request.when().post(endpoint).then().log().all().extract().response();
	}

	@Then("Dietician recieves {int} method not allowed to retrieve patients morbidity details by patient ID with invalid method")
	public void dietician_recieves_method_not_allowed_to_retrieve_patients_morbidity_details_by_patient_id_with_invalid_method(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("Dietician create GET request to retrieve patients morbidity details by invalid patient ID")
	public void dietician_create_get_request_to_retrieve_patients_morbidity_details_by_invalid_patient_id()throws FileNotFoundException, IOException {
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);     
	    
	}

	@When("Dietician send GET http request with endpoint to retrieve patients morbidity details by invalid patient ID")
	public void dietician_send_get_http_request_with_endpoint_to_retrieve_patients_morbidity_details_by_invalid_patient_id() {
		String endpoint = routes.getString("Invalidpatient_GetPatientsMorbidityDetails");
        response = request.when().get(endpoint).then().log().all().extract().response();
	}

	@Then("Dietician recieves {int} not found to retrieve patients morbidity details by invalid patient ID")
	public void dietician_recieves_not_found_to_retrieve_patients_morbidity_details_by_invalid_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	}
	@Given("Dietician create GET request to retrieve patients morbidity details by patient ID with invalid endpoint")
	public void dietician_create_get_request_to_retrieve_patients_morbidity_details_by_patient_id_with_invalid_endpoint() throws FileNotFoundException, IOException {
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
     
	}

	@When("Dietician send GET http request with invalid endpoint to retrieve patients morbidity details by patient ID with invalid endpoint")
	public void dietician_send_get_http_request_with_invalid_endpoint_to_retrieve_patients_morbidity_details_by_patient_id_with_invalid_endpoint() {
		String endpoint = routes.getString("Invalidendpoint_GetPatientsMorbidityDetails");
        response = request.when().get(endpoint).then().log().all().extract().response();
	}

	@Then("Dietician recieves {int} not found to retrieve patients morbidity details by patient ID with invalid endpoint")
	public void dietician_recieves_not_found_to_retrieve_patients_morbidity_details_by_patient_id_with_invalid_endpoint(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	}

	@Given("Dietician create GET request  with no auth to retrieve patients by fielId")
	public void dietician_create_get_request_with_no_auth_to_retrieve_patients_by_fiel_id()throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification());
	}

	@When("Dietician send GET http request with endpoint to retrieve patients by fielId")
	public void dietician_send_get_http_request_with_endpoint_to_retrieve_patients_by_fiel_id() {
	    response = request.when().get(routes.getString("Get_RetrievePatientfilebyFileId").replace("{fileId}", IdHolder.FileID)).then().log().all().extract().response();

	}

	@Then("Dietician recieves {int} unauthorized to retrieve patients by fielId")
	public void dietician_recieves_unauthorized_to_retrieve_patients_by_fiel_id(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}
	@Given("Admin create GET request to retrieve patients by fielId")
	public void admin_create_get_request_to_retrieve_patients_by_fiel_id() throws FileNotFoundException, IOException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token);
	    
	}

	@When("Admin send GET http request with endpoint to retrieve patients by fielId")
	public void admin_send_get_http_request_with_endpoint_to_retrieve_patients_by_fiel_id() {
	    response = request.when().get(routes.getString("Get_RetrievePatientfilebyFileId").replace("{fileId}", IdHolder.FileID)).then().log().all().extract().response();

	}

	@Then("Admin recieves {int} Forbidden to retrieve patients by fielId")
	public void admin_recieves_forbidden_to_retrieve_patients_by_fiel_id(Integer int1) {
		assertEquals(response.getStatusCode(),403);
	}

	@Given("Dietician create POST request to retrieve patients by field with invalid method")
	public void dietician_create_post_request_to_retrieve_patients_by_field_with_invalid_method() throws FileNotFoundException, IOException {
		//String dieticianToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNY2xlYW4xMDJAZ21haWwuY29tIiwiaWF0IjoxNzIzOTIxMTg3LCJleHAiOjE3MjM5NDk5ODd9.PpnB1Kqw1aE6S2vAHxokBT89Nk5Q-4ta3cLbHcCuqxkFPTBsoV4sLR0x9pKHruN4edRj9dBmX7F3lw0glTYDuQ"; // Replace with your actual token
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
     
	   
	}

	@When("Dietician send POST http request with endpoint to retrieve patients by field with invalid method")
	public void dietician_send_post_http_request_with_endpoint_to_retrieve_patients_by_field_with_invalid_method() {
		String endpoint = routes.getString("Invalid_Post_Get_RetrievePatientfilebyFileId");
				response = request.when().post(endpoint).then().log().all().extract().response();
	}
	   

	@Then("Dietician recieves {int} method not allowed to retrieve patients by field with invalid method")
	public void dietician_recieves_method_not_allowed_to_retrieve_patients_by_field_with_invalid_method(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("Dietician create GET request to retrieve patients by invalid fileId")
	public void dietician_create_get_request_to_retrieve_patients_by_invalid_file_id() throws FileNotFoundException, IOException {
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
     
	    
	}

	@When("Dietician send GET http request with endpoint to retrieve patients by invalid fileId")
	public void dietician_send_get_http_request_with_endpoint_to_retrieve_patients_by_invalid_file_id() {
		String endpoint = routes.getString("InvalidFileid_Get_RetrievePatientfilebyFileId");
        response = request.when().get(endpoint).then().log().all().extract().response();
	}

	@Then("Dietician recieves {int} not found to retrieve patients by invalid fileId")
	public void dietician_recieves_not_found_to_retrieve_patients_by_invalid_file_id(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	}

	@Given("Dietician create GET request to retrieve patients by field with invalid endpoint")
	public void dietician_create_get_request_to_retrieve_patients_by_field_with_invalid_endpoint()throws FileNotFoundException, IOException {
	
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.Dieticiantoken);
     	   
	}

	@When("Dietician send GET http request to retrieve patients by field with invalid endpoint")
	public void dietician_send_get_http_request_to_retrieve_patients_by_field_with_invalid_endpoint() {
		String endpoint = routes.getString("Invalidendpoint_Get_RetrievePatientfilebyFileId");
        response = request.when().get(endpoint).then().log().all().extract().response();
	}

	@Then("Dietician recieves {int} not found to retrieve patients by field with invalid endpoint")
	public void dietician_recieves_not_found_to_retrieve_patients_by_field_with_invalid_endpoint(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	}















}
