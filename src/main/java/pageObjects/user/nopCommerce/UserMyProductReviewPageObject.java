package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopcommerce.AddressPageUI;
import pageUIs.user.nopcommerce.CustomerInfoPageUI;

public class UserMyProductReviewPageObject extends BasePage{

	private WebDriver driver;
	
	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
}
