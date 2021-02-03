package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Values {

	public static ArrayList<String> testcases = new ArrayList<String>();
	 public static int testCaseDataRow; 
	  public static int testCaseRow;
	  public static int rowCount;
	  public static int colCount;
	  public static ExtentReports extent;
	  public static ExtentTest parent;
	  public static ExtentTest child;
	  public static String outputDirectory;
	  public static String timeStamp = "";
	  public static int currentStep;
	  public static int failureNo;
	  public static int screenshotNo;
	  public static boolean testFailure = false;
	  public static WebDriver driver;
	  public static List<List<Object>> data = new ArrayList<List<Object>>() ;;
	  public static Properties frameworkProperty;
}
