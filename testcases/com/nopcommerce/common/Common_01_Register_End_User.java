package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.*;


//Apply POM cho test case

public class Common_01_Register_End_User extends BaseTest{
	private WebDriver driver;
	public static String email,validPassword;
	private  String firstName,lastName,confirmPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;



	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Classes Test") //Dùng @BeforeTest vì trong testNG @BeforeTest sẽ chạy trước @BeforeClass -> class này sẽ dc chạy đầu tiên trước tất cả các classes test còn lại vì như vậy các classes test còn lại sẽ có data test để sử dụng
	public void User_01_User_Register(String browserName) {
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
		driver.quit();
	}



	/*Nếu để driver.quit() trong @AfterTest thì sau khi tất cả các class test chạy xong thì cửa số đăng ký user/pass của class Common_01_Register_End_User này mới dc đóng do đó ta sẽ để driver.quit() vào trong @BeforeTest để nó tự đóng ngay sau khi đăng ký user/pass xong */
	/*@AfterTest
	public void afterClass() {
		driver.quit();
	}*/

	
}
