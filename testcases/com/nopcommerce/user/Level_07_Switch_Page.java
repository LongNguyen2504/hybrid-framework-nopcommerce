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
import pageObjects.nopcommerce.AddressPageObject;
import pageObjects.nopcommerce.CustomerInfoPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;
import pageObjects.nopcommerce.RegisterPageObject;
import pageObjects.nopcommerce.RewardPointPageObject;


//Apply POM cho test case

public class Level_07_Switch_Page extends BaseTest{
	private WebDriver driver;
	private String email,firstName,lastName,validPassword,confirmPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private AddressPageObject addressPage;
	private RewardPointPageObject rewardPointPage;

	//Cách 3 : Khởi tạo qua method của class PageGeneratorManager
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePageObject(driver);
		firstName = "Automation";
		lastName = "FC" ;
		validPassword = "123456" ;
		confirmPassword = "123456" ;
		email = "afc" + randNumber() + "@mail.vn";
	}

	@Test
	public void User_01_Register() {
		registerPage =homePage.clickToRegisterLink(); 
		registerPage.inputToFirstnameTxtBox(firstName);
		registerPage.inputToLastnameTxtBox(lastName);
		registerPage.inputToEmailTxtBox(email);
		registerPage.inputToPasswordTxtBox(validPassword);
		registerPage.inputToConfirmPasswordTxtBox(confirmPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		homePage = registerPage.clickToContinueButton();
	}
	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();// Cách 2 : page manager
		loginPage.inputToEmailTxtBox(email);
		loginPage.inputToPasswordTxtBox(validPassword);
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}
	@Test
	public void User_03_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());


	}

	//@Test
	public void User_04_Switch_Page() {
		
		//Cách này sẽ tạo ra DRY,số lượng pageObject,pageUI nhiều nếu gọi 2 chiều vd 1 page cần 13 hàm để mở 13 pages còn lại nếu ta có 14 page cần switch => 14 pages x 13 hàm = 182 hàm
		//Solution => tạo các hàm để open từng page trong basepage và 1 basepageUI để lưu locator của từng page
		//Lưu ý : pre-condition của business để đáp ứng sự hợp lý của việc switch page vd cần phải login thành công thì mới dc switch các page trong trang MyAccountDashboard
		//Customer -> addresses
		
//		  addressPage = customerInfoPage.openAddressPage(); //addresses-> Customer customerInfoPage = addressPage.openCustomerInfoPage(); //addresses -> RewardPoint
//		  rewardPointPage = addressPage.openRewardPointPage(); //RewardPoint-> addresses addressPage = rewardPointPage.openAddressPage(); //RewardPoint -> Customer
//		  customerInfoPage = rewardPointPage.openCustomerInfoPage(); // Customer -> RewardPoint rewardPointPage = customerInfoPage.openRewardPointPage();
		 

	}
	@Test
	public void User_04_Switch_Page_Refactor() {
		//Cách switch base sử dụng basepage và basepageUI, *cần tham số driver
		addressPage = customerInfoPage.openAddressPage(driver);
		//addresses-> Customer  
		customerInfoPage = addressPage.openCustomerInfoPage(driver);
		//addresses -> RewardPoint
		rewardPointPage = addressPage.openRewardPointPage(driver);
		//RewardPoint-> addresses 
		addressPage = rewardPointPage.openAddressPage(driver);
		//RewardPoint -> Customer
		customerInfoPage = rewardPointPage.openCustomerInfoPage(driver);
		// Customer -> RewardPoint
		rewardPointPage = customerInfoPage.openRewardPointPage(driver);
		
	}
	
	@Test
	public void User_05_Switch_Role() {
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
