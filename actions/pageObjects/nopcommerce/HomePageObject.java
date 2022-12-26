package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commons.BasePage;
import pageUIs.nopcommerce.HomePageUI;
public class HomePageObject extends BasePage{
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToRegisterLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		//return new RegisterPageObject(driver);//return new RegisterPageObject(driver); //Cách 2 : page manager
		return PageGeneratorManager.getRegisterPageObject(driver); //Cách 3 : thông qua hàm của PageGeneratorManager
	}

	public LoginPageObject clickToLoginLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		//return new LoginPageObject(driver); cách 2 page generator manager
		return PageGeneratorManager.getLoginPageObject(driver); //Cách 3 : thông qua hàm của PageGeneratorManager
		
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public CustomerInfoPageObject clickToMyAccountLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver); //Cách 3 : thông qua hàm của PageGeneratorManager
	}

}
