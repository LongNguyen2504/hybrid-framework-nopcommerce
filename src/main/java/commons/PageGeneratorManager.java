package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.nopCommerce.AdminDashboardPageObject;
import pageObjects.admin.nopCommerce.AdminLoginPageObject;
import pageObjects.user.nopCommerce.UserAddressPageObject;
import pageObjects.user.nopCommerce.UserCustomerInfoPageObject;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserLoginPageObject;
import pageObjects.user.nopCommerce.UserMyProductReviewPageObject;
import pageObjects.user.nopCommerce.UserRegisterPageObject;
import pageObjects.user.nopCommerce.UserRewardPointPageObject;

public class PageGeneratorManager { 
	public static UserHomePageObject getUserHomePageObject(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	public static UserRegisterPageObject getUserRegisterPageObject(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	public static UserLoginPageObject getUserLoginPageObject(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new UserCustomerInfoPageObject(driver);
	}

	public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new UserAddressPageObject(driver);
	}
	public static UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new UserRewardPointPageObject(driver);
	}
	public static UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new UserMyProductReviewPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new AdminLoginPageObject(driver);
	}
	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new AdminDashboardPageObject(driver);
	}

	/*After refactor*/
	/*Final PageGeneratorManager */
	/*usage: HomePageObject homePage = PageGeneratorManager.getPageGenerator.getHomePageObject(driver)*/
	/*public static PageGeneratorManager getPageGenerator(){
		return new PageGeneratorManager();
	}

	public UserHomePageObject getUserHomePageObject(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	public UserRegisterPageObject getUserRegisterPageObject(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	public UserLoginPageObject getUserLoginPageObject(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	public UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new UserCustomerInfoPageObject(driver);
	}

	public UserAddressPageObject getUserAddressPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new UserAddressPageObject(driver);
	}
	public UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new UserRewardPointPageObject(driver);
	}
	public UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new UserMyProductReviewPageObject(driver);
	}

	public AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new AdminLoginPageObject(driver);
	}
	public AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new AdminDashboardPageObject(driver);
	}*/

	
	

}
