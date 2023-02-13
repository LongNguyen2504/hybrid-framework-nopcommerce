package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.nopCommerce.*;


//Apply POM cho test case

public class Level_12_Assert_Verify extends BaseTest{
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
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.."); // set false intentionally
		homePage = registerPage.clickToContinueButton();
	}
	@Test
	public void User_02_User_Login() {
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		loginPage.inputToEmailTxtBox(email);
		loginPage.inputToPasswordTxtBox(validPassword);
		homePage = loginPage.clickToLoginButton();
		verifyFalse(homePage.isMyAccountLinkDisplayed());// set false intentionally
		customerInfoPage = homePage.clickToMyAccountLink();
		verifyFalse(customerInfoPage.isCustomerInfoPageDisplayed()); // set false intentionally
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
