package userManagement;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
 
 

public class GetUsers {
	@Test
	public void valiadateGetResponseBody() {
		//set base uri for the api
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		  // Send a GET request and validate the response body using 'then'
		 given()
         .when()
         .get("/todos/1")
         .then()
         .assertThat()
         .statusCode(200)
         .body(not(blankOrNullString()))
         .body("title", equalTo("delectus aut autem"))
         .body("userId", equalTo(1));

			
	}
	 @Test
	    public void validateGetResponseBody() {
	        // Set base URI for the API
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	        // Send a GET request and store the response in a variable
	        Response response = given()
	                .when()
	                .get("/todos/1")
	                .then()
	                .extract()
	                .response();

	        // Validate that the response body is not empty
	        assertThat(response.getBody().asString(), not(isEmptyString()));

	        // Validate that the response contains a specific value
	        assertThat(response.getBody().asString(), containsString("delectus aut autem"));

	        // Validate that the response has a specific JSON attribute
	       //assertThat(response.getBody().jsonPath().get("title"), equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));

	        // Validate that the response has a specific XML element (if applicable)
	       //  assertThat(response.getBody().xmlPath().get("element"), equalTo("expectedValue"));
	    
	}

}
