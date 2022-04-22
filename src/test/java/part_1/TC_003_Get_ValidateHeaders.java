package part_1;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_003_Get_ValidateHeaders {
	@Test
	void getUserDetails() {
		// TODO Auto-generated method stub

		//specify base URI
		RestAssured.baseURI="http://dummy.restapiexample.com";
		
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		
		//Response object
		//save the response to 'response' object
		Response response=httprequest.request(Method.GET,"/api/v1/employees");
		
		//print response in console(for reference only)
		String responseBody=response.getBody().asString();
		System.out.println("Response Body received: "+responseBody);
		
		//validate status code
		int statuscode=response.getStatusCode();
		System.out.println("status code received: "+statuscode);
		Assert.assertEquals(200, statuscode);
		
		// ***validating headers***
		
		//1. content-type
		String ContentType=response.header("Content-Type");
		System.out.println("Content-Type received: " + ContentType);
		Assert.assertEquals("application/json", ContentType);
		
		//1. Content-Encoding
		String ContentEncoding=response.header("Content-Encoding");
		System.out.println("Content-Encoding received: " + ContentEncoding);
		Assert.assertEquals("gzip", ContentEncoding);

	}
}
