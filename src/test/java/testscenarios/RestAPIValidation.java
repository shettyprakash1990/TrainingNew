package testscenarios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import pages.HomePage;
import pages.LoginPage;
import pages.EndLinkApplication;
import utilities.ExcelData;
import utilities.RestAPI;
import utilities.RestMethods;
import utilities.Util;
import utilities.Values;

public class RestAPIValidation {

	@BeforeClass
	public void before() {
		Util.createOutputDirectory();
		Values.extent = new ExtentReports(Values.outputDirectory+"/Results.html", true);
		
	}

		@Test
	public void TC_001() {
		Util.start("data","TC_001","Rest Get Function ");	
		for (String testcase : Values.testcases) {
			try {
				Values.child = Values.extent.startTest(testcase);			
				Values.testCaseDataRow = Util.returnIndex(testcase);
				
				//====================================
				RestMethods.validateGetMethod();
				//====================================
				
			}catch(Exception e) {
				Util.Failed("Exception caught"+e.getMessage());
			}finally {
				Values.parent
				.appendChild(Values.child);
				Values.currentStep = 0;
			}
		}
		Values.extent.endTest(Values.parent);
		Values.extent.flush();
	}
		
		
		@Test
		public void TC_002() {
			Util.start("data","TC_002","Rest Post Function ");	
			for (String testcase : Values.testcases) {
				try {
					Values.child = Values.extent.startTest(testcase);			
					Values.testCaseDataRow = Util.returnIndex(testcase);
					
					//====================================
					RestMethods.validatePostMethod();
					//====================================
					
				}catch(Exception e) {
					Util.Failed("Exception caught"+e.getMessage());
				}finally {
					Values.parent
					.appendChild(Values.child);
					Values.currentStep = 0;
				}
			}
			Values.extent.endTest(Values.parent);
			Values.extent.flush();
		}	
		
}
