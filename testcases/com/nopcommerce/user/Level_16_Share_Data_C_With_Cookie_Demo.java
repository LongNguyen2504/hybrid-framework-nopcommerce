package com.nopcommerce.user;

import com.nopcommerce.common.Common_01_Register_Cookie;
import com.nopcommerce.common.Common_01_Register_End_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserLoginPageObject;

import java.util.Set;


//Apply POM cho test case

public class Level_16_Share_Data_C_With_Cookie_Demo extends BaseTest{
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
		/*Login*/
		log.info("Pre-Condition - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		log.info("Pre-Condition - Step 02: Set cookie and reload page '" + email + "'");
		loginPage.setCookies(driver,Common_01_Register_Cookie.loggedCookie);
		for (Cookie cookie : Common_01_Register_Cookie.loggedCookie) {
			System.out.println("Cookie at C class : "+cookie);
		}

		loginPage.refreshPage(driver); /*Phải refresh thì mới apply dc các cookies vừa set bên trên*/

		log.info("Pre-Condition - Step 05: Verify 'My Account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());




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
