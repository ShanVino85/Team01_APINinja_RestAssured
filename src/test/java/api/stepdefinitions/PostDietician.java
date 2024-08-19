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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PostDietician extends RestUtils {
	
	
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	public static String emailId;
	public static String password; 
	
	@Given("Admin creates POST request with valid data of Dietician")
	public void admin_creates_post_request_with_valid_data_of_dietician() throws FileNotFoundException, IOException {
//		request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token);
        String dieticianData = "{\n" +
                "  \"ContactNumber\": \"4353411197\",\n" +
                "  \"DateOfBirth\": \"1973-08-07T18:14:08.570Z\",\n" +
                "  \"Education\": \"BE\",\n" +
                "  \"Email\": \"vianan152@gmail.com\",\n" +
                "  \"Firstname\": \"amera\",\n" +
                "  \"HospitalCity\": \"jercy\",\n" +
                "  \"HospitalName\": \"northshore\",\n" +
                "  \"HospitalPincode\": \"224131\",\n" +
                "  \"HospitalStreet\": \"12s wolf rd\",\n" +
                "  \"Lastname\": \"tammy\"\n" +
                "}";
        
        request = given().spec(requestSpecification()).header("Authorization", "Bearer " + IdHolder.token).body(dieticianData);
	}

	@When("Admin send POST http request with endpoint")
	public void admin_send_post_http_request_with_endpoint() {
		response = request.when().post(routes.getString("Post_CreateDietician")).then().log().all().extract().response();
	}

	@Then("Admin recieves {int} created and with response body.")
	public void admin_recieves_created_and_with_response_body(Integer int1) {
		assertEquals(response.getStatusCode(),201);
        JsonPath jsonPathEvaluator = response.jsonPath();
         emailId = jsonPathEvaluator.get("Email");
         password=jsonPathEvaluator.get("loginPassword");


	}



}
