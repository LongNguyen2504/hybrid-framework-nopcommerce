package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.nopcommerce.LoginPageUI;

public class UserLoginPageObject extends BasePage{
	WebDriver driver;
	
	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserHomePageObject clickToLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		//return new HomePageObject(driver); // cách 2 page generator manager
		return PageGeneratorManager.getUserHomePageObject(driver); //Cách 3 : thông qua hàm của PageGeneratorManager
		
		
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

	public UserHomePageObject loginAsUser(String email,String password) {
		// TODO Auto-generated method stub
		inputToEmailTxtBox(email);
		inputToPasswordTxtBox(password);
		return clickToLoginButton();
	}


	
	
	

}
