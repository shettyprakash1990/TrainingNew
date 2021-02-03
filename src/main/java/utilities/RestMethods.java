package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestMethods {

	public static void validateGetMethod() {
		try {
			String baseUrl = Util.get("Domain");
			String getUrl =  Util.get("geturl");
			String strLastName =  Util.get("LastName");
			
			
			RestAPI RestAPI = new RestAPI(baseUrl);
			String strResponse = RestAPI.getResponseString(getUrl);
			Util.Info("Json Resonse is as below ");
			Util.infoJson(strResponse);
			Response response = RestAPI.getResponse(getUrl);
			JsonPath jsonPath = response.getBody().jsonPath();
			String lastName = jsonPath.get("data[2].last_name");
			if(lastName.equals(strLastName)) {
				Util.Passed("Response has the valid Last Name as expected");
			}else {
				Util.Failed("Response does not have the valid Last Name , Actual is "+lastName +", But expected is :"+ strLastName);
			}
		}catch(Exception e){			
			Util.Failed("Exception caught , Message is "+e.getMessage());		
		}	
	}
	
	public static void validatePostMethod() {
		try {
			String baseUrl = Util.get("Domain");
			String postUrl =  Util.get("postUrl");
			String expCode = Util.get("StatusCode");
			String path = "./src/test/resources/json/" + Util.get("jsonPath");
			RestAPI RestAPI = new RestAPI(baseUrl);
			Response response = RestAPI.postRequest(postUrl, path);
			if(response.getStatusCode()==Integer.parseInt(expCode)) {
				Util.Passed("Status code is as expected : "+expCode);
			}else {
				Util.Failed("Status code is not valid, Expected is : "+expCode + " ,Actual is "+response.getStatusCode());
			}
		}catch(Exception e){			
			Util.Failed("Exception caught , Message is "+e.getMessage());		
		}	
	}
}
