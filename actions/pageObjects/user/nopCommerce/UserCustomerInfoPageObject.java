package pageObjects.user.nopCommerce;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopcommerce.AddressPageUI;
import pageUIs.user.nopcommerce.CustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage{

	private WebDriver driver;
	
	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("verify Customer Info Page is displayed")
	public boolean isCustomerInfoPageDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_TITLE);
		return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_TITLE);
	}

//	demo testcase User_04_Switch_Page trong class Level_07_Switch_Page
//	public AddressPageObject openAddressPage() {
//		waitForElementVisible(driver, CustomerInfoPageUI.ADDRESS_LINK);
//		clickToElement(driver, CustomerInfoPageUI.ADDRESS_LINK);
//		// TODO Auto-generated method stub
//		return new AddressPageObject(driver);
//	}
//	public RewardPointPageObject openRewardPointPage() {
//		waitForElementVisible(driver, CustomerInfoPageUI.REWARD_POINT_LINK);
//		clickToElement(driver, CustomerInfoPageUI.REWARD_POINT_LINK);
//		// TODO Auto-generated method stub
//		return new RewardPointPageObject(driver);
//	}

	
}
