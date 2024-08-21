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

	
		@Given("Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields No Pdf add.")
		public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_into_the_form_data_key_and_value_fields_No_Pdf_add() throws FileNotFoundException {
		    
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
		}

		@When("Dietician send PUT http request with endpoint only valid mandatory details No Pdf add")
		public void dietician_send_put_http_request_with_endpoint_only_valid_mandatory_details_No_Pdf_add() throws IOException {
			
			response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPutdata.ExcelMandatorydataPUT(),"application/json")
					.multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
					
					asString = response.then().log().all().extract().asString();
			
					logger.info("===========Put Patient with valid mandatory details=====================  ");
		}
	
	//-----------------------------------Patient PUT request with valid data(Add pdf file)--------------------------------------//

	@Given("Dietician creates PUT request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields Add pdf file.")
	public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields_Add_pdf_file() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send PUT http request with endpoint with valid data Add pdf file")
	public void dietician_send_put_http_request_with_endpoint_with_valid_data_Add_pdf_file() throws IOException {
	   
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.ValiddataAddPdfPUT() ,"application/json")
				  .multiPart("file", file1 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		asString = response.then().log().all().extract().asString();
		logger.info("===========Put Patient by entering valid data=====================  ");
	}

	@Then("Dietician recieves {int} ok  with updated response body")
	public void dietician_recieves_ok_with_updated_response_body(Integer int1) {
		
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

	//-----------------------------------Patient PUT request entering only valid mandatory details(No Pdf added)--------------------------------------//

	
			@Given("Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields.")
			public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
			    
				request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId2);
			}

			@When("Dietician send PUT http request with endpoint only valid mandatory details")
			public void dietician_send_put_http_request_with_endpoint_only_valid_mandatory_details() throws IOException {
				
				response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPutdata.ExcelMandatorydataPUT(),"application/json")
						  .when().put(routes.getString("Put_UpdatePatient"));
						
						asString = response.then().log().all().extract().asString();
				
			    
			}
		
		//-----------------------------------Patient PUT request with valid data(Add pdf file)--------------------------------------//

		@Given("Dietician creates PUT request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields.")
		public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
			
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId2);
		}

		@When("Dietician send PUT http request with endpoint with valid data")
		public void dietician_send_put_http_request_with_endpoint_with_valid_data() throws IOException {
		   
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.ValiddataAddPdfPUT() ,"application/json")
					  .multiPart("file", file1 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
		}

		@Then("Dietician recieves {int} ok and with updated response body")
		public void dietician_recieves_ok_and_with_updated_response_body(Integer int1) {
			
			assertEquals(response.getStatusCode(),200);
		}


		

		//-----------------------------------Patient PUT request with existing file by not attaching new file(using valid data)--------------------------------------//
		
		@Given("Dietician creates PUT request by not attaching any file into the form-data key and value fields with valid data.")
		public void dietician_creates_put_request_by_not_attaching_any_file_into_the_form_data_key_and_value_fields_with_valid_data() throws FileNotFoundException {
		   
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
		}

		@When("Dietician send PUT http request with endpoint with existing file by not attaching new file with valid data")
		public void dietician_send_put_http_request_with_endpoint_with_existing_file_by_not_attaching_new_file_with_valid_data() throws IOException {
		   
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.ExistingpdffilePUT() ,"application/json")
					  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
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
		
		//--------------------------- Put Operation [Add New Reports with/without Vitals for existing Patient]-------------------
	//-----------------------------------	dietician able to add new reports with vitals

