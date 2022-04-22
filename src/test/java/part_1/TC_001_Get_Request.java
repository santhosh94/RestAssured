package part_1;

import static org.testng.Assert.assertEquals;

import org.apache.http.client.methods.AbortableHttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_001_Get_Request {

	@Test
	void getUserDetails() {
		// TODO Auto-generated method stub

		//specify base URI
		RestAssured.baseURI="https://reqres.in/api/users";
		
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		
		//Response object
		// passing '/2' as path parameter to save the response to 'response' object
		Response response=httprequest.request(Method.GET,"/2");
		
		//print response in console(for reference only)
		String responseBody=response.getBody().asString();
		System.out.println("Response Body received: "+responseBody);
		
		// asserting status code
		int statuscode = response.statusCode();
		System.out.println("Status code received: " + statuscode);
		Assert.assertEquals(statuscode,200);

		// assert status line
		String statusline = response.getStatusLine();
		System.out.println("Status line: " + statusline);
		Assert.assertEquals(statusline,"HTTP/1.1 200 OK");
	}

}
