package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.admin.nopCommerce.AdminDashboardPageObject;
import pageObjects.admin.nopCommerce.AdminLoginPageObject;
import pageObjects.user.nopCommerce.UserAddressPageObject;
import pageObjects.user.nopCommerce.UserCustomerInfoPageObject;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserLoginPageObject;
import pageObjects.user.nopCommerce.UserRegisterPageObject;
import pageObjects.user.nopCommerce.UserRewardPointPageObject;


//Apply POM cho test case

public class Level_08_Switch_Role extends BaseTest{
	private WebDriver driver;
	private String userEmailAddress,firstName,lastName,userValidPassword,userConfirmPassword;
	private String adminEmailAddress,adminPassword;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private UserAddressPageObject userAddressPage;
	private UserRewardPointPageObject userRewardPointPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePageObject(driver);
		firstName = "Automation";
		lastName = "FC" ;
		userValidPassword = "123456" ;
		userConfirmPassword = "123456" ;
		userEmailAddress = "afc" + randNumber() + "@mail.vn";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_User_Register() {
		userRegisterPage =userHomePage.clickToRegisterLink(); 
		userRegisterPage.inputToFirstnameTxtBox(firstName);
		userRegisterPage.inputToLastnameTxtBox(lastName);
		userRegisterPage.inputToEmailTxtBox(userEmailAddress);
		userRegisterPage.inputToPasswordTxtBox(userValidPassword);
		userRegisterPage.inputToConfirmPasswordTxtBox(userConfirmPassword);
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		userHomePage = userRegisterPage.clickToContinueButton();
	}
	@Test
	public void Role_02_User_Login() {
		userLoginPage = userHomePage.clickToLoginLink();
		//Login as User role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress,userValidPassword); // warpper inputEmail,inputPass,clickLoginBtn
		
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}
	@Test
	public void Role_03_Admin_Login() {
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver); // sau khi mo browser,get url thi phai khoi tao pageObject truc tiep 
		//Login as admin role
		adminDashboardPage =  adminLoginPage.loginAsAdmin(adminEmailAddress,adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
