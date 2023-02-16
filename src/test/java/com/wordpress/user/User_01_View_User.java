package com.wordpress.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.wordpress.admin.*;
import pageObjects.wordpress.user.UserHomePO;
import pageObjects.wordpress.user.UserPostDetailPO;
import pageObjects.wordpress.user.UserSearchPostPO;


//Apply POM cho test case

public class User_01_View_User extends BaseTest{
	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPO;
	private AdminPostSearchPO adminPostSearchPO;
	private AdminPostAddNewPO adminPostAddNewPO;
	private UserPostDetailPO userPostDetailPO;
	private UserSearchPostPO userSearchPostPO;
	private UserHomePO userHomePO;
	private AdminUserPO adminUserPO;
	private String adminUserName = "automationFC";
	private String adminPassword = "automationfc";
	private String searchPostUrl;
	private int randNumber = randNumber();
	private String postTitle = "Live Coding Title " + randNumber;
	private String postBody = "Live Coding Body " + randNumber;
	private String author = "Automation FC";
	private String urlAdmin,urlUser ;
	private String currentDate = getCurrentDate();
	private String headerTitleID = "title";
	private String headerAuthorID = "author";
	private String editPostTitle = "Edit Title" + randNumber;
	private String editPostBody = "Edit Body" + randNumber;


	@Parameters({"browser","urlAdmin","urlUser"})
	@BeforeClass
	public void beforeClass(String browserName,String urlAdmin,String urlUser) {
		log.info("Pre-condition - Step 01: Open browser and Admin site");
		driver = getBrowserDriver(browserName,urlAdmin);
		this.urlAdmin = urlAdmin;
		this.urlUser = urlUser;
		adminLoginPage = AdminPageGeneratorManager.getAdminLoginPO(driver);

		log.info("Pre-condition - Step 02: Enter to Username textbox with value : " + adminUserName ); // mục đích khi in cả value data test như thế này để có thể đọc report và manual lại thuận tiện
		adminLoginPage.enterToUserNameTextbox(adminUserName);
		log.info("Pre-condition - Step 03: Enter to Password textbox with value : " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		log.info("Pre-condition - Step 04: Click to Log In button");
		adminDashboardPO = adminLoginPage.clickToLogInButton();



	}
	 

	@Test
	public void User_01_View() {
		log.info("View_user - Step 01: Click to 'User' menu link");
		adminUserPO = adminDashboardPO.clickToUserMenuLink();

		log.info("View_user - Step 02: Count Users from UI");
		int totalUserAtUI = adminUserPO.getUserNumberAtUI();
		log.info("View_user - Step 03: Count User from Database");
		int totalUserAtDatabase = adminUserPO.getUserNumberAtDatabase();
		log.info("View_user - Step 04: Verify number Users from Database and User from UI are equal");
		verifyEquals(totalUserAtUI,totalUserAtDatabase);
	}



	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	
}
