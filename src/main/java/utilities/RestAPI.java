package utilities;


import java.io.File;
import java.util.HashMap;

import org.apache.commons.collections4.Get;

import com.relevantcodes.extentreports.model.Log;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPI {

	private RequestSpecification httpRequest;

	public RestAPI(String baseUri){
		try { 
			RestAssured.baseURI = baseUri;
			httpRequest = RestAssured.given();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RestAPI(String baseUri,String userName,String password){
		try { 
			RestAssured.baseURI = baseUri;
			httpRequest = RestAssured.given().auth().basic(userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RestAPI(String baseUri,String accessToken){
		try { 
			RestAssured.baseURI = baseUri;
			httpRequest = RestAssured.given().auth().oauth2(accessToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getResponseString(String get) throws Exception{
		String str;
		try{
			Response response = httpRequest.get(get);
			str = response.body().asString();
		}catch(Exception ex){			
			throw ex;			
		}
		return str;
	}

	public String getResponseString(){
		Response response = httpRequest.get();
		String str = response.body().asString();
		return str;
	}


	public Response getResponse(){
		Response response = httpRequest.get();		
		return response;
	}

	public Response getResponse(String get){
		Response response = httpRequest.get(get);		

		return response;
	}

	public static int getStatusCode(Response response){
		try{
			return response.getStatusCode();
		}catch(Exception e){
			return -1;
		}
	}


	public static String getResponseHeader(Response response){
		try{
			return response.getHeaders().toString();
		}catch(Exception e){
			return null;
		}
	}


	public static String getResponseBody(Response response){
		try{
			return response.getBody().asString();
		}catch(Exception e){
			return null;
		}
	}


	public Response postRequest(String postUrl,String strFile) {
		String str = null;
		Response response = null;       
		try{			 
			response = httpRequest.contentType(ContentType.JSON).body(new File(strFile)).post(postUrl);	            
			Util.Info("Rest response for the POST request with url "+  postUrl + " is as below" );
			Util.infoJson(response.getBody().asString());
		}catch(Exception e){			
			Util.Failed("Exception caught in postRequest Action, Message is "+e.getMessage());		
		}	
		return response;
	}

	public Response putRequest(String putUrl,String strFile) {
		String str = null;
		Response response = null;  
		try{			      
			response = httpRequest.contentType(ContentType.JSON).body(new File(strFile)).put(putUrl);	            
			Util.Info("Rest response for the PUT request with url "+  putUrl + " is as below" );
			Util.infoJson(response.getBody().asString());
		}catch(Exception e){			
			Util.Failed("Exception caught in putRequest Action, Message is "+e.getMessage());		
		}	
		return response;
	}


	public Response patchRequest(String patchUrl,String strFile) {
		String str = null;
		Response response = null;        
		try{
			
			response = httpRequest.contentType(ContentType.JSON).body(new File(strFile)).patch(patchUrl);	            
			Util.Info("Rest response for the PATCH request with url "+  patchUrl + " is as below" );
			Util.infoJson(response.getBody().asString());
		}catch(Exception e){			
			Util.Failed("Exception caught in patchRequest Action, Message is "+e.getMessage());		
		}	
		return response;
	}
	
	public Response deleteRequest(String deleteUrl) {
		Response response = null;   
		String str = null;
		try{
			     
			response = httpRequest.delete(deleteUrl);	            
			Util.Info("Rest response for the DELETE request with url "+  deleteUrl + " is as below" );
			Util.infoJson(response.getBody().asString());
		}catch(Exception e){			
			Util.Failed("Exception caught in deleteRequest Action, Message is "+e.getMessage());		
		}
		return response;
	}
	
	


}