//	@Given("Dietician creates PUT request by entering valid data\\( Mandatory and additional details) into the form-data key and value fields and valid patient ID")
//	public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
//		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId2);
//
//	}
//
//	@When("Dietician send PUT http request with endpoint")
//	public void dietician_send_put_http_request_with_endpoint() throws IOException {
//		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelonlyMandatorydata(),"application/json")
//				  .multiPart("file", file2 ,"application/pdf").multiPart("vitals",PatientPutVitals.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));
//	}

	@Then("Dietician recieves {int} ok and with updated response body.")
	public void dietician_recieves_ok_and_with_updated_response_body2(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	//----------- Dietician Adding Reports without Vitals
	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields except valid vitals data and valid patient ID")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_except_valid_vitals_data_and_valid_patient_id() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);
	}

	@When("Dietician send PUT http request  to the endpoint with valid patient ID")
	public void dietician_send_put_http_request_to_the_endpoint_with_valid_patient_id() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.ExistingpdffilePUT() ,"application/json")
				  .multiPart("file", file1 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient"));
	;
	}

	@Then("Dietician recieves {int} ok and with updated response body contains updated details")
	public void dietician_recieves_ok_and_with_updated_response_body_contains_updated_details(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	//-----------------------Dietician Adding Only Vitals with Report

	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields except file and valid patient ID")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_except_file_and_valid_patient_id() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId2);
	}

	@When("Dietician sends PUT HTTP request to the endpoint with valid patient ID")
	public void dietician_sends_put_http_request_to_the_endpoint_with_valid_patient_id() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelonlyMandatorydata(),"application/json")
				  .multiPart("file", file2 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.ModifyvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));
	}

	@Then("Dietician receives {int} OK and the response body contains updated details")
	public void dietician_receives_ok_and_the_response_body_contains_updated_details(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	//---------------Dietician Adding Only Vitals without Report

	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields except file and valid patient ID without reports for existing patient without existing report")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_except_file_and_valid_patient_id_without_reports_for_existing_patient_without_existing_report() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId2);

	}

	@When("Dietician sends PUT HTTP request to the endpoint with valid patient ID without reports for existing patient without existing report")
	public void dietician_sends_put_http_request_to_the_endpoint_with_valid_patient_id_without_reports_for_existing_patient_without_existing_report() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelonlyMandatorydata(),"application/json")
				  .multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));

	}
	//---------------Dietician Adding Reports with Mandatory Details Only
	@Given("Dietician creates PUT request by entering valid data \\(Mandatory only) into the form-data key and value fields and valid patient ID")
	public void dietician_creates_put_request_by_entering_valid_data_mandatory_only_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId2);

	}

	@When("Dietician sends PUT HTTP request to the endpoint with valid patient ID add new reports with only valid mandatory details")
	public void dietician_sends_put_http_request_to_the_endpoint_with_valid_patient_id_add_new_reports_with_only_valid_mandatory_details() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelonlyMandatorydata(),"application/json")
				  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));
	}

	//-----------------Dietician Adding Reports with Additional Details Only
	@Given("Dietician creates PUT request by entering valid data \\(Additional details only) into the form-data key and value fields and valid patient ID")
	public void dietician_creates_put_request_by_entering_valid_data_additional_details_only_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);

	}

	@When("Dietician sends PUT HTTP request to the endpoint with valid patient ID add new reports with only valid additional details")
	public void dietician_sends_put_http_request_to_the_endpoint_with_valid_patient_id_add_new_reports_with_only_valid_additional_details() throws IOException {
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelValiddata(),"application/json")
				  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));
	}

	//----------Feature: Get Operation [Get all Patients]

	@Given("Dietician creates GET request to the endpoint")
	public void dietician_creates_get_request_to_the_endpoint() throws FileNotFoundException {
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken);

	}

	@When("Dietician sends GET HTTP request to the endpoint with dietician bearer token")
	public void dietician_sends_get_http_request_to_the_endpoint_with_dietician_bearer_token() throws IOException {
		response = request.when().get(routes.getString("Get_GetallPatients")).then().log().all().extract().response();
	}

	@Then("Dietician receives {int} OK with response body containing patient details")
	public void dietician_receives_ok_with_response_body_containing_patient_details(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
		
	//---------------{Get All Patients Negative} No Authorization 	
		@Given("Dietician creates GET request to the endpoint with no authorization")
		public void dietician_creates_get_request_to_the_endpoint_with_no_authorization() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer ");
		}

		@When("Dietician sends GET HTTP request to the endpoint with no authorization")
		public void dietician_sends_get_http_request_to_the_endpoint_with_no_authorization() {
			response = request.when().get(routes.getString("Get_GetallPatients")).then().log().all().extract().response();
		}

		@Then("Dietician receives {int} Unauthorized")
		public void dietician_receives_unauthorized(Integer int1) {
			assertEquals(response.getStatusCode(),401);
		}
		//-------------Admin Token
		@Given("Admin creates GET request to the endpoint")
		public void admin_creates_get_request_to_the_endpoint() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Admintoken);

		}

		@When("Admin sends GET HTTP request to the endpoint with admin bearer token")
		public void admin_sends_get_http_request_to_the_endpoint_with_admin_bearer_token() {
			response = request.when().get(routes.getString("Get_GetallPatients")).then().log().all().extract().response();
		}

		@Then("Admin receives {int} Forbidden")
		public void admin_receives_forbidden(Integer int1) {
			assertEquals(response.getStatusCode(),403);
		}
		
		//-----------Patient Token
		@Given("Patient creates GET request to the endpoint")
		public void patient_creates_get_request_to_the_endpoint() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Patienttoken);

		}

		@When("Patient sends GET HTTP request to the endpoint with patient bearer token")
		public void patient_sends_get_http_request_to_the_endpoint_with_patient_bearer_token() {
			response = request.when().get(routes.getString("Get_GetallPatients")).then().log().all().extract().response();

		}

		@Then("Patient receives {int} Forbidden")
		public void patient_receives_forbidden(Integer int1) {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(response.getStatusCode(),403);
		}
		
		//-----Invalid Method
		@Given("Dietician creates PUT request to the endpoint")
		public void dietician_creates_put_request_to_the_endpoint() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Patienttoken);

		}

		@When("Dietician sends PUT HTTP request to the endpoint with valid data")
		public void dietician_sends_put_http_request_to_the_endpoint_with_valid_data() {
			response = request.when().put(routes.getString("Get_GetallPatients")).then().log().all().extract().response();

		}

		@Then("Dietician receives {int} Method Not Allowed")
		public void dietician_receives_method_not_allowed(Integer int1) {
			assertEquals(response.getStatusCode(),405);
		}
		
		//----Invalid Endpoint
		@Given("Dietician creates GET request to an invalid endpoint")
		public void dietician_creates_get_request_to_an_invalid_endpoint() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Patienttoken);

		}

		@When("Dietician sends GET HTTP request to the invalid endpoint")
		public void dietician_sends_get_http_request_to_the_invalid_endpoint() {
			response = request.when().put(routes.getString("Get_InvalidGetallPatients")).then().log().all().extract().response();

		}

		@Then("Dietician receives {int} Not Found")
		public void dietician_receives_not_found(Integer int1) {
			assertEquals(response.getStatusCode(),404);
		}
		
		//-----------Patient Module Putrequest Negative------ No Authorization
		@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields and valid patient ID")
		public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer ");

		}

		@When("Dietician sends PUT HTTP request to the endpoint with no authorization")
		public void dietician_sends_put_http_request_to_the_endpoint_with_no_authorization() throws IOException {
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelonlyMandatorydata(),"application/json")
					  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));

		}
	//---------------Admin Token
		@Given("Admin creates PUT request by entering valid data into the form-data key and value fields and valid patient ID")
		public void admin_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Admintoken);

		}

		@When("Admin sends PUT HTTP request to the endpoint with admin bearer token")
		public void admin_sends_put_http_request_to_the_endpoint_with_admin_bearer_token() throws IOException {
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelonlyMandatorydata(),"application/json")
					  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));

		}
	//----------------Patient Token
		@Given("Patient creates PUT request by entering valid data into the form-data key and value fields and valid patient ID")
		public void patient_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Patienttoken);

		}

		@When("Patient sends PUT HTTP request to the endpoint with patient bearer token")
		public void patient_sends_put_http_request_to_the_endpoint_with_patient_bearer_token() throws IOException {
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.ExcelonlyMandatorydata(),"application/json")
					  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));

		}

	   // ------------- Dietician Adding Reports with Invalid Data
		
		@Given("Dietician creates PUT request by entering invalid data \\(Additional details only) into the form-data key and value fields and valid patient ID")
		public void dietician_creates_put_request_by_entering_invalid_data_additional_details_only_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);

		}

		@When("Dietician sends PUT HTTP request to the endpoint with valid patient ID and  with invalid data")
		public void dietician_sends_put_http_request_to_the_endpoint_with_valid_patient_id_and_with_invalid_data() throws IOException {
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.onlyinvalidMandatorydataNeg(),"application/json")
					  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));
		}

		@Then("Dietician receives {int} Bad Request")
		public void dietician_receives_bad_request(Integer int1) {
			assertEquals(response.getStatusCode(),404);
		}
		
		//-----------Dietician Adding Reports with Invalid Patient ID
		
		@Given("Dietician creates PUT request by entering valid data \\(Additional details only) into the form-data key and value fields and invalid patient ID")
		public void dietician_creates_put_request_by_entering_valid_data_additional_details_only_into_the_form_data_key_and_value_fields_and_invalid_patient_id() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.invalidpatientId);

		}

		@When("Dietician sends PUT HTTP request to the endpoint with invalid patient ID as path parameter")
		public void dietician_sends_put_http_request_to_the_endpoint_with_invalid_patient_id_as_path_parameter() throws IOException {
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.onlyinvalidMandatorydataNeg(),"application/json")
					  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json").when().put(routes.getString("Put_Updatepatientwithvital/withoutvitals"));

		}
		
		//------------ Dietician Using Invalid Method
		@Given("Dietician creates POST request by entering valid data into the form-data key and value fields and valid patient ID")
		public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);

		}

		@When("Dietician sends POST HTTP request to the endpoint and add new reports using POST method")
		public void dietician_sends_post_http_request_to_the_endpoint_and_add_new_reports_using_post_method() throws IOException {
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.onlyinvalidMandatorydataNeg(),"application/json")
					  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json")
					  .when().post(routes.getString("Put_Updatepatientwithvital/withoutvitals"));

		}
		
		//-----------Dietician Using Invalid Endpoint
		@When("Dietician sends PUT HTTP request to an invalid endpoint and unable to add new reports")
		public void dietician_sends_put_http_request_to_an_invalid_endpoint_and_unable_to_add_new_reports() throws IOException {
			response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPostdata.onlyinvalidMandatorydataNeg(),"application/json")
					  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json")
					  .when().put(routes.getString("Put_invalidUpdatePatient"));
		}
		
		//------------Dietician Using Invalid Content Type
		@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields and valid patient ID with invalid content type")
		public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id_with_invalid_content_type() throws FileNotFoundException {
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId", IdHolder.patientId1);

		}

		@When("Dietician sends PUT HTTP request to the endpoint and  invalid content type")
		public void dietician_sends_put_http_request_to_the_endpoint_and_invalid_content_type() throws IOException {
			response = request.contentType("application/pdf").multiPart("patientInfo",PatientPostdata.onlyinvalidMandatorydataNeg(),"application/json")
					  .multiPart("file", file1 ,"application/pdf").multiPart("vitals",PatientPutVitalsdata.AddPdfAddvitalsPUT(),"application/json")
					  .when().put(routes.getString("Put_invalidUpdatePatient"));
		}

		@Then("Dietician receives {int} Unsupported Media Type")
		public void dietician_receives_unsupported_media_type(Integer int1) {
			assertEquals(response.getStatusCode(),415);
		}



	}









