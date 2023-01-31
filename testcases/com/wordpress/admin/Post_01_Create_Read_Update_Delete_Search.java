package com.wordpress.admin;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.wordpress.admin.*;


//Apply POM cho test case

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest{
	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPO;
	private AdminPostSearchPO adminPostSearchPO;
	private AdminPostAddNewPO adminPostAddNewPO;
	private String adminUserName = "automationFC";
	private String adminPassword = "automationfc";
	private String searchPostUrl;
	private int randNumber = randNumber();
	private String postTitle = "Live Coding Title " + randNumber;
	private String postBody = "Live Coding Body " + randNumber;


	@Parameters({"browser","urlName"})
	@BeforeClass
	public void beforeClass(String browserName,String urlAdmin) {
		log.info("Pre-condition - Step 01: Open browser and get admin URL");
		driver = getBrowserDriver(browserName,urlAdmin);
		adminLoginPage = AdminPageGeneratorManager.getAdminLoginPO(driver);

		log.info("Pre-condition - Step 02: Enter to Username textbox with value : " + adminUserName ); // mục đích khi in cả value data test như thế này để có thể đọc report và manual lại thuận tiện
		adminLoginPage.enterToUserNameTextbox(adminUserName);
		log.info("Pre-condition - Step 03: Enter to Password textbox with value : " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);
		log.info("Pre-condition - Step 04: Click to Log In button");
		adminDashboardPO = adminLoginPage.clickToLogInButton();





	}
	 

	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create_Post - Step 01: Click to 'Posts' menu link");
		adminPostSearchPO =adminDashboardPO.clickToPostMenuLink();

		log.info("Create_Post - Step 02: Get 'Search Posts' page Url");
		searchPostUrl = adminPostSearchPO.getPageUrl(driver);

		log.info("Create_Post - Step 03: Click to 'Add New' button");
		adminPostAddNewPO = adminPostSearchPO.clickToAddNewButton();

		log.info("Create_Post - Step 04: Enter to post title");
		adminPostAddNewPO.enterToPostTitle(postTitle);

		log.info("Create_Post - Step 05: Enter to body");
		adminPostAddNewPO.enterToPostBody(postBody);

		log.info("Create_Post - Step 06: Click to 'Publish' button");
		adminPostAddNewPO.clickToPublishButton();

		log.info("Create_Post - Step 07: Click to Confirm 'Publish' button");
		adminPostAddNewPO.clickToConfirmPublishButton();

		log.info("Create_Post - Step 08: Verify 'Post published.' message is displayed");
		adminPostAddNewPO.isPostPublishedMessageDisplay("Post published..");






	}
	@Test
	public void Post_02_Search_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		//Open String viewPostUrl
		adminPostSearchPO = adminPostAddNewPO.openSearchPostPageUrl(searchPostUrl);


	}
	@Test
	public void Post_03_View_Post() {


	}
	@Test
	public void Post_04_Edit_Post() {


	}
	@Test
	public void Post_05_Delete_Post() {


	}


	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	
}
