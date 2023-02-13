package pageObjects.user.nopCommerce;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.nopcommerce.HomePageUI;
public class UserHomePageObject extends BasePage{
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Navigate to Register page") // line này là log của allure
	public UserRegisterPageObject clickToRegisterLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		//return new RegisterPageObject(driver);//return new RegisterPageObject(driver); //Cách 2 : page manager
		return PageGeneratorManager.getUserRegisterPageObject(driver); //Cách 3 : thông qua hàm của PageGeneratorManager
	}
	@Step("Navigate to Login page")
	public UserLoginPageObject clickToLoginLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		//return new LoginPageObject(driver); cách 2 page generator manager
		return PageGeneratorManager.getUserLoginPageObject(driver); //Cách 3 : thông qua hàm của PageGeneratorManager
		
	}

	@Step("Verify 'My Account' link is displayed")
	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
	@Step("Navigate to My Account page")
	public UserCustomerInfoPageObject clickToMyAccountLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver); //Cách 3 : thông qua hàm của PageGeneratorManager
	}

}
