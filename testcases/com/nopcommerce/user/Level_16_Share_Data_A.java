package com.nopcommerce.user;

import com.nopcommerce.common.Common_01_Register_End_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.nopCommerce.*;


//Apply POM cho test case

public class Level_16_Share_Data_A extends BaseTest{
	private WebDriver driver;
	private String email= "";
	private String validPassword = "";
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
		/*Gọi các email và validPassword đã chạy ở class Common_01_Register(class này dc xem như common data test cho các classes test sử dụng lại) để sử dụng cho test class này*/
		email = Common_01_Register_End_User.email;
		validPassword = Common_01_Register_End_User.validPassword;
		/*Login*/
		log.info("Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		log.info("Login - Step 02: Enter to Email address textbox with value is '" + email + "'");
		loginPage.inputToEmailTxtBox(email);
		log.info("Login - Step 03: Enter to Pasword textbox with value is '" + validPassword + "'");
		loginPage.inputToPasswordTxtBox(validPassword);
		log.info("Login - Step 04: Click to Login button");
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
