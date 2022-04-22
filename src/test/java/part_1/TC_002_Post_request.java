package part_1;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_002_Post_request {

	@Test
	void postNewUser() {
		// TODO Auto-generated method stub

		//specify base URI
		RestAssured.baseURI="https://reqres.in";
		
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		
		//Request payload sending along with post request
		JSONObject requestparams=new JSONObject();
		requestparams.put("name", "joey");
		requestparams.put("job", "testing");
		
		
		httprequest.header("content-type","application/JSON");
		//attach data to the body
		httprequest.body(requestparams.toJSONString());
		
		//Response object
		//save the response to 'response' object
		Response response=httprequest.request(Method.POST,"/api/users"); // '/api/users 'parameter here
		
		//print response in console(for reference only)
		String responseBody=response.getBody().asString();
		System.out.println("Response Body received: "+responseBody);
		
		// asserting status code
		int statuscode = response.statusCode();
		System.out.println("Status code received: " + statuscode);
		Assert.assertEquals(statuscode,201);
		
		//assert 'name' field in successBody (Response payload)
		String successbody=response.jsonPath().getString("name");
		System.out.println("name field in JSON path received :"+successbody);
		Assert.assertEquals(successbody,"joey");
		
	}
}
