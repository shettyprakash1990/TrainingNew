package pages;


import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Util;






/**
 * Base class for all the pages.
 *
 */
public class Page {
	protected WebDriver browser;	

	/**
	 * Constructor for Page class 
	 * @param browser
	 * @param report
	 */
	protected Page(WebDriver browser) {
		this.browser=browser;		
		PageFactory.initElements(browser, this);

	}


	/**
	 * Check if the element is present in the page
	 * @param element WebElement need to check
	 * @return True/False
	 */
	protected boolean isElementPresent(WebElement element){
		try{
			new WebDriverWait(browser, 2).until(ExpectedConditions
					.elementToBeClickable(element));
			if(element.isDisplayed()){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			return false;		
		}
	}
	
	/**
	 * Check if the element is present in the page
	 * @param element WebElement need to check
	 * @return True/False
	 */
	protected void verifyElementDisplayed(WebElement element,String elemName){
		try{
			if(isElementPresent(element)) {
				Util.Passed(elemName+" Element is displayed as expected");
			}else {
				Util.Failed(elemName+" Element is not displayed");
			}
		}catch(Exception ex){
			Util.Failed("Eception caught while verifying element "+ex.getMessage());	
		}
	}

	public void scrollTop() {
		try {
			((JavascriptExecutor) browser).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		}catch (Exception e) {
			
		}
	}
	

	/**
	 * Method to click on a link(WebElement link)
	 * @param : WebElement
	 * @param : Element Name
	 */
	protected void jsClick(WebElement we,String elemName) {		
		try{			
			((JavascriptExecutor) browser).executeScript("arguments[0].click();",we);	
		}catch (RuntimeException ex) {
			Util.Failed("Uanble to click on Element-"+ elemName +", Exception is->"+ex.getMessage());
		} 
	}

	public void jsMoveToElement(WebElement we) {
		try {
		((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", we);
		}catch (Exception ex) {
			
		} 
	}
	/**
	 * Check if the element is present in the page
	 * @param Element locator of type By
	 * @return True/False
	 */
	public boolean isElementPresent(By by){
		try{
			new WebDriverWait(browser, 30).until(ExpectedConditions
					.elementToBeClickable(by));
			if(browser.findElement(by).isDisplayed()){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			return false;		
		}
	}
	
	public boolean isElementPresent1(By by){
		try{
			if(browser.findElement(by).isDisplayed()){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			return false;		
		}
	}


	/***
	 * Method to check for an alert for 20 seconds
	 * @param       : Element Name
	 * @return      : 
	 * Modified By  :  
	 ***/

	public boolean isAlertPresent(){
		try{
			WebDriverWait wait = new WebDriverWait(browser, 20);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		}catch(Exception e){			
			return false;
		}
	}

	/**
	 * Method to wait for element to load in the page
	 * @param WebElement
	 */
	protected Boolean waitForIsClickable(WebElement we) {
		try {
			new WebDriverWait(browser, 200).until(ExpectedConditions
					.elementToBeClickable(we));			
		return true;
		} catch (Exception ex) {
			//Common.Failed("Element is not visible after waiting for 60 Seconds, : "+ex.getMessage());			
			return false;
		}    	
	}
	
	/**
	 * Method to wait for element to load in the page
	 * @param WebElement
	 */
	protected void waitForElement(By by) {
		try {
			new WebDriverWait(browser, 100).until(ExpectedConditions
					.elementToBeClickable(by));				
		} catch (Exception ex) {
			//Common.Failed("Element is not visible after waiting for 60 Seconds, : "+ex.getMessage());			
		}    	
	}
	
	/**
	 * Method to wait for element to load in the page
	 * @param WebElement
	 */
	protected void waitForElement(WebElement we) {
		try {
			new WebDriverWait(browser, 100).until(ExpectedConditions
					.visibilityOf(we));				
		} catch (Exception ex) {
			//Common.Failed("Element is not visible after waiting for 60 Seconds, : "+ex.getMessage());			
		}    	
	}
	
	/**
	 * Method to wait for element to load in the page
	 * @param WebElement
	 */
	protected void waitForFrameandSwitch(int index) {
		try {
			new WebDriverWait(browser, 60).until(ExpectedConditions
					.frameToBeAvailableAndSwitchToIt(index));				
		} catch (Exception ex) {
			//Common.Failed("Element is not visible after waiting for 60 Seconds, : "+ex.getMessage());			
		}    	
	}
	
	public String getText(WebElement we) {
		try {
			return we.getText();
		} catch (Exception ex) {
			Util.Failed("Unable to get the text : "+ex.getMessage());			
			return "";
		}  
	}
	
	public void ScrollEnd() {
		JavascriptExecutor js = (JavascriptExecutor) browser;
        //This will scroll the web page till end.		
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void ScrollFirst() {
		JavascriptExecutor js = (JavascriptExecutor) browser;
        //This will scroll the web page till end.		
        js.executeScript("window.scrollTo(0, 0)");
	}

	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) browser;
        //This will scroll the web page till end.		
        js.executeScript("arguments[0].scrollIntoView(true);", element);
	}


	/***
	 * Method to click on a link(WebElement button)
	 * @param : WebElement
	 * @param : Element Name
	 ***/
	public void clickOn(WebElement we,String elemName) {		
		try{
			waitForIsClickable(we);
			String strProp = we.toString();
			if (isElementPresent(we)){
				we.click();	
				Util.Info("Clicked on element "+elemName);
			}else{
				Util.Failed("Unable to click on Element "+elemName+", Element with following property is not displayed->"+strProp);
			}
		}catch (Exception ex) {
			Util.Failed("Uanble to click on Element-"+ elemName +", Exception is->"+ex.getMessage());
		} 
	}
	
	public void clickOn(WebElement we) {		
		try{
			waitForIsClickable(we);
			String strProp = we.toString();
			if (isElementPresent(we)){
				we.click();								
			}
		}catch (Exception ex) {
			Util.Failed("Uanble to click on Element, Exception is->"+ex.getMessage());
		} 
	}

	/***
	 * Method to enter text in a textbox
	 * @param : WebElement - Textbox
	 * @param : Text to be entered
	 * @return :
	 ***/
	public boolean enterText(WebElement we,String text){
		try{
			waitForIsClickable(we);
			if(isElementPresent(we)){
				we.clear();
				we.sendKeys(text);
				Util.Info("Entered Value in the text field "+text);
				return true;
			}else{
				Util.Failed("Element is not displayed, Unable to enter text->"+ text);
				return false;
			}
		}
		catch (Exception ex) {			
			Util.Failed("Unable to enter text in the text field->"+ text);
			return false;
		} 
	}
	
	/***
	 * Method to enter text in a textbox
	 * @param : WebElement - Textbox
	 * @param : Text to be entered
	 * @return :
	 ***/
	public boolean jsEnterText(WebElement we,String text){
		try{
			waitForIsClickable(we);
			if(isElementPresent(we)){
				JavascriptExecutor js = (JavascriptExecutor)browser;				 
				//js.executeScript("arguments[0].setAttribute('value', '" + text +"');", we);
				js.executeScript("arguments[0].value = arguments[1];", we,text);
				
				
				sleep(5000);
				return true;
			}else{
				Util.Failed("Element is not displayed, Unable to enter text->"+ text);
				sleep(5000);
				return false;
			}
			
			
			}
		catch (RuntimeException ex) {			
			Util.Failed("Unable to enter text in the text field->"+ text);
			return false;
		} 
	}

	/***
	 * Method to switch to child window
	 * @param : parentWindow
	 ***/
	public void navigateToNewWindow() {
		boolean blnNavigate=false;
		String parentWindow = browser.getWindowHandle();
		try{				
			Set<String> handles = browser.getWindowHandles();			
			for (String windowHandle : handles) {					
				String strActWindow = browser.switchTo().window(windowHandle).getWindowHandle();
				if(!strActWindow.equals(parentWindow)){
					blnNavigate = true;
					browser.manage().window().maximize();				
					break;
				}					
			}
			if(!blnNavigate){
				Util.Failed("Unable to Navigate to the new page");
			}
		}catch(Exception ex){
			Util.Failed("Unable to Navigate to the new page");
		}
	}
	
	public boolean isDisabled(WebElement elem) {
		if(elem.isEnabled()) {
			return false;
		}else {
			return true;
		}
	}
	
	public void sleep(int milisec) {
		try {
			Thread.sleep(milisec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getList(String str) {
		ArrayList<String> list = new ArrayList<String>();
		String newline = System.getProperty("line.separator");
		String[] arr = str.split("\\n");
		for(String arrString : arr) {
			if(!arrString.contains("Select all")) {
				list.add(arrString);
			}
		}
		return list;
	}
	
	public boolean svgIsSelected(WebElement we) {
		String attrClass= we.getAttribute("class");
		if(attrClass.contains("semitransparent")) {
			return false;
		}else {
			return true;
		}
	}
}
