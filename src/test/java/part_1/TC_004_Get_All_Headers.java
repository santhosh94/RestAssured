package part_1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_004_Get_All_Headers {

	@Test
	void getallHeaders() {
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httprequest=RestAssured.given();
		
		// Response object
		Response response=httprequest.request(Method.GET,"/employees");
		
		// store all headers
		Headers allheaders=response.headers();
		
		//print all headers
		for(Header header:allheaders) {
			System.out.println(header.getName() + ":   :" +header.getValue());
		}
	}
}
