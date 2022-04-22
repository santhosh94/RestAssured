package part_1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_007_Post_BasicAuthentication {

	@Test
	public void getBasicAuthenitcation() {
		
		RestAssured.baseURI="give your URI";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authscheme=new  PreemptiveBasicAuthScheme();
		authscheme.setUserName("give username");
		authscheme.setPassword("give password");
		
		// mention type of authentication
		RestAssured.authentication=authscheme;
		
		/*
		 * rest will continue the same
		 */
		RequestSpecification httprequest=RestAssured.given();
		//save response
				Response response=httprequest.request(Method.GET,"/employee/1");
				
				//get response body in string form
				String responsebody=response.getBody().asString();
				System.out.println("response body :"+responsebody);
	}
}
