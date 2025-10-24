package stepDefinitions;

import static io.restassured.RestAssured.*;
import io.restassured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;


import java.util.List;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;



import io.cucumber.java.en.*;

public class stepDefinitionAPI {
	
	

 String BASE_URL = "https://openlibrary.org";
 String AUTHOR_ID = "OL1A";
 String res;
 JsonPath js;
	@Given("the baseURL")
	public void the_base_url() {
	    // Write code here that turns the phrase above into concrete actions
	
        RestAssured.baseURI = BASE_URL;

	}
	
	@When("I hit the endpoint for getAuthors")
	public void i_hit_the_endpoint_for_get_authors() {
	    
		String res = given()
		          .log().all()
		        .when()
		          .get("/authors/" + AUTHOR_ID + ".json")
		        .then()
		          .log().all()
		          .assertThat()
		          .statusCode(200)
		          .contentType(containsString("application/json"))
		          .body("key", equalTo("/authors/" + AUTHOR_ID))
		          .body("name", not(emptyString()))
		          .body("personal_name", equalTo("Sachi Rautroy"))
		          .body("alternate_names", hasItem("Yugashrashta Sachi Routray"))
		          .extract().response().asString();
		 js = new JsonPath(res);
	}
	
	@Then("I verify the {string} in response is {string}")
	public void i_verify_the_in_response_is(String name, String expected_name) {
		
		assertEquals(js.getString(name),expected_name);
	    
	}
	
	@Then("I verify the {string} in response contains {string}")
	public void i_verify_the_in_response_contains(String alternate_names, String expected_alternate_name) {
		
		given()
        .log().all()
      .when()
        .get("/authors/" + AUTHOR_ID + ".json")
      .then()
        .log().all()
        .body(alternate_names, hasItem(expected_alternate_name))
        .extract().response();
	}




	
	
	
	

}
