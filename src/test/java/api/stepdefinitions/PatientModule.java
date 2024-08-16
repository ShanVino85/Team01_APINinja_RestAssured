package api.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import api.resourses.*;
import api.utils.IdHolder;
import api.utils.RestUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientModule extends RestUtils {
	
	PatientPostdata PatientPostdata= new PatientPostdata(); 
	// ObjectMapper OM = new ObjectMapper();
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	public static String token;
	String asString;
	String Json1;
	
	File file1=new File("C:\\Users\\shanj\\vino-workspace\\Team01_API_Ninja\\src\\test\\resources\\HypoThyroid-Report.pdf");
	
	
	
	/* String patientInfo = "{\n" +
             "\"firstName\": \"John\",\n" +
             "\"LastName\": \"Doe\",\n" +
             "\"ContactNumber\": \"1234567885\",\n" +
             "\"Email\": \"john.doe82@example.com\",\n" +
             "\"Allergy\": \"SOY\",\n" +
             "\"FoodPreference\": \"Vegan\",\n" +
             "\"CuisineCategory\": \"Indian\",\n" +
             "\"DateOfBirth\": \"1981-02-14\"\n" +
             "}";*/
	@Given("Set dietician bearer token")
	public void set_dietician_bearer_token() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}
	
	 
  @Given("Dietician creates POST request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields.")
	public void dietician_creates_post_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		
	}

	@When("Dietician send POST http request with endpoint")
	public void dietician_send_post_http_request_with_endpoint() throws IOException {
	
		//Json1= OM.writeValueAsString(PatientPostdata.Exceldata());
		//response = request.contentType("multipart/form-data").multiPart("patientInfo", patientInfo,"application/json")
		//response = request.contentType("multipart/form-data").multiPart("patientInfo",Json1,"application/json")	
		//asString = response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/PatientModule/PostreqPatient.json")).log().all().extract().asString();
		
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.Exceldata(),"application/json")
		  .multiPart("file", file1 ,"application/pdf").when().post(routes.getString("Post_Createpatient"));
		
		asString = response.then().log().all().extract().asString();
		
			IdHolder.patientId =  UserKeyJson(response,"patientId");
			  System.out.println("patientId ="  +IdHolder.patientId);
			  
			  IdHolder.patientEmail =  UserKeyJson(response,"Email");
			  System.out.println("patientEmail ="  +IdHolder.patientEmail);
			  
			  
	}

	@Then("Dietician recieves {int} created and with response body. \\(Auto created dietician ID and login password)")
	public void dietician_recieves_created_and_with_response_body_auto_created_dietician_id_and_login_password(Integer int1) {
		assertEquals(response.getStatusCode(),201);
	}

//	@Given("Dietician creates POST request only by valid mandatory details into the form-data key and value fields.")
//	public void dietician_creates_post_request_only_by_valid_mandatory_details_into_the_form_data_key_and_value_fields() {
//	    
//	}
	
	
	@Given("User creates Patient Post request with request body")
	public void user_creates_patient_post_request_with_request_body() throws FileNotFoundException, IOException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken)
				.body(PatientPostdata.Patienttokencreation(IdHolder.patientEmail) );
	}

	@When("User send Patient POST HTTP request with endpoint")
	public void user_send_patient_post_http_request_with_endpoint() {
	   
		 response = request
				 .when().post(routes.getString("Post_UserPatientLoginurl")).then()
				// .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/UserModule/PostPatient.json"))
				 .log().all().extract().response();
			
			IdHolder.Patienttoken =  UserKeyJson(response,"token");
			  System.out.println("Patienttoken ="  +IdHolder.Patienttoken);
	}

	@Then("Patient recieves {int} created with response body")
	public void patient_recieves_created_with_response_body(Integer int1) {
	    
		assertEquals(response.getStatusCode(),200);
	}

	@Given("Dietician creates PUT request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields.")
	public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields() {
		
	}

	@When("Dietician send PUT http request with endpoint")
	public void dietician_send_put_http_request_with_endpoint() {
		
	}

	@Then("Dietician recieves {int} ok and with updated response body.")
	public void dietician_recieves_ok_and_with_updated_response_body(Integer int1) {
		
	}

//	@Given("Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields.")
//	public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_into_the_form_data_key_and_value_fields() {
//	    
//	}

	@Given("Patient create GET request")
	public void patient_create_get_request() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId);
	}

	@When("Patient send GET http request with endpoint")
	public void patient_send_get_http_request_with_endpoint() {
		response = request.when().get(routes.getString("Get_GetPatientsMorbidityDetails")).then()
				 .log().all().extract().response();
		
		IdHolder.fileIds =  UserKeyJson(response,"fileId");
		  System.out.println("fileIds ="  +IdHolder.fileIds);
	}

	@Then("Patient recieves {int} ok with details of the patient id")
	public void patient_recieves_ok_with_details_of_the_patient_id(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}


}
