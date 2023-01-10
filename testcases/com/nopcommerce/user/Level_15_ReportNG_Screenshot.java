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

public class Level_15_ReportNG_Screenshot extends BaseTest{
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
		//Để các log này được chạy thì cần file log config để biết ghi log vào file nào,testcase nào trong package nào sẽ được apply log này
		log.info("Register - Step 01: Open 'Register' page"); //log.info() = description note khi in ra log,dùng tên chức năng như tên testcase - Step x - mô tả nội dung step(navigate,action,close,...) + data

		registerPage = homePage.clickToRegisterLink();
		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTxtBox(firstName);
		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTxtBox(lastName);
		log.info("Register - Step 04: Enter to Email address textbox with value is '" + email + "'");
		registerPage.inputToEmailTxtBox(email);
		log.info("Register - Step 05: Enter to Pasword textbox with value is '" + validPassword + "'");
		registerPage.inputToPasswordTxtBox(validPassword);
		log.info("Register - Step 06: Enter to Confirm Pasword textbox with value is '" + confirmPassword + "'");
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);
		log.info("Register - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();
		log.info("Register - Step 08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed....");

	}
	@Test
	public void User_02_User_Login() {
		log.info("Register - Step 09: Click to Continue button");
		homePage = registerPage.clickToContinueButton();
		log.info("Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		log.info("Login - Step 02: Enter to Email address textbox with value is '" + email + "'");
		loginPage.inputToEmailTxtBox(email);
		log.info("Login - Step 03: Enter to Pasword textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTxtBox(validPassword);
		log.info("Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		log.info("Login - Step 05: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		log.info("Login - Step 06: Navigte to 'My Account' link");
		customerInfoPage = homePage.clickToMyAccountLink();
		log.info("Login - Step 07: Verify to 'Customer Info' link");
		Assert.assertFalse(customerInfoPage.isCustomerInfoPageDisplayed()); // set false intentionally to capture screen shot at fail step


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
