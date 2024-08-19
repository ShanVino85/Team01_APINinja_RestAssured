package api.stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import api.utils.IdHolder;
import api.utils.RestUtils;
import api.pojo.UserDieticianPostPojo;
import api.resourses.DietitianPostdata;
import api.resourses.UserAdminLogindata;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserDieticianPositive extends RestUtils {
	
	RequestSpecification request;
    Response response;
    
	//************Put By Id///
    @Given("Admin creates PUT request with valid data {string},{int}")
    public void admin_creates_put_request_with_valid_data(String sheetname, Integer rownum)throws FileNotFoundException, IOException {

   
		// boolean isPutRequest = requestType.equalsIgnoreCase("PUT");
	//	UserDieticianPostPojo dietitianData = DietitianPostdata.getDietitianData(sheetname, rownum,isPutRequest);
    	UserDieticianPostPojo dietitianData = DietitianPostdata.getDietitianData(sheetname, rownum);
    	
		System.out.println("Data loaded from Excel: " + dietitianData);

		// Create the request and log the details
		request = given()
					.spec(requestSpecification())    // Specify the request specification
					.body(dietitianData)             // Set the request body
					.header("Authorization", "Bearer " + IdHolder.token)  // Add the authorization header
					.pathParam("dieticianId", "618")  // Set the path parameter
					.log().all();                    // Log all details of the request
		 
		       // request.pathParam("dieticianId", "618"); // Use the dietician ID for PUT requests
		   

		// The actual sending of the request will be done later in the step
		System.out.println("Created PUT request with body and logged details above.");
    }
 

	@When("Admin sends PUT {string} HTTP request with endpoint")
	public void admin_sends_put_http_request_with_endpoint(String string) {
		response = request.when().put("/dietician/{dieticianId}"); 
		 System.out.println("Response Status Code: " + response.getStatusCode());
		    System.out.println("Response Body: " + response.getBody().asString());
	}


	@Then("Admin receives {int} OK and with updated response body for dietitian")
	public void admin_receives_ok_and_with_updated_response_body_for_dietitian(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
	}
	
	//********************Get By Dietician Id//
	
	@Given("Given Admin create GET request by the stored dietician Id")
	public void given_admin_create_get_request_by_the_stored_dietician_id() throws FileNotFoundException {
		 request = given().spec(requestSpecification())
	                .header("Authorization", "Bearer " + IdHolder.token)
	                .pathParam("dieticianId", IdHolder.dieticianId);
		 System.out.println("Saranyaaaa"+IdHolder.dieticianId);
	}

	@When("Admin send GET http request with endpoint by Id")
	public void admin_send_get_http_request_with_endpoint_by_id() {
		 response = request.when().get(routes.getString("Get_GetDietician_By_Id")).then().log().all().extract().response();
	}

	@Then("Admin recieves {int} ok with details of the dietician id")
	public void admin_recieves_ok_with_details_of_the_dietician_id(Integer expectedStatusCode) {
		 assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
	}
//********************Delete By Id//
	@Given("Admin create DELETE request")
	public void admin_create_delete_request() throws FileNotFoundException {
		  request = given().spec(requestSpecification())
		            .header("Authorization", "Bearer " + IdHolder.token)
		            .pathParam("dieticianId", IdHolder.dieticianId);  // Replace with appropriate dieticianId
	
	}

	@When("Admin send DELETE http request with endpoint")
	public void admin_send_delete_http_request_with_endpoint() {
		response = request.when().delete("/dietician/{dieticianId}"); 
	}

	@Then("Admin recieves {int} ok with details of the dietician id by deleting it")
	public void admin_recieves_ok_with_details_of_the_dietician_id_by_deleting_it(Integer expectedStatusCode) {
		assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
	}
	

///%%%%%%%%%%%%%%%%Negative//
//########Get By Id Negative//
@Given("Admin create POST request by the stored dietician Id for negative")
public void admin_create_post_request_by_the_stored_dietician_id_for_negative() throws FileNotFoundException {
	request = given().spec(requestSpecification())
            .header("Authorization", "Bearer " + IdHolder.token)
            .pathParam("dieticianId", IdHolder.dieticianId);
 System.out.println("Saranyaaaa"+IdHolder.dieticianId);
}

@When("Admin send Post http request with endpoint by Id for negative")
public void admin_send_post_http_request_with_endpoint_by_id_for_negative() {
	response = request.when().get(routes.getString("Post_CreateDietician")).then().log().all().extract().response();
}

@Then("Admin recieves {int} method not allowed")
public void admin_recieves_method_not_allowed(Integer expectedStatusCode) {
	assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
}

//invalid id
@Given("Admin create Get request by the stored dietician Id for negative by invalid id")
public void admin_create_get_request_by_the_stored_dietician_id_for_negative_by_invalid_id() throws FileNotFoundException {
	request = given().spec(requestSpecification())
            .header("Authorization", "Bearer " + IdHolder.token)
            .pathParam("dieticianId", IdHolder.invalid_DieticianId);
 System.out.println("Saranyaaaa"+IdHolder.dieticianId);
}

@When("Admin send Post http request with endpoint by Id for negative by invalid id")
public void admin_send_post_http_request_with_endpoint_by_id_for_negative_by_invalid_id() {
	response = request.when().get(routes.getString("Get_GetDietician_By_Id")).then().log().all().extract().response();
}

@Then("Admin recieves {int} method not allowed by invalid id")
public void admin_recieves_method_not_allowed_by_invalid_id(Integer expectedStatusCode) {
	assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
}

//invalid endpoint

@Given("Admin create Get request by the stored dietician Id for negative by endpoint")
public void admin_create_get_request_by_the_stored_dietician_id_for_negative_by_endpoint() throws FileNotFoundException {
	request = given().spec(requestSpecification())
            .header("Authorization", "Bearer " + IdHolder.token)
            .pathParam("dieticianId", IdHolder.dieticianId);
 System.out.println("Saranyaaaa"+IdHolder.dieticianId);
}

@When("Admin send Post http request with endpoint by Id for negative by endpoint")
public void admin_send_post_http_request_with_endpoint_by_id_for_negative_by_endpoint() {
	response = request.when().get(routes.getString("Invalid_Get_By_Id")).then().log().all().extract().response();}

@Then("Admin recieves {int} method not allowed by endpoint")
public void admin_recieves_method_not_allowed_by_endpoint(Integer expectedStatusCode) {
	assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
}

//^^^^^^^^^^^^^^Delete By Id//
//Delete by post//
@Given("Admin create POST request for delete Dietician")
public void admin_create_post_request_for_delete_dietician() throws FileNotFoundException {
	 request = given().spec(requestSpecification())
	            .header("Authorization", "Bearer " + IdHolder.token)
	            .pathParam("dieticianId", IdHolder.dieticianId);  // Replace with appropriate dieticianId

}

@When("Admin send DELETE http request with endpoint for delete Dietician")
public void admin_send_delete_http_request_with_endpoint_for_delete_dietician() {
	response = request.when().post("Post_CreateDietician"); 
}

@Then("Admin recieves {int} method not allowed for delete Dietician")
public void admin_recieves_method_not_allowed_for_delete_dietician(Integer expectedStatusCode) {
	assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
}

//Delete by invalid id//


@Given("Admin create DELETE request for delete Dietician by invalid Id")
public void admin_create_delete_request_for_delete_dietician_by_invalid_id() throws FileNotFoundException {
	 request = given().spec(requestSpecification())
	            .header("Authorization", "Bearer " + IdHolder.token)
	            .pathParam("dieticianId", IdHolder.invalid_DieticianId);  // Replace with appropriate dieticianId
}

@When("Admin send DELETE http request with endpoint for delete Dietician by invalid Id")
public void admin_send_delete_http_request_with_endpoint_for_delete_dietician_by_invalid_id() {
	response = request.when().post("Delete_DeletebyDieticianId"); 
}

@Then("Admin recieves {int} method not allowed for delete Dietician by invalid Id")
public void admin_recieves_method_not_allowed_for_delete_dietician_by_invalid_id(Integer expectedStatusCode) {
	assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
}
//DELETE BY INVALID ENDPOINT//
@Given("Admin create DELETE request for delete Dietician by invalid endpoint")
public void admin_create_delete_request_for_delete_dietician_by_invalid_endpoint() throws FileNotFoundException {
	 request = given().spec(requestSpecification())
	            .header("Authorization", "Bearer " + IdHolder.token)
	            .pathParam("dieticianId", IdHolder.dieticianId);  // Replace with appropriate dieticianId

}

@When("Admin send DELETE http request with invalid endpoint by invalid endpoint")
public void admin_send_delete_http_request_with_invalid_endpoint_by_invalid_endpoint() {
	response = request.when().post("Invalid_delete_By_Id"); 
}

@Then("Admin recieves {int} method not allowed for delete Dietician by invalid endpoint")
public void admin_recieves_method_not_allowed_for_delete_dietician_by_invalid_endpoint(Integer expectedStatusCode) {
	assertEquals(expectedStatusCode, Integer.valueOf(response.getStatusCode()));
}






}
















