package stepDefinitions;

import static io.restassured.RestAssured.*;
import io.restassured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.cucumber.java.en.*;

public class stepDefinitionAPI {
	
 String BASE_URL = "https://openlibrary.org";
 String AUTHOR_ID = "OL1A";
 Response response;
 JsonPath js;
	
 @Given("the baseURL")
	public void the_base_url() {
	    //Setting up BASE_URI
	
        RestAssured.baseURI = BASE_URL;

	}
	
	@When("I hit the endpoint for getAuthors")
	public void i_hit_the_endpoint_for_get_authors() {
	    
	 response = given()
		          .log().all()
		          .when()
		          .get("/authors/" + AUTHOR_ID + ".json")
		          .then()
		          .log().all()
		          .assertThat()
		          .statusCode(200)
		          .extract().response(); 
		
		
	}
	
	@Then("I verify the {string} in response is {string}")
	public void i_verify_the_in_response_is(String name, String expected_name) {
		
		assertEquals(response.jsonPath().get(name),expected_name);
	    
	}
	
	@Then("I verify the {string} in response contains {string}")
	public void i_verify_the_in_response_contains(String alternate_names, String expected_alternate_name) {
		

		java.util.List<String> alternateNames = response.jsonPath().getList("alternate_names");

        //Print the list
        System.out.println("Alternate Names: " + alternateNames);

        // Assert that the list contains "Yugashrashta Sachi Routray"
        assertTrue("Assertion Fail : Expected name not found in alternate_names!!!", alternateNames.contains(expected_alternate_name));
       
     }


}
