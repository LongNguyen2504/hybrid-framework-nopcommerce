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
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;
import pageObjects.nopcommerce.RegisterPageObject;


//Apply POM cho test case

public class Level_06_Page_Generator_Manager_III extends BaseTest{
	private WebDriver driver;
	private String existingEmail,notFoundEmail,invalidEmail,firstName,lastName,password,confirmPassword,incorrectPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;

	//Cách 3 : Khởi tạo qua method của class PageGeneratorManager
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePageObject(driver);
		firstName = "Automation";
		lastName = "FC" ;
		password = "123456" ;
		confirmPassword = "123456" ;
		incorrectPassword = "654321";
		existingEmail = "afc" + randNumber() + "@mail.vn";
		notFoundEmail = "afc" + randNumber() + "@mail.com";
		invalidEmail = "afc@afc.com@.vn";
		
		
		System.out.println("Pre-condition - Step 01 : Click to register link");
		registerPage =homePage.clickToRegisterLink(); 
		
		


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
		homePage = registerPage.clickToContinueButton();
		

	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		loginPage.clickToLoginButton();
		
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTxtBox(), "Please enter your email");

	}
	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		loginPage.inputToEmailTxtBox(invalidEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTxtBox(), "Wrong email");

		
		


	}

	@Test
	public void Login_03_Email_Not_Found() {
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		loginPage.inputToEmailTxtBox(notFoundEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");


	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		System.out.println("Register_04 ");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox("");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		System.out.println("Register_05 ");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox(incorrectPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");



	}

	@Test
	public void Login_06_Valid_Email_password() {
		System.out.println("Register_06");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox(password);
		homePage = loginPage.clickToLoginButton(); // Cách 2 : page manager
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
