package api.stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import api.utils.IdHolder;
import api.utils.RestUtils;
import api.pojo.UserDieticianPostPojo;
import api.resourses.UserAdminLogindata;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.resourses.*;


public class UserDieticianPostPositive extends RestUtils {
	int statusCode=201;
	RequestSpecification request;
    Response response;
    
//***POST**//
    
	@Given("Admin creates POST request for Dietician with valid data. \\( Mandatory and additional details)")
	public void admin_creates_post_request_for_dietician_with_valid_data_mandatory_and_additional_details() throws FileNotFoundException {
		UserDieticianPostPojo userDieticianPojo = new UserDieticianPostPojo();
		userDieticianPojo.setContact_Number("1434567897");
		userDieticianPojo.setDate_Of_Birth("2024-07-26T18:14:08.570Z");
		userDieticianPojo.setEducation("Master's in Computer Science");
		userDieticianPojo.setEmail("example9@example.com");
		userDieticianPojo.setFirst_name("Johnsr");
		userDieticianPojo.setHospital_City("CityName9");
		userDieticianPojo.setHospital_Name("HospitalName0");
		userDieticianPojo.setHospital_Pincode("123450");
		userDieticianPojo.setHospital_Street("StreetName0");
		userDieticianPojo.setLast_name("Doese");
		System.out.println("Request Payload: " + userDieticianPojo.toString());
		 request = given().spec(requestSpecification())
                 .header("Authorization", "Bearer " + IdHolder.token)
                 .body(userDieticianPojo);
		 
	//		request=given().spec(requestSpecification()).body( UserAdminLogindata.dataBuild());
	}

	@When("Admin send POST http request with endpoint")
	public void admin_send_post_http_request_with_endpoint() {
		response = request.when().post(routes.getString("Post_CreateDietician")).then().log().all().extract().response();
		System.out.println("Response Status Code: " + response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());
	}

	@Then("Admin recieves {int} created and with response body. \\(Auto created dietician ID and login password)")
	public void admin_recieves_created_and_with_response_body_auto_created_dietician_id_and_login_password(Integer statusCode) {
		
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(),201);
		String responseBody = response.getBody().asString();

		// Parse the response body using JsonPath
		JsonPath jsonPath = new JsonPath(responseBody);

		// Extract the 'id' and 'loginPassword' from the response
		IdHolder.dieticianId = jsonPath.getInt("id");
		String loginPassword = jsonPath.getString("Email");

		// Print the extracted values (Optional)
		System.out.println("Extracted ID: " + IdHolder.dieticianId);
		System.out.println("Extracted Login Password: " + loginPassword);

		
	}
	
	//****Dietician Token****//
	
	@Given("User creates Post request with request body for Dietician token")
	public void user_creates_post_request_with_request_body_for_dietician_token() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User send POST HTTP request with endpoint for Dietician token")
	public void user_send_post_http_request_with_endpoint_for_dietician_token() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User recieves {int} created with response body for Dietician token")
	public void user_recieves_created_with_response_body_for_dietician_token(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
	//******** GET ALL *****//
	
	@Given("Admin create GET request")
	public void admin_create_get_request() throws FileNotFoundException {
		request = given().spec(requestSpecification())
                .header("Authorization", "Bearer " + IdHolder.token);
		 
	}

	@When("Admin send GET http request with endpoint")
	public void admin_send_get_http_request_with_endpoint() {
		   response = request.when().get(routes.getString("Get_GetallPatients")).then().log().all().extract().response();
	        System.out.println("Response Status Code: " + response.getStatusCode());
	        System.out.println("Response Body: " + response.getBody().asString());
	}

	@Then("Admin recieves {int} ok with response body")
	public void admin_recieves_ok_with_response_body(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
	}
	
	//******** GET BY ID *****//
	
	@Given("Given Admin create GET request by the stored dietician Id")
	public void given_admin_create_get_request_by_the_stored_dietician_id() throws FileNotFoundException {
	 request = given().spec(requestSpecification())
	                .header("Authorization", "Bearer " + IdHolder.token)
	                .pathParam("dieticianId", IdHolder.dieticianId);
		 System.out.println("Saranyaaaa"+IdHolder.dieticianId);
	}
	

	@When("Admin send GET http request with endpoint by Id")
	public void admin_send_get_http_request_with_endpoint_by_id() throws FileNotFoundException {
	    // Send the GET request and store the response
	    response = request.when().get(routes.getString("Get_GetDietician_By_Id")).then().log().all().extract().response();

	    // Logging the response
	    System.out.println("Response Status Code: " + response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody().asString());

	}

	@Then("Admin recieves {int} ok with details of the dietician id")
	public void admin_recieves_ok_with_details_of_the_dietician_id(Integer expectedStatusCode) {
		 assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
	}
	
	
	//******** PUT BY ID *****//
	
	@Given("Admin creates PUT request with valid data. \\( Mandatory and additional details)")
	public void admin_creates_put_request_with_valid_data_mandatory_and_additional_details() throws FileNotFoundException {
		 UserDieticianPostPojo userDieticianPojo = new UserDieticianPostPojo();
		    userDieticianPojo.setContact_Number("1434567893");
		    userDieticianPojo.setDate_Of_Birth("2023-06-15T18:14:08.570Z");
		    userDieticianPojo.setEducation("PhD in Nutrition Science");
		    userDieticianPojo.setEmail("example2_update@example.com");
		    userDieticianPojo.setFirst_name("Chloe");
		    userDieticianPojo.setHospital_City("Kirkland");
		    userDieticianPojo.setHospital_Name("OverLake");
		    userDieticianPojo.setHospital_Pincode("543310");
		    userDieticianPojo.setHospital_Street("Seattle");
		    userDieticianPojo.setLast_name("Boldt");
		    System.out.println("Request Payload: " + userDieticianPojo.toString());

		    // Create the PUT request with authorization and body
		    request = given().spec(requestSpecification())
		                     .header("Authorization", "Bearer " + IdHolder.token)
		                     .body(userDieticianPojo)
		                     .pathParam("dieticianId", IdHolder.dieticianId); }

	@When("Admin send PUT http request with endpoint")
	public void admin_send_put_http_request_with_endpoint() {
		  response = request.when().put("/dietician/{dieticianId}");  // Replace with the correct endpoint
		    System.out.println("Response Status Code: " + response.getStatusCode());
		    System.out.println("Response Body: " + response.getBody().asString());
		}


	@Then("Admin recieves {int} ok and with updated response body.")
	public void admin_recieves_ok_and_with_updated_response_body(Integer expectedStatusCode) {
	    // Write code here that turns the phrase above into concrete actions
		 assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
	}
	//******** DELETE BY ID *****//

		@Given("Admin create DELETE request")
		public void admin_create_delete_request() throws FileNotFoundException {
			
			  request = given().spec(requestSpecification())
			            .header("Authorization", "Bearer " + IdHolder.token)
			            .pathParam("dieticianId", IdHolder.dieticianId);  // Replace with appropriate dieticianId
		}

		@When("Admin send DELETE http request with endpoint")
		public void admin_send_delete_http_request_with_endpoint() {
			 response = request.when().delete("/dietician/{dieticianId}");  // Replace with the correct endpoint
			    System.out.println("Response Status Code: " + response.getStatusCode());
			    System.out.println("Response Body: " + response.getBody().asString());
		}

		@Then("Admin recieves {int} ok with details of the dietician id by deleting it")
		public void admin_recieves_ok_with_details_of_the_dietician_id_by_deleting_it(Integer expectedStatusCode) {
			 assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
		}





}
