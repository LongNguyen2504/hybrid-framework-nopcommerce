package pageObjects.nopcommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopcommerce.AddressPageUI;
import pageUIs.nopcommerce.CustomerInfoPageUI;
import pageUIs.nopcommerce.RewardPointPageUI;

public class RewardPointPageObject extends BasePage{

	private WebDriver driver;
	
	public RewardPointPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
//	demo testcase User_04_Switch_Page trong class Level_07_Switch_Page
//	public CustomerInfoPageObject openCustomerInfoPage() {
//		waitForElementVisible(driver, RewardPointPageUI.CUSTOMER_INFO_LINK);
//		clickToElement(driver, RewardPointPageUI.CUSTOMER_INFO_LINK);
//		// TODO Auto-generated method stub
//		return new CustomerInfoPageObject(driver);
//	}
//	public AddressPageObject openAddressPage() {
//		waitForElementVisible(driver, RewardPointPageUI.ADDRESS_LINK);
//		clickToElement(driver, RewardPointPageUI.ADDRESS_LINK);
//		// TODO Auto-generated method stub
//		return new AddressPageObject(driver);
//	}
	
}
