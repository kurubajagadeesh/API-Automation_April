package userManagement;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;


public class Paramaters {
	@Test(groups="Sanity")
	public void validateStatusCodeGetUser() {
		RestAssured.baseURI="https://reqres.in/";
		Response response = given().queryParam("page", 2).when().get("/api/users");
		int statuscode=response.statusCode();
		assertEquals(statuscode, 200);
		response.prettyPrint();
	}
	 @Test(groups="regression")
	    public void testGetUsersWithQueryParameters() {
		 RestAssured.baseURI="https://reqres.in/";
	        Response response = given()
	                .queryParam("page", 2)
	                .when()
	                .get("/api/users")
	                .then()
	                .statusCode(200)
	                .extract()
	                .response();

	        // Assert that the response contains 6 users
	        response.then().body("data", hasSize(6));

	        // Assert that the first user in the list has the correct values
	        response.then().body("data[0].id", is(7));
	        response.then().body("data[0].email", is("michael.lawson@reqres.in"));
	        response.then().body("data[0].first_name", is("Michael"));
	        response.then().body("data[0].last_name", is("Lawson"));
	        response.then().body("data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"));
	    }
	 @Test
	 public void testGetUsersWithMultipleQueryParams() {
	    Response response =
	            given()
	                    .queryParam("page", 2)
	                    .queryParam("per_page", 3)
	                    .queryParam("rtqsdr", 4)
	                    .when()
	                    .get("https://reqres.in/api/users")
	                    .then()
	                    .statusCode(200)
	                    .extract()
	                    .response();
	 }

	

}
