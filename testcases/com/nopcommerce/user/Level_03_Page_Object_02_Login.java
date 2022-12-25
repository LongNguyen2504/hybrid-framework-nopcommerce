package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;


//Apply POM cho test case

public class Level_03_Page_Object_02_Login{
	//extends class BasePage để có thể dùng tất cả các method có modifier là protected của BasePage và giúp che giấu việc khởi tạo new BasePage,khai báo
	private WebDriver driver;
	private String existingEmail,notFoundEmail,invalidEmail,firstName,lastName,password,confirmPassword,incorrectPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		homePage = new HomePageObject(driver);
		
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
		
		registerPage = new RegisterPageObject(driver);

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
		homePage = new HomePageObject(driver); // nguyên tắc cứ chuyển page là phải tạo instance
		
		

	}

	@Test
	public void Login_01_Empty_Data() {
		
		homePage.clickToLoginLink();
		
		//home page - login link - login page
		loginPage = new LoginPageObject(driver);
		loginPage.clickToLoginButton();
		
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTxtBox(), "Please enter your email");

	}
	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();
		//home page - login link - login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTxtBox(invalidEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTxtBox(), "Wrong email");

		
		


	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();
		//home page - login link - login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTxtBox(notFoundEmail);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");


	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		System.out.println("Register_04 -Step 01 : Click to register link");
		homePage.clickToLoginLink();
		//home page - login link - login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox("");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		System.out.println("Register_05 - Step 01 : Click to register link");
		homePage.clickToLoginLink();
		//home page - login link - login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox(incorrectPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getUnsuccessErrorMessageAtEmailTxtBox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");



	}

	@Test
	public void Login_06_Valid_Email_password() {
		System.out.println("Register_06 - Step 01 : Click to register link");
		homePage.clickToLoginLink();
		//home page - login link - login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTxtBox(existingEmail);
		loginPage.inputToPasswordTxtBox(password);
		loginPage.clickToLoginButton();

		//Login thành công -> homepage

		HomePageObject homePage = new HomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());


		
	}

	int randNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
