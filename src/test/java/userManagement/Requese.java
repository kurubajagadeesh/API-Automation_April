package userManagement;

 
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
 
 
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Requese {

	@Test(groups="smoke")
	public void getListUsers() {
		given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.assertThat()
		.statusCode(200)
        .body(not(blankOrNullString()))
        .body("page",equalTo(2));
		
		
	}
	@Test(groups="smoke")
	public void getSingleUser() {
		RestAssured.baseURI = "https://reqres.in/";
		Response response = given().when().get("/api/users/2");
		 assertEquals(response.getStatusCode(),200);
		 assertThat(response.getBody().asString(),not(blankOrNullString()));
		 assertThat(response.getBody().asString(),containsString("janet.weaver@reqres.in"));
		 assertThat(response.getBody().jsonPath().getString("data.first_name"),equalTo("Janet"));
		System.out.println(response.body().asString());
	
	}
	@Test(groups="regression")
	public void getListOfResources() {
		RestAssured.baseURI = "https://reqres.in/";
		Response response = given().when().get("/api/unknown");
		assertEquals(response.getStatusCode(),200);
		assertThat(response.body().asString(),not(blankOrNullString()));
		int users=response.jsonPath().getList("data").size();
		assertThat(response.jsonPath().getList("data"),hasSize(users));
		List<String> emails=response.jsonPath().getList("data.name",String.class);
		//System.out.println(emails.toString());
		assertThat(response.jsonPath().getList("data.name",String.class),hasItems("cerulean","fuchsia rose"));
	}
	@Test(groups= {"smoke","regression"})
	public void validateListContainsItems() {
		RestAssured.baseURI = "https://reqres.in/";
		Response response = given().when().get("/api/unknown");
		List<String> expectedNames = Arrays.asList("cerulean", "fuchsia rose", "true red");

		assertEquals(response.getStatusCode(),200);
		assertThat(response.jsonPath().getList("data.name",String.class),hasItems(expectedNames.toArray(new String[0])));
		
	}
	
	
}
