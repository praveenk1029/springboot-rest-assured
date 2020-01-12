package com.practice.springbootrestassured;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class SpringbootRestAssuredApplicationTests {

	@Test
	public void getEmployeeByEmpId() {
		RestAssured.baseURI = "http://localhost:8080/rest/employee/getEmployee";
		//RestAssured.baseURI = "http://localhost:8080/rest/employee/getAllEmployees";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/1");
		//Response response = httpRequest.request(Method.GET);
		String responseBody = response.getBody().asString();
		System.out.println("responseBody = " + responseBody);

		assertEquals(responseBody.contains("Mike"), true);
		assertEquals(java.util.Optional.of(response.getStatusCode()),
				java.util.Optional.of(Integer.valueOf("200")));
	}

	@Test
	public void getEmployeeJson(){
		RestAssured.baseURI = "http://localhost:8080/rest/employee/getEmployee";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/6");
		JsonPath jsonPath = response.jsonPath();
		System.out.println("jsonPath.get()::::"+jsonPath.get("empFirstName"));
	}

	@Test
	public void getUserAuthentication(){
		RestAssured.baseURI = "http://localhost:8080/rest/db/secured/display";
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("Praveen");
		authScheme.setPassword("admin");
		RestAssured.authentication = authScheme;

		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET);
		System.out.println("Response Body:::"+response.getBody().asString());
	}

	@Test
	public void saveEmployee(){
		RestAssured.baseURI = "http://localhost:8080/rest/employee/saveEmployee";
		RequestSpecification httpRequest = RestAssured.given();
		JSONObject jsonObjectReqParams = new JSONObject();
		jsonObjectReqParams.put("empFirstName", "David");
		jsonObjectReqParams.put("empLastName", "Fullerton");
		jsonObjectReqParams.put("salary", 8582.09);
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(jsonObjectReqParams.toJSONString());
		Response response = httpRequest.request(Method.POST);
		String responseBody = response.getBody().asString();
		System.out.println("Response Body:::::::::::"+responseBody);
		System.out.println("Response Status Code::::"+response.getStatusCode());
	}

	@Test
	public void deleteEmployee(){
		RestAssured.baseURI = "http://localhost:8080/rest/employee/deleteEmployee";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.DELETE, "/14");
		String responseBody = response.getBody().asString();
		System.out.println("responseBody:::::"+responseBody);
		response.getStatusCode();
		assertEquals(java.util.Optional.of(response.getStatusCode()),
				java.util.Optional.of(Integer.valueOf("200")));
	}

	@Test
	public void testGetEmployeeWithHeaders(){
		RestAssured.baseURI = "http://localhost:8080/rest/employee/getEmployee";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,"/2");
		String responseBody = response.getBody().asString();
		System.out.println("responseBody::::::"+responseBody);
		String contentType = response.header("Content-Type");
		System.out.println("contentType::::::::"+contentType);
		System.out.println("Response Headers:::"+response.getHeaders());
		Assert.assertEquals(contentType, "application/json;charset=UTF-8");
		System.out.println("response.headers()::::"+response.headers());
	}


}
