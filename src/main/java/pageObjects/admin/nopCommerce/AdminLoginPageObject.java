package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.nopcommerce.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void inputToUsernameTxtBox(String email) {
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, email);
	}
	public void inputToPasswordTxtBox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
	}
	public AdminDashboardPageObject clickToLoginBtn() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
	public AdminDashboardPageObject loginAsAdmin(String email,String password) {
		inputToUsernameTxtBox(email);
		inputToPasswordTxtBox(password);
		return clickToLoginBtn();
		
	}
	

}
