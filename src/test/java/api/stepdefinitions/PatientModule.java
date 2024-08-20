package api.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.resourses.*;
import api.utils.IdHolder;
import api.utils.RestUtils;


public class PatientModule extends RestUtils {
	Logger logger = LogManager.getLogger("PatientModule.java");
	PatientPostdata PatientPostdata= new PatientPostdata(); 
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	public static String token;
	String asString;
	String Json1;
	
	File file1=new File("C:\\Users\\shanj\\vino-workspace\\Team01_API_Ninja\\src\\test\\resources\\Samplefile\\HypoThyroid-Report.pdf");
	File file2=new File("C:\\Users\\shanj\\vino-workspace\\Team01_API_Ninja\\src\\test\\resources\\Samplefile\\HyperThyroid_Report.pdf");
	File file3=new File("C:\\Users\\shanj\\vino-workspace\\Team01_API_Ninja\\src\\test\\resources\\Samplefile\\DiabeticandHemogramTest_Thyrocarelab.pdf");
	
	
	
	@Given("Set dietician bearer token")
	public void set_dietician_bearer_token() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}
	
	 //-----------------------------------Patient Post creation entering valid data(Pdf file added)--------------------------------------//
	
   @Given("Dietician creates POST request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields.")
	public void dietician_creates_post_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		
	}

	@When("Dietician send POST http request with endpoint")
	public void dietician_send_post_http_request_with_endpoint() throws IOException {
	
		//asString = response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/PatientModule/PostreqPatient.json")).log().all().extract().asString();
		
		logger.info("===========POST Patient creation  with endpoint=====================  ");
		
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.ExcelValiddata(),"application/json")
		  .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Post_Createpatient"));
		
		asString = response.then().log().all().extract().asString();
		
			IdHolder.patientId1 =  UserKeyJson(response,"patientId");
			  System.out.println("patientId ="  +IdHolder.patientId1);
			  
			  IdHolder.patientEmail =  UserKeyJson(response,"Email");
			  System.out.println("patientEmail ="  +IdHolder.patientEmail);
			  
		}

   @Then("Dietician recieves {int} created and with response body. \\(Auto created dietician ID and login password)")
	public void dietician_recieves_created_and_with_response_body_auto_created_dietician_id_and_login_password(Integer int1) {
		assertEquals(response.getStatusCode(),201);
	}

   //-----------------------------------Patient Post creation entering only by valid mandatory details(No pdf file added)--------------------------------------//
   
	@Given("Dietician creates POST request only by valid mandatory details into the form-data key and value fields.")
	public void dietician_creates_post_request_only_by_valid_mandatory_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
	    
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}
	
	@When("Dietician send POST http request with endpoint	only with valid mandatory details")
	public void dietician_send_post_http_request_with_endpoint_only_with_valid_mandatory_details() throws IOException {
	   
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.ExcelonlyMandatorydata(),"application/json")
				  .when().post(routes.getString("Post_Createpatient"));
				
				asString = response.then().log().all().extract().asString();
				
				IdHolder.patientId2 =  UserKeyJson(response,"patientId");
				  System.out.println("patientId ="  +IdHolder.patientId2);
				  logger.info("===========POST Patient request with valid mandatory details=====================  ");
	}
	
	
	//-----------------------------------Patient Token creation--------------------------------------//
	
	@Given("User creates Patient Post request with request body")
	public void user_creates_patient_post_request_with_request_body() throws FileNotFoundException, IOException {
		
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken)
				.body(PatientPostdata.Patienttokencreation(IdHolder.patientEmail) );
	}

	@When("User send Patient POST HTTP request with endpoint")
	public void user_send_patient_post_http_request_with_endpoint() {
		logger.info("===========POST Patient Token creation=====================  ");
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
	
	//-----------------------------------Patient PUT request entering only valid mandatory details(No Pdf added)--------------------------------------//

	
		@Given("Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields.")
		public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		    
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
		}

		@When("Dietician send PUT http request with endpoint only valid mandatory details")
		public void dietician_send_put_http_request_with_endpoint_only_valid_mandatory_details() throws IOException {
			
			response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPutdata.ExcelMandatorydataPUT(),"application/json")
					.multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
					
					asString = response.then().log().all().extract().asString();
			
					logger.info("===========Put Patient with valid mandatory details=====================  ");
		}
	
	//-----------------------------------Patient PUT request with valid data(Add pdf file)--------------------------------------//

	@Given("Dietician creates PUT request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields.")
	public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send PUT http request with endpoint with valid data")
	public void dietician_send_put_http_request_with_endpoint_with_valid_data() throws IOException {
	   
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.ValiddataAddPdfPUT() ,"application/json")
				  .multiPart("file", file1 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		logger.info("===========Put Patient by entering valid data=====================  ");
	}

	@Then("Dietician recieves {int} ok and with updated response body")
	public void dietician_recieves_ok_and_with_updated_response_body(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
	}


	

	//-----------------------------------Patient PUT request with existing file by not attaching new file(using valid data)--------------------------------------//
	
	@Given("Dietician creates PUT request by not attaching any file into the form-data key and value fields.")
	public void dietician_creates_put_request_by_not_attaching_any_file_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
	   
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send PUT http request with endpoint with existing file by not attaching new file")
	public void dietician_send_put_http_request_with_endpoint_with_existing_file_by_not_attaching_new_file() throws IOException {
	   
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.ExistingpdffilePUT() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		logger.info("===========Put Patient with existing file by not attaching new file=====================  ");
	}
	
	//=============================================Put with Vitals====================================================================================
	
	@Given("Dietician creates PUT request by entering valid data\\( Mandatory and additional details) into the form-data key and value fields and valid patient ID")
	public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
	    
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId2);
	}

	@When("Dietician send PUT http request with endpoint by add new reports with vitals for existing patient with valid data")
	public void dietician_send_put_http_request_with_endpoint_by_add_new_reports_with_vitals_for_existing_patient_with_valid_data() throws IOException {
		
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelonlyMandatorydata() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT() ,"application/json")
				  .when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));
		
		asString = response.then().log().all().extract().asString();
		
		logger.info("===========Put Patient with Vitals=====================  ");
	}

	

	
	
	//----------------------------------GET Request---------------------------------//

	/*@Given("Patient create GET request")
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
	}*/
	
	//==========================================================POST Negative Test cases==========================================================================//
	
	
	
	@Given("Dietician creates POST request only by valid additional details into the form-data key and value fields.")
	public void dietician_creates_post_request_only_by_valid_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}

	@When("Dietician send POST http request with endpoint only for valid additional details")
	public void dietician_send_post_http_request_with_endpoint_only_for_valid_additional_details() throws IOException {
	    
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.OnlyValiddataNeg(),"application/json")
				  .when().post(routes.getString("Post_Createpatient"));
				
				asString = response.then().log().all().extract().asString();
				
				logger.info("===========Post Patient by valid additional details Negative =====================  ");
	}

	@Then("Dietician recieves {int} Bad request")
	public void dietician_recieves_bad_request(Integer int1) {
		assertEquals(response.getStatusCode(),400);
	}

	@Given("Dietician creates POST request only by invalid mandatory details into the form-data key and value fields.")
	public void dietician_creates_post_request_only_by_invalid_mandatory_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}

	@When("Dietician send POST http request with endpoint only for invalid mandatory details")
	public void dietician_send_post_http_request_with_endpoint_only_for_invalid_mandatory_details() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.onlyinvalidMandatorydataNeg(),"application/json")
				 .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Post_Createpatient"));
				
				asString = response.then().log().all().extract().asString();
				
				String errorMessage =  UserKeyJson(response,"errorMessage");
				  System.out.println("errorMessage ="  + errorMessage);
				  logger.info("===========Post Patient by invalid mandatory details Negative =====================  ");
	}

	@Given("Dietician creates POST request only by invalid additional details into the form-data key and value fields.")
	public void dietician_creates_post_request_only_by_invalid_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}

	@When("Dietician send POST http request with endpoint for valid mandatory fields and invalid additional details")
	public void dietician_send_post_http_request_with_endpoint_for_valid_mandatory_fields_and_invalid_additional_details() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.validManinvalidAddNeg(),"application/json")
				 .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Post_Createpatient"));
				
				asString = response.then().log().all().extract().asString();
				
				String errorMessage =  UserKeyJson(response,"errorMessage");
				  System.out.println("errorMessage ="  + errorMessage);
				  logger.info("===========Post Patient by invalid additional details Negative =====================  ");
	}

	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields.")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}

	@When("Dietician send PUT http request with endpoint")
	public void dietician_send_put_http_request_with_endpoint() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.validManinvalidAddNeg(),"application/json")
				 .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Post_Createpatient"));
				
				asString = response.then().log().all().extract().asString();
				String error =  UserKeyJson(response,"errorMessage");
				  System.out.println("errorMessage ="  + error);
				  
				  logger.info("===========Post Patient by invalid Method Negative =====================  ");
	}

	@Then("Dietician recieves {int} method not allowed")
	public void dietician_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
    }

	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields.")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}

	@When("Dietician sent POST http request with invalid endpoint")
	public void dietician_sent_post_http_request_with_invalid_endpoint() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.validManinvalidAddNeg(),"application/json")
				 .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Post_invalidCreatepatient"));
				
				asString = response.then().log().all().extract().asString();
				String error =  UserKeyJson(response,"errorMessage");
				  System.out.println("errorMessage ="  + error);
				  
				  logger.info("===========Post Patient by invalid Enpoint Negative =====================  ");
	}

	@Then("Dietician recieves {int} not found")
	public void dietician_recieves_not_found(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	}

	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields and invalid content type")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_invalid_content_type() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}

	@When("Dietician send POST http request with endpoint and invalid content type")
	public void dietician_send_post_http_request_with_endpoint_and_invalid_content_type() throws IOException {
		response = request.contentType("application/pdf").multiPart("patientInfo", PatientPostdata.validManinvalidAddNeg(),"application/json")
				 .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Post_invalidCreatepatient"));
				
				asString = response.then().log().all().extract().asString();
				String error =  UserKeyJson(response,"errorMessage");
				  System.out.println("errorMessage ="  + error);
				  
				  logger.info("===========Post Patient by invalid content type Negative =====================  ");
	}

	@Then("Dietician recieves {int} unsupported media type")
	public void dietician_recieves_unsupported_media_type(Integer int1) {
		assertEquals(response.getStatusCode(),415);
	}
	
	@Given("Dietician creates POST request by entering Existing data into the form-data key and value fields")
	public void dietician_creates_post_request_by_entering_existing_data_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);
	}

	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields and sets no auth")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_sets_no_auth() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer ");
		 logger.info("===========Post Patient with no auth Negative =====================  ");
	}

	@Then("Dietician recieves {int} unauthorized")
	public void dietician_recieves_unauthorized(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}

	@Given("Admin creates POST request by entering valid data into the form-data key and value fields.")
	public void admin_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Admintoken);
	}

	@When("Admin send POST http request with endpoint")
	public void admin_send_post_http_request_with_endpoint() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.ExcelValiddata(),"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Post_Createpatient"));
				
				asString = response.then().log().all().extract().asString();
				logger.info("===========Post Patient with Admin Token Negative =====================  ");
	}

	@Then("Admin recieves {int} forbidden")
	public void admin_recieves_forbidden(Integer int1) {
		assertEquals(403,response.getStatusCode());
	}

	@Given("Patient creates POST request by entering valid data into the form-data key and value fields.")
	public void patient_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request = given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Patienttoken);
	}

	@When("Patient send POST http request with endpoint")
	public void patient_send_post_http_request_with_endpoint() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.ExcelValiddata(),"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Post_Createpatient"));
				
				asString = response.then().log().all().extract().asString();
				
				logger.info("===========Post Patient with Patient Token Negative =====================  ");
	}

	@Then("Patient recieves {int} forbidden")
	public void patient_recieves_forbidden(Integer int1) {
		assertEquals(response.getStatusCode(),403);
	}
	
	//==========================================================PUT Negative Test cases==========================================================================//
	
	@Given("Dietician creates PUT request by entering only valid additional details into the form-data key and value fields.")
	public void dietician_creates_put_request_by_entering_only_valid_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send PUT http request with endpoint by mandatory fields empty  only valid additional details")
	public void dietician_send_put_http_request_with_endpoint_by_mandatory_fields_empty_only_valid_additional_details() throws IOException {
		
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.onlyaddfieldPUTNeg() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		logger.info("===========Put Patient by mandatory fields empty and only valid additional details Negative =====================  ");
	}

	@Given("Dietician creates PUT request by entering only invalid additional details into the form-data key and value fields.")
	public void dietician_creates_put_request_by_entering_only_invalid_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send PUT http request with endpoint by only invalid additional details")
	public void dietician_send_put_http_request_with_endpoint_by_only_invalid_additional_details() throws IOException {
		
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.onlyinvalidaddfieldPUTNeg() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		String error =  UserKeyJson(response,"errorMessage");
		  System.out.println("errorMessage ="  + error);
		  
		  logger.info("===========Put Patient only invalid additional details Negative =====================  ");
	}

	@Given("Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields with invalid patient id")
	public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_into_the_form_data_key_and_value_fields_with_invalid_patient_id() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.invalidpatientId);
	}

	@When("Dietician send PUT http request with endpoint by valid data and invalid patient id")
	public void dietician_send_put_http_request_with_endpoint_by_valid_data_and_invalid_patient_id() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.onlyinvalidaddfieldPUTNeg() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		String error =  UserKeyJson(response,"errorMessage");
		  System.out.println("errorMessage ="  + error);
		  logger.info("===========Put Patient by valid data and invalid patient id Negative =====================  ");
	}
	
	@Given("Dietician create PUT request by entering valid data into the form-data key and value fields")
	public void dietician_create_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}
	
	@When("Dietician send POST http request with endpoint entering valid data invalid method")
	public void dietician_send_post_http_request_with_endpoint_entering_valid_data_invalid_method() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.onlyinvalidaddfieldPUTNeg() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		String error =  UserKeyJson(response,"error");
		  System.out.println("errorMessage ="  + error);
		  
		  logger.info("===========Put Patient by entering valid data invalid method Negative =====================  ");
	}


	@When("Dietician sent PUT http request with invalid endpoint")
	public void dietician_sent_put_http_request_with_invalid_endpoint() throws IOException {
		
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.onlyinvalidaddfieldPUTNeg() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_invalidUpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		 logger.info("===========Put Patient with invalid endpoint Negative =====================  ");
	}

	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_invalid_content_type() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send PUT http request with endpoint and invalid content type")
	public void dietician_send_put_http_request_with_endpoint_and_invalid_content_type() throws IOException {
		
		response = request.contentType("multipart/pdf").multiPart("patientInfo",PatientPutdata.onlyinvalidaddfieldPUTNeg(),"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		String error =  UserKeyJson(response,"error");
		  System.out.println("errorMessage ="  + error);
		  logger.info("===========Put Patient with invalid content type Negative =====================  ");
	}

	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields and sets no auth")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_sets_no_auth() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer ").pathParam("patientId", IdHolder.patientId1);
	}
	
	@When("Dietician send PUT http request with endpoint for sets no auth")
	public void dietician_send_put_http_request_with_endpoint_for_sets_no_auth() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.onlyinvalidaddfieldPUTNeg() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		 logger.info("===========Put Patient sets no auth Negative =====================  ");
	}

	@Given("Admin creates PUT request by entering valid data into the form-data key and value fields.")
	public void admin_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Admintoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Admin send PUT http request with endpoint")
	public void admin_send_put_http_request_with_endpoint() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.onlyinvalidaddfieldPUTNeg() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_invalidUpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		 logger.info("===========Put Patient sets Admintoken Negative =====================  ");
	}

	@Given("Patient creates PUT request by entering valid data into the form-data key and value fields.")
	public void patient_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Patienttoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Patient send PUT http request with endpoint")
	public void patient_send_put_http_request_with_endpoint() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.onlyinvalidaddfieldPUTNeg() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_invalidUpdatePatient"));
		asString = response.then().log().all().extract().asString();
		
		logger.info("===========Put Patient sets Patienttoken Negative =====================  ");
	}

