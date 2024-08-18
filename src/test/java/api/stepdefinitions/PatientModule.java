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
		
		response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPostdata.ExcelValiddata(),"application/json")
		  .multiPart("file", file2 ,"application/pdf").when().post(routes.getString("Post_Createpatient"));
		
		asString = response.then().log().all().extract().asString();
		
			IdHolder.patientId1 =  UserKeyJson(response,"patientId");
			  System.out.println("patientId1 ="  +IdHolder.patientId1);
			  
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
				  System.out.println("patientId2 ="  +IdHolder.patientId2);
	}
	
	
	//-----------------------------------Patient Token creation--------------------------------------//
	
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
	
	//-----------------------------------Patient PUT request entering only valid mandatory details(No Pdf added)--------------------------------------//

	
		@Given("Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields.")
		public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		    
			request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId2", IdHolder.patientId2);
		}

		@When("Dietician send PUT http request with endpoint only valid mandatory details")
		public void dietician_send_put_http_request_with_endpoint_only_valid_mandatory_details() throws IOException {
			
			response = request.contentType("multipart/form-data").multiPart("patientInfo", PatientPutdata.ExcelMandatorydataPUT(),"application/json")
					  .when().put(routes.getString("Put_UpdatePatient2"));
					
					asString = response.then().log().all().extract().asString();
			
		    
		}
	
	//-----------------------------------Patient PUT request with valid data(Add pdf file)--------------------------------------//

	@Given("Dietician creates PUT request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields.")
	public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
		
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId2", IdHolder.patientId2);
	}

	@When("Dietician send PUT http request with endpoint with valid data")
	public void dietician_send_put_http_request_with_endpoint_with_valid_data() throws IOException {
	   
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.ValiddataAddPdfPUT() ,"application/json")
				  .multiPart("file", file1 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient2"));
	}

	@Then("Dietician recieves {int} ok and with updated response body")
	public void dietician_recieves_ok_and_with_updated_response_body(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
	}


	

	//-----------------------------------Patient PUT request with existing file by not attaching new file(using valid data)--------------------------------------//
	
	@Given("Dietician creates PUT request by not attaching any file into the form-data key and value fields.")
	public void dietician_creates_put_request_by_not_attaching_any_file_into_the_form_data_key_and_value_fields() throws FileNotFoundException {
	   
		request=given().spec(requestSpecification()).header("Authorization", "Bearer "+IdHolder.Dieticiantoken).pathParam("patientId1", IdHolder.patientId1);
	}

	@When("Dietician send PUT http request with endpoint with existing file by not attaching new file")
	public void dietician_send_put_http_request_with_endpoint_with_existing_file_by_not_attaching_new_file() throws IOException {
	   
		response = request.contentType("multipart/form-data").multiPart("patientInfo",PatientPutdata.ExistingpdffilePUT() ,"application/json")
				  .multiPart("file", file2 ,"application/pdf").when().put(routes.getString("Put_UpdatePatient1"));
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


}
