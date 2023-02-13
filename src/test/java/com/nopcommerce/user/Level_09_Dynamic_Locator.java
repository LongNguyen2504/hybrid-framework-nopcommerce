package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.nopCommerce.UserAddressPageObject;
import pageObjects.user.nopCommerce.UserCustomerInfoPageObject;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserLoginPageObject;
import pageObjects.user.nopCommerce.UserMyProductReviewPageObject;
import pageObjects.user.nopCommerce.UserRegisterPageObject;
import pageObjects.user.nopCommerce.UserRewardPointPageObject;


//Apply POM cho test case

public class Level_09_Dynamic_Locator extends BaseTest{
	private WebDriver driver;
	private String email,firstName,lastName,validPassword,confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserRewardPointPageObject rewardPointPage;
	private UserMyProductReviewPageObject myProductReviewPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
		firstName = "Automation";
		lastName = "FC" ;
		validPassword = "123456" ;
		confirmPassword = "123456" ;
		email = "afc" + randNumber() + "@mail.vn";
	}
	 

	@Test
	public void User_01_User_Register() {
		registerPage = homePage.clickToRegisterLink(); 
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox(email);
		registerPage.inputToPasswordTxtBox(validPassword);
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		homePage = registerPage.clickToContinueButton();
	}
	@Test
	public void User_02_User_Login() {
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager 
		loginPage.inputToEmailTxtBox(email);
		loginPage.inputToPasswordTxtBox(validPassword);
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}
	@Test
	public void User_03_Dynamic_Page_I() {

		//Cách 1 : dùng dynamic page
		//Hàm openMyAccountPageByName chứa rest parameter là tham số cuối cùng,do đó khi truyền 'Reward points' vào thì hàm String.format sẽ map 'Reward points' vào string DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA(trong implement của openMyAccountPageByName )
		rewardPointPage = (UserRewardPointPageObject) customerInfoPage.openPagesAtMyAccountByName(driver, "Reward points");  
		addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyAccountByName(driver, "Reward points");
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		customerInfoPage = (UserCustomerInfoPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Customer info");
		
	}

	//@Test
	public void User_03_Dynamic_Page_II() {
		//Cách 2 -Khi app có nhiều link tương tư(số lượng quá lớn), khởi tạo pageObject sau bên testcase 
		customerInfoPage.openPagesAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		myProductReviewPage.openPagesAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
		rewardPointPage.openPagesAtMyAccountByPageName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		
		addressPage.openPagesAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
	}
	
	@Test
	public void User_05_Switch_Role() {
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
