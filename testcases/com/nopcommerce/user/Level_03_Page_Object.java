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
import pageObjects.RegisterPageObject;


//Apply POM cho test case

public class Level_03_Page_Object{
	//extends class BasePage để có thể dùng tất cả các method có modifier là protected của BasePage và giúp che giấu việc khởi tạo new BasePage,khai báo
	private WebDriver driver;
	private String emailAddress,firstName,lastName,password,confirmPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		firstName = "Automation";
		lastName = "FC" ;
		password = "123456" ;
		confirmPassword = "123456" ;
		emailAddress = "afc" + randNumber() + "@mail.vn";
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		

	}

	@Test
	public void Register_01_Empty_Data() {
//		waitForElementClickable(driver, "//a[@class='ico-register']");
//		clickToElement(driver, "//a[@class='ico-register']");
		System.out.println("Register_01 - Step 01 : Click to register link");
		homePage.clickToRegisterLink();
		
//		waitForElementClickable(driver, "//button[@id='register-button']");
//		clickToElement(driver, "//button[@id='register-button']");
		System.out.println("Register_01 - Step 02 : Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_01 - Step 03 : Verify error message displayed");

		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTxtBox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTxtBox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTxtBox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPaswordTxtBox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTxtBox(), "Password is required.");

/*		Assert.assertEquals(getElementText(driver, "span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "span[@id='ConfirmPassword-error']"), "Password is required.");
*/

	}

	@Test
	public void Register_02_Invalid_Email() {
//		waitForElementClickable(driver, "//a[@class='ico-register']");
//		clickToElement(driver, "//a[@class='ico-register']");
		System.out.println("Register_02 - Step 01 : Click to register link");
		homePage.clickToRegisterLink();
		
		
//		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
//		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
//		sendkeyToElement(driver, "//input[@id='Email']", "111@323$%^");
//		sendkeyToElement(driver, "//input[@id='Password']", "123456");
//		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		System.out.println("Register_02 - Step 02 : Input to required fields");
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox("111@323$%^");
		registerPage.inputToPasswordTxtBox(password);
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);
		
		
		
		System.out.println("Register_02 - Step 03 : Click to register button");
		registerPage.clickToRegisterButton();
		
//		waitForElementClickable(driver, "//button[@id='register-button']");
//		clickToElement(driver, "//button[@id='register-button']");
		System.out.println("Register_02 - Step 04 : Verify error message email display");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTxtBox(), "Wrong email");

//		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03 - Step 01 : Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_03 - Step 02 : Input to required fields");
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox(emailAddress);
		registerPage.inputToPasswordTxtBox(password);
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);

		System.out.println("Register_03 - Step 03 : Click to register button");
		registerPage.clickToRegisterButton();

		
		System.out.println("Register_03 - Step 04 : Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");


	}

	@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04 -Step 01 : Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_04 - Step 02 : Input to required fields");
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox(emailAddress);
		registerPage.inputToPasswordTxtBox(password);
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);

		System.out.println("Register_04 - Step 03 : Click to register button");
		registerPage.clickToRegisterButton();

		
		System.out.println("Register_04 - Step 04 : Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Pwd_Less_Than_6_Chars() {
		System.out.println("Register_05 - Step 01 : Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_05 - Step 02 : Input to required fields");
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox(emailAddress);
		registerPage.inputToPasswordTxtBox("123");
		registerPage.inputToConfirmPasswordTxtBox("123");

		System.out.println("Register_05 - Step 03 : Verify error password message");
	
		Assert.assertEquals(registerPage.getErrorMessageAtPaswordTxtBox(), "Password must meet the following rules:\nmust have at least 6 characters");



	}

	@Test
	public void Register_06_Invalid_Confirm_Pwd() {
		System.out.println("Register_06 - Step 01 : Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_06 - Step 02 : Input to required fields");
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox(emailAddress);
		registerPage.inputToPasswordTxtBox(password);
		registerPage.inputToConfirmPasswordTxtBox("123334");



		
		System.out.println("Register_06 - Step 03 : Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_06 - Step 04 : Verify error confirm password message");

		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTxtBox(), "The password and confirmation password do not match.");
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
