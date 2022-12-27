package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopcommerce.AddressPageUI;
import pageUIs.user.nopcommerce.CustomerInfoPageUI;

public class UserAddressPageObject extends BasePage{

	private WebDriver driver;
	
	public UserAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}
//  demo testcase User_04_Switch_Page trong class Level_07_Switch_Page
//	public RewardPointPageObject openRewardPointPage() {
//		waitForElementVisible(driver, AddressPageUI.REWARD_POINT_LINK);
//		clickToElement(driver, AddressPageUI.REWARD_POINT_LINK);
//		// TODO Auto-generated method stub
//		return new RewardPointPageObject(driver);
//	}
//	public CustomerInfoPageObject openCustomerInfoPage() {
//		waitForElementVisible(driver, AddressPageUI.CUSTOMER_INFO_LINK);
//		clickToElement(driver, AddressPageUI.CUSTOMER_INFO_LINK);
//		// TODO Auto-generated method stub
//		return new CustomerInfoPageObject(driver);
//	}
	
}
