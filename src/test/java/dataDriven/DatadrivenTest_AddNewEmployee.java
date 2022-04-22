package dataDriven;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DatadrivenTest_AddNewEmployee {

	@Test(dataProvider="empdataprovider")
	public void postNewEmployee(String ename,String esal,String eage) {
		
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		// Request object
		RequestSpecification httprequest = RestAssured.given();

		// Request payload sending along with post request
		JSONObject requestparams = new JSONObject();
		requestparams.put("name", ename);
		requestparams.put("salary", esal);
		requestparams.put("age", eage);

		httprequest.header("Content-Type", "application/json");
		// attach data to the body
		httprequest.body(requestparams.toJSONString());

		// Response object //POST Request
		// save the response to 'response' object
		Response response = httprequest.request(Method.POST, "/create"); // 'api/v1/create 'parameter here

		// capture response body to perform validations
		String responseBody = response.getBody().asString();
		System.out.println("response body: " + responseBody);

		/*
		 * since dataprovider is used, below validations will occur three as we have
		 * given from 'getEmpData' method
		 */
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esal), true);
		Assert.assertEquals(responseBody.contains(eage), true);

		// validate status code
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
				System.out.println("statuscode: "+statuscode);
				
	}
	
	@DataProvider(name="empdataprovider")
	String [][] getEmpData() throws IOException{
		
		/*
		 * tradition keyword driven( data is part of the script)
		 */
		//String empdata[][]= {{"joey","6000","30"},{"Hank","10000","45"},{"Mil","12000","20"}};
		
		/*
		 * data driven : passing data via excel
		 */
		//path of the excel file "C:\Users\Santhosh\workspace\RestAssured_API_automation\src\test\java\dataDriven\empdata.xlsx"
		String path=System.getProperty("user.dir")+"/src/test/java/dataDriven/empdata.xlsx";
		int rowcount=XLUtility.getRowCount(path, "Sheet1");
		int cellcount=XLUtility.getCellCount(path, "Sheet1",1);
		
		String empdata[][]=new String [rowcount][cellcount]; //creating array as same size of rowcount and cellcount
		
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<cellcount;j++) {
				empdata[i-1][j]=XLUtility.getCellData(path, "Sheet1", i, j);
			}
		}
		return(empdata);
	}
}
