package com.nopcommerce.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserLoginPageObject;
import pageObjects.user.nopCommerce.UserRegisterPageObject;


//Apply POM cho test case

public class Level_16_Share_Data_B extends BaseTest{
	private WebDriver driver;
	public static String email,validPassword;
	private  String firstName,lastName,confirmPassword;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		/*Vì ở đây Level_16_Share_Data_B không muốn sử dụng lại user/pass đã tạo ở class Common_01_Register nên trong beforeClass tại đây sẽ chạy lại các step đăng ký user/pass mới để sử dụng riêng cho class này*/
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePageObject(driver);

		firstName = "Automation";
		lastName = "FC" ;
		validPassword = "123456" ;
		confirmPassword = "123456" ;
		email = "afc" + randNumber() + "@mail.vn";


		//Để các log này được chạy thì cần file log config để biết ghi log vào file nào,testcase nào trong package nào sẽ được apply log này
		log.info("Pre-Condition - Step 01: Open 'Register' page"); //log.info() = description note khi in ra log,dùng tên chức năng như tên testcase - Step x - mô tả nội dung step(navigate,action,close,...) + data
		registerPage = homePage.clickToRegisterLink();
		log.info("Pre-Condition - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstnameTxtBox(firstName);
		log.info("Pre-Condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastnameTxtBox(lastName);
		log.info("Pre-Condition - Step 04: Enter to Email address textbox with value is '" + email + "'");
		registerPage.inputToEmailTxtBox(email);
		log.info("Pre-Condition - Step 05: Enter to Pasword textbox with value is '" + validPassword + "'");
		registerPage.inputToPasswordTxtBox(validPassword);
		log.info("Pre-Condition - Step 06: Enter to Confirm Pasword textbox with value is '" + confirmPassword + "'");
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);
		log.info("Pre-Condition - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();
		log.info("Pre-Condition - Step 08: Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		log.info("Pre-Condition - Step 09: Click to Continue button");
		homePage = registerPage.clickToContinueButton();

		/*Login*/
		log.info("Pre-Condition - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		log.info("Pre-Condition - Step 02: Enter to Email address textbox with value is '" + email + "'");
		loginPage.inputToEmailTxtBox(email);
		log.info("Pre-Condition - Step 03: Enter to Pasword textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTxtBox(validPassword);
		log.info("Pre-Condition - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
	}
	 

	@Test
	public void Search_01_Empty_Data() {



	}

	@Test
	public void Search_02_Relative_Product_Name() {



	}
	@Test
	public void Search_03_Absolute_Product_Name() {



	}
	@Test
	public void Search_04_Parent_Category() {



	}
	@Test
	public void Search_05_Incorrect_Manufactorer() {



	}
	@Test
	public void Search_06_Correct_Manufactorer() {



	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