//=========================================================Patient Token creation Negative==============================================================
	@Given("Patient creates Post request with invalid credential")
	public void patient_creates_post_request_with_invalid_credential() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken)
				.body(PatientPostdata.Patienttokencreation(IdHolder.invalidpatientEmail) );
	}

	@When("Patient send POST HTTP request with invalid credential and endpoint")
	public void patient_send_post_http_request_with_invalid_credential_and_endpoint() {
		response = request
				 .when().post(routes.getString("Post_UserPatientLoginurl")).then()
				 .log().all().extract().response();
		String error =  UserKeyJson(response,"errorCode");
		  System.out.println("errorMessage ="  + error);
		  
		  logger.info("===========Patient token creation with invalid credential Negative =====================  ");
	}

	@Then("Patient recieves {int} unauthorized")
	public void patient_recieves_unauthorized(Integer int1) {
		assertEquals(response.getStatusCode(),401);
	}

	@Given("Patient creates GET request with request body.")
	public void patient_creates_get_request_with_request_body() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken)
				.body(PatientPostdata.Patienttokencreation(IdHolder.patientEmail) );
	}

	@When("Patient send GET HTTP request with endpoint")
	public void patient_send_get_http_request_with_endpoint() {
		response = request
				 .when().get(routes.getString("Post_UserPatientLoginurl")).then()
				 .log().all().extract().response();
		 logger.info("===========Patient token creation with invalid Method Negative =====================  ");
	}

	@Then("Patient recieves {int} method not allowed")
	public void patient_recieves_method_not_allowed(Integer int1) {
		assertEquals(response.getStatusCode(),405);
	}

	@Given("Patient creates Post request with request body.")
	public void patient_creates_post_request_with_request_body() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken)
				.body(PatientPostdata.Patienttokencreation(IdHolder.patientEmail) );
		
		 logger.info("===========Patient token creation with invalid Enpoint Negative =====================  ");
	}

	@When("Patient send POST HTTP request with invalid endpoint")
	public void patient_send_post_http_request_with_invalid_endpoint() {
		response = request
				 .when().post(routes.getString("Post_invalidUserPatientLoginurl")).then()
				 .log().all().extract().response();
	}
	
	@Then("Patient recieves {int} notfound")
	public void patient_recieves_notfound(Integer int1) {
		assertEquals(response.getStatusCode(),404);
	}

	@Given("Patient creates Post request with request body and invalid content type.")
	public void patient_creates_post_request_with_request_body_and_invalid_content_type() throws FileNotFoundException {
		request=given().spec(requestSpecification()).contentType(ContentType.HTML).header("Authorization", "Bearer "+IdHolder.Dieticiantoken)
				.body(PatientPostdata.Patienttokencreation(IdHolder.patientEmail) );
		
		 logger.info("===========Patient token creation with invalid content type Negative =====================  ");
	}

	@When("Patient send POST HTTP request with invalid content type endpoint")
	public void patient_send_post_http_request_with_invalid_content_type_endpoint() {
		response = request
				 .when().post(routes.getString("Post_UserPatientLoginurl")).then()
				 .log().all().extract().response();
	}

	@Then("Patient recieves {int} unsupported media type")
	public void patient_recieves_unsupported_media_type(Integer int1) {
		assertEquals(response.getStatusCode(),415);
	}



}
