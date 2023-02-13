package com.nopcommerce.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.nopCommerce.*;
import reportConfig.ExtentTestManagerV5;

import java.lang.reflect.Method;


//Apply POM cho test case

public class Level_15_ExtentReportV5_Screenshot extends BaseTest{
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
	public void beforeClass(String browserName,Method method) {
		/*Có thể làm các step pre-condition nhưng k nên đưa extentReport như 2 line 35 36 dưới đây vào BeforeClass vì khi đưa vào nó sẽ hiểu BeforeClass dc tính là 1 testcase -> tính số lượng testcases sai gây hiểu lầm
		* do đó nếu muốn vẫn dùng log để in ra mà k cần gọi startTest  của extentReport  trong @BeforeClass thì có 2 cách :
		* 1.Ta sẽ k cần ghi log cho pre-condition trong beforeClass như thế này
		* 2.gọi getTest và log ra luôn mà k cần gọi startTest trước đó ví dụ : ExtentTestManagerV5.getTest().log(Status.INFO,"Pre-Condition - Step 01: ....")*/
	//	ExtentTestManagerV5.startTest(method.getName(), "Pre-Condition for all testcases here"); //-> ko nên dùng startTest trong đây
	//	ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 01: Open 'Register' page"); // có thể dùng để gọi log mà k cần tới startTest trước nó -> recommend ko nên vì pre-condition có thể bỏ hẳn log

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
		ExtentTestManagerV5.startTest(method.getName(), "User_01_User_Register");
		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 01: Open 'Register' page");//description note khi in ra log,dùng tên chức năng như tên testcase - Step x - mô tả nội dung step(navigate,action,close,...) + data
		registerPage = homePage.clickToRegisterLink();
		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTxtBox(firstName);
		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTxtBox(lastName);
		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 04: Enter to Email address textbox with value is '" + email + "'");
		registerPage.inputToEmailTxtBox(email);
		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 05: Enter to Pasword textbox with value is '" + validPassword + "'");
		registerPage.inputToPasswordTxtBox(validPassword);
		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 06: Enter to Confirm Pasword textbox with value is '" + confirmPassword + "'");
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);
		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();
		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");

	}
	@Test
	public void User_02_User_Login(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "User_02_User_Login");

		ExtentTestManagerV5.getTest().log(Status.INFO,"Register - Step 09: Click to Continue button");
		homePage = registerPage.clickToContinueButton();
		ExtentTestManagerV5.getTest().log(Status.INFO,"Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		ExtentTestManagerV5.getTest().log(Status.INFO,"Login - Step 02: Enter to Email address textbox with value is '" + email + "'");
		loginPage.inputToEmailTxtBox(email);
		ExtentTestManagerV5.getTest().log(Status.INFO,"Login - Step 03: Enter to Pasword textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTxtBox(validPassword);
		ExtentTestManagerV5.getTest().log(Status.INFO,"Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		ExtentTestManagerV5.getTest().log(Status.INFO,"Login - Step 05: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		ExtentTestManagerV5.getTest().log(Status.INFO,"Login - Step 06: Navigte to 'My Account' link");
		customerInfoPage = homePage.clickToMyAccountLink();
		ExtentTestManagerV5.getTest().log(Status.INFO,"Login - Step 07: Verify to 'Customer Info' link");
		Assert.assertFalse(customerInfoPage.isCustomerInfoPageDisplayed()); // set false intentionally to capture screen shot at fail step


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
