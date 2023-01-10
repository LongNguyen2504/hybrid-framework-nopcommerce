package com.nopcommerce.user;

import com.relevantcodes.extentreports.LogStatus;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.nopCommerce.*;
import reportConfig.ExtentManager;

import java.lang.reflect.Method;


//Apply POM cho test case

public class Level_15_ExtentReportV2_Screenshot extends BaseTest{
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
	 

	//Vì extent report sử dụng log riêng khác với reportNG nên cần code lại như sau
	@Test
	public void User_01_User_Register(Method method) {
		ExtentManager.startTest(method.getName(), "User_01_User_Register");
		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 01: Open 'Register' page");//log.info() = description note khi in ra log,dùng tên chức năng như tên testcase - Step x - mô tả nội dung step(navigate,action,close,...) + data
		registerPage = homePage.clickToRegisterLink();
		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTxtBox(firstName);
		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTxtBox(lastName);
		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 04: Enter to Email address textbox with value is '" + email + "'");
		registerPage.inputToEmailTxtBox(email);
		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 05: Enter to Pasword textbox with value is '" + validPassword + "'");
		registerPage.inputToPasswordTxtBox(validPassword);
		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 06: Enter to Confirm Pasword textbox with value is '" + confirmPassword + "'");
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);
		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();
		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed....");
		ExtentManager.endTest();

	}
	@Test
	public void User_02_User_Login(Method method) {
		ExtentManager.startTest(method.getName(), "User_02_User_Login");

		ExtentManager.getTest().log(LogStatus.INFO,"Register - Step 09: Click to Continue button");
		homePage = registerPage.clickToContinueButton();
		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 02: Enter to Email address textbox with value is '" + email + "'");
		loginPage.inputToEmailTxtBox(email);
		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 03: Enter to Pasword textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTxtBox(validPassword);
		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 05: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 06: Navigte to 'My Account' link");
		customerInfoPage = homePage.clickToMyAccountLink();
		ExtentManager.getTest().log(LogStatus.INFO,"Login - Step 07: Verify to 'Customer Info' link");
		Assert.assertFalse(customerInfoPage.isCustomerInfoPageDisplayed()); // set false intentionally to capture screen shot at fail step


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
