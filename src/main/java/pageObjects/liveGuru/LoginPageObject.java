package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopcommerce.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public HomePageObject clickToLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		//return new HomePageObject(driver); // cách 2 page generator manager
		return PageGeneratorManager.getHomePageObject(driver);
		
		
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
