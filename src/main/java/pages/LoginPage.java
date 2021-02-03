package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.Util;

public class LoginPage extends Page {



	@FindBy(xpath="//input[@type='email']")
	private WebElement txtUsername;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement txtPassword;
	
	@FindBy(xpath="//span[text()='Sign In']")
	private WebElement btnSignIn;

	@FindBy(xpath="//*[name()='svg'][@class='icon logoutbutton']")
	private WebElement btnLogOut;
	
	
	
	
	public LoginPage(WebDriver browser) {
		super(browser);
	}


	public HomePage loginToApplication() {
		String username= Util.get("Username");
		String password =  Util.get("Password");
		enterText(txtUsername, username);
		enterText(txtPassword, password);
		clickOn(btnSignIn, "Submit Button");
		waitForIsClickable(btnLogOut);
		Util.Screenshot();
		return new HomePage(browser);
	}
	
	public HomePage loginToApplication(String username,String password) {
		enterText(txtUsername, username);
		enterText(txtPassword, password);
		clickOn(btnSignIn, "Submit Button");
		waitForIsClickable(btnLogOut);
		Util.Screenshot();
		return new HomePage(browser);
	}
	
}
