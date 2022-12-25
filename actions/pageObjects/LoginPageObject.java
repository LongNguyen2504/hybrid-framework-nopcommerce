package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		
		
		
	}

	public String getErrorMessageAtEmailTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		
	}

	public void inputToEmailTxtBox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_TXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TXTBOX, email);
		
	}
	
	public void inputToPasswordTxtBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TXTBOX, password);
	}

	public String getUnsuccessErrorMessageAtEmailTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.UNSUCCESS_EMAIL_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESS_EMAIL_MESSAGE);
	}


	
	
	

}
