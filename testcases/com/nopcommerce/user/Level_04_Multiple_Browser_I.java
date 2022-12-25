package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;


//Apply POM cho test case

public class Level_04_Multiple_Browser_I{
	private WebDriver driver;
	private String emailAddress,firstName,lastName,password,confirmPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String projectPath = System.getProperty("user.dir");

	//Run multi browser cách 1 : dùng parameter của testNG và if else trong @Before class để control browser value từ xml gửi qua 
	//-> làm cách này thì phải update bằng tay nếu có vài trăm class thì không khả thi -> implement base test để các test class extend và sử dụng
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on "+ browserName);
		
		if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println(driver.toString());
		}else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println(driver.toString());
		}else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
			System.out.println(driver.toString());
		}else {
			throw new RuntimeException("Browser name invalid");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageObject(driver);
		
		firstName = "Automation";
		lastName = "FC" ;
		password = "123456" ;
		confirmPassword = "123456" ;
		emailAddress = "afc" + randNumber() + "@mail.vn";
		
		System.out.println("Register_03 - Step 01 : Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_03 - Step 02 : Input to required fields");
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox(emailAddress);
		registerPage.inputToPasswordTxtBox(password);
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);

		System.out.println("Register_03 - Step 03 : Click to register button");
		registerPage.clickToRegisterButton();

		
		System.out.println("Register_03 - Step 04 : Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("Register_03 - Step 05 : Click to continue button");
		registerPage.clickToContinueButton();
		
		
		

	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01 : Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		
		System.out.println("Register_01 - Step 02 : Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_01 - Step 03 : Verify error message displayed");

		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTxtBox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTxtBox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTxtBox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPaswordTxtBox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTxtBox(), "Password is required.");



	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02 - Step 01 : Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		System.out.println("Register_02 - Step 02 : Input to required fields");
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox("111@323$%^");
		registerPage.inputToPasswordTxtBox(password);
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);
		
		
		
		System.out.println("Register_02 - Step 03 : Click to register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_02 - Step 04 : Verify error message email display");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTxtBox(), "Wrong email");


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
/*--------XML----------
 * <?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite parallel="false" name="NopCommerce">

	<test name="Run on Firefox">
		<parameter name="browser" value="firefox" />
		<classes>
			<class name="com.nopcommerce.user.Level_04_Multiple_Browser_I" />
		</classes>
	</test>

	<test name="Run on Chrome">
		<parameter name="browser" value="chrome" />
		<classes>
			<class name="com.nopcommerce.user.Level_04_Multiple_Browser_I" />
		</classes>
	</test>

	<test name="Run on Edge">
		<parameter name="browser" value="edge" />
		<classes>
			<class name="com.nopcommerce.user.Level_04_Multiple_Browser_I" />
		</classes>
	</test>


	<!-- Test -->

</suite> <!-- Suite -->
 * 
 * */
