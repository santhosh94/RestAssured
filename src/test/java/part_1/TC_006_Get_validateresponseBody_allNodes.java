package part_1;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_006_Get_validateresponseBody_allNodes {
	@Test
	public void getValidateResponseBody_AllNodes() {
		
		//base URI
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httprequest=RestAssured.given();
		
		//save response
		Response response=httprequest.request(Method.GET,"/employee/1");
		
		//get response body in string form
		String responsebody=response.getBody().asString();
		System.out.println("response body :"+responsebody);
		
		
		JsonPath jsonpath=response.jsonPath();
		
		System.out.println("employee_name :"+jsonpath.get("data.employee_name"));
		System.out.println("status :"+jsonpath.get("status"));
		Assert.assertEquals(jsonpath.get("data.employee_name"), "Tiger Nixon");
		
		/*
		 * similarly
		 */
		Assert.assertEquals(jsonpath.get("status"), "success");
		
	}
}
