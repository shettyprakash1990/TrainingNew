package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.model.ScreenCapture;

import utilities.Util;

public class HomePage extends Page {


	@FindBy(xpath="//*[name()='svg'][@class='icon logoutbutton']")
	private WebElement btnLogOut;
	
	@FindBy(xpath="//*[name()='svg'][@class='createbutton']")
	private WebElement btnCreate;
	
	@FindBy(xpath="//div[contains(@class,'src-components-organisms-addrecipient')]//input")
	private WebElement txtToRecipient;
	
	@FindBy(xpath="//div[contains(@class,'src-components-organisms-recipientFilters')]//*[name()='svg']")
	private WebElement wesRecipientFilters;
	
	@FindBy(xpath="//div[contains(@class,'src-components-molecules-chatmessagebar')]/div[@placeholder='Secure Message']")
	private WebElement txtMessageBox;
	
	@FindBy(xpath="//*[name()='svg'][@class='sendbutton']")
	private WebElement btnSend;
	
	public HomePage(WebDriver browser) {
		super(browser);
	}
	
	
	public void SendMessage() {
		clickOn(btnCreate, "Send New Message Link");
		enterText(txtToRecipient, Util.get("RecipientMail"));
		/*String[] arrFilters = {"","","","","",""};
		
		 * List<WebElement> wes = browser.findElements(By.xpath(
		 * "//div[contains(@class,'src-components-organisms-recipientFilters')]//*[name()='svg']"
		 * )); String RecipientFilters = Common.get("RecipientFilters"); for(int
		 * i=0;i<arrFilters.length;i++) { if(RecipientFilters.contains(arrFilters[i])) {
		 * if(svgIsSelected(wes.get(i))) {
		 * 
		 * }else { wes.get(i).click(); } } }
		 */
		clickOn(txtMessageBox);
		enterText(txtMessageBox, Util.get("MessageBody"));
		clickOn(btnSend,"Send Message Button");
		Util.Screenshot();
	}
	
	public LoginPage logout() {
		clickOn(btnLogOut);
		if(isElementPresent(By.xpath("//input[@type='email']"))) {
			Util.Passed("Logged out successfully from the applciation");
			Util.Screenshot();
		}else {
			Util.Failed("Unable to logout from the application");
		}
		return new LoginPage(browser);
	}
}
