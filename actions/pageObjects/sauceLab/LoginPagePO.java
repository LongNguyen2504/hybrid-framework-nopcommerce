package pageObjects.sauceLab;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageUIs.sauceLab.sort.LoginPageUI;

public class LoginPagePO extends BasePage{
	WebDriver driver;

	public LoginPagePO(WebDriver driver) {
		this.driver = driver;
	}


	public void enterToUsernameTextbox(String userName) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX,userName);
	}

	public void enterToPaswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX,password);
	}

	public ProductPO clickToLoginButton() {
		waitForElementClickable(driver,LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getProductPO(driver);
	}
}
