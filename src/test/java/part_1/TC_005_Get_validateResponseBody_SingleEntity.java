package part_1;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_005_Get_validateResponseBody_SingleEntity {

	@Test
	public void getValidateResponseBody() {
		
		//base URI
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		RequestSpecification httprequest=RestAssured.given();
		
		//save response
		Response response=httprequest.request(Method.GET,"/employees");
		
		//get response body in string form
		String responsebody=response.getBody().asString();
		System.out.println("response body :"+responsebody);
		
		//validate presence of 'Dai Rios' in response body
		Assert.assertEquals(responsebody.contains("Dai Rios"), true);
		
	}
}
