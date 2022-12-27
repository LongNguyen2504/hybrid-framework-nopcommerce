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
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserLoginPageObject;
import pageObjects.user.nopCommerce.UserRegisterPageObject;


//Apply POM cho test case

public class Level_06_Page_Generator_Manager_II extends BaseTest{
	private WebDriver driver;
	private String existingEmail,notFoundEmail,invalidEmail,firstName,lastName,password,confirmPassword,incorrectPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	//Cách 2 : Ko tạo instance trực tiếp trong testcase nữa
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = new UserHomePageObject(driver); //Cách 2 page manager vẫn phải để nguyên line này -> vẫn bị lặp lại -> dùng cách 3 
		
		firstName = "Automation";
		lastName = "FC" ;
		password = "123456" ;
		confirmPassword = "123456" ;
		incorrectPassword = "654321";
		existingEmail = "afc" + randNumber() + "@mail.vn";
		notFoundEmail = "afc" + randNumber() + "@mail.com";
		invalidEmail = "afc@afc.com@.vn";
		
		
		System.out.println("Pre-condition - Step 01 : Click to register link");
		homePage.clickToRegisterLink(); 
		
		
		registerPage = new UserRegisterPageObject(driver);// Cách 2 : page manager : registerPage = homePage.clickToRegisterLink(); // method clickToRegisterLink return new RegisterPageObject()


		System.out.println("Pre-condition - Step 02 : Input to required fields");
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox(existingEmail);
		registerPage.inputToPasswordTxtBox(password);
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);

		System.out.println("Pre-condition - Step 03 : Click to register button");
		registerPage.clickToRegisterButton();

		
		System.out.println("Pre-condition - Step 04 : Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition - Step 05 : Click to continue button");
		registerPage.clickToContinueButton();
		homePage = new UserHomePageObject(driver); // nguyên tắc cứ chuyển page là phải tạo instance
		//homePage = registerPage.clickToContinueButton(); // Cách 2 : page manager,line này thay thế 2 line trên
		

	}

	@Test
	public void Login_01_Empty_Data() {
		
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		//loginPage = homePage.clickToLoginLink();// Cách 2 : page manager,line này thay thế 2 line trên
		loginPage.clickToLoginButton();
		
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTxtBox(), "Please enter your email");

	}
	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		//loginPage = homePage.clickToLoginLink();// Cách 2 : page manager,line này thay thế 2 line trên
		loginPage.inputToEmailTxtBox(invalidEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTxtBox(), "Wrong email");

		
		


	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		//loginPage = homePage.clickToLoginLink();// Cách 2 : page manager,line này thay thế 2 line trên
		loginPage.inputToEmailTxtBox(notFoundEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");


	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		System.out.println("Register_04 -Step 01 : Click to register link");
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		//loginPage = homePage.clickToLoginLink();// Cách 2 : page manager,line này thay thế 2 line trên
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox("");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		System.out.println("Register_05 - Step 01 : Click to register link");
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		//loginPage = homePage.clickToLoginLink();// Cách 2 : page manager,line này thay thế 2 line trên
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox(incorrectPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");



	}

	@Test
	public void Login_06_Valid_Email_password() {
		System.out.println("Register_06 - Step 01 : Click to register link");
		homePage.clickToLoginLink();
		loginPage = new UserLoginPageObject(driver);
		//loginPage = homePage.clickToLoginLink();// Cách 2 : page manager,line này thay thế 2 line trên
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox(password);
		loginPage.clickToLoginButton();
		UserHomePageObject homePage = new UserHomePageObject(driver);
		//homePage = loginPage.clickToLoginButton(); // Cách 2 : page manager,line này thay thế 2 line trên
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
