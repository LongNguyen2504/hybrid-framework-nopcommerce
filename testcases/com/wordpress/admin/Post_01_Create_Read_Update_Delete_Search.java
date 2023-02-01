package com.wordpress.admin;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.wordpress.admin.*;
import pageObjects.wordpress.user.UserHomePO;
import pageObjects.wordpress.user.UserPageGeneratorManager;
import pageObjects.wordpress.user.UserPostDetailPO;


//Apply POM cho test case

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest{
	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPO;
	private AdminPostSearchPO adminPostSearchPO;
	private AdminPostAddNewPO adminPostAddNewPO;
	private UserPostDetailPO userPostDetailPO;
	private UserHomePO userHomePO;
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
		verifyTrue(adminPostAddNewPO.isPostPublishedMessageDisplay("Post published."));






	}
	@Test
	public void Post_02_Search_And_View_Post() {
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		//Open String viewPostUrl
		adminPostSearchPO = adminPostAddNewPO.openSearchPostPageUrl(searchPostUrl);

		log.info("Search_Post - Step 02: Enter to 'Search' textbox");
		adminPostSearchPO.enterToSearchTextbox(postTitle);

		log.info("Search_Post - Step 03: Enter to 'Search Posts' button");
		adminPostSearchPO.clickToSearchPostsButton();

		log.info("Search_Post - Step 04: Verify Search Table contains post title : '" + postTitle + "'");
		verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed(headerTitleID,postTitle)); // truyền vào id của cột trong table data và value cần verify

		log.info("Search_Post - Step 05: Verify Search Table contains author name :'" + author + "'");
		verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed(headerAuthorID,author));

		log.info("Search_Post - Step 06: Open 'End User' site");
		userHomePO = adminPostSearchPO.openEndUserSite(driver,this.urlUser);


		log.info("Search_Post - Step 07: Verify Post information displayed at Home Page");
		verifyTrue(userHomePO.isPostInforDisplayedWithPostTitle(postTitle)); //post title
		verifyTrue(userHomePO.isPostInforDisplayedWithPostBody(postTitle,postBody)); //post content
		verifyTrue(userHomePO.isPostInforDisplayedWithPostAuthor(postTitle,author)); //post author
		verifyTrue(userHomePO.isPostInforDisplayedWithPostCurrentDate(postTitle,currentDate)); //post date


		log.info("Search_Post - Step 08: Click to Post Title");
		userPostDetailPO = userHomePO.clickToPostTitle(postTitle);


		log.info("Search_Post - Step 09: Verify Post information displayed at Post Detail page");
		verifyTrue(userPostDetailPO.isPostInforDisplayedWithPostTitle(postTitle));//post title
		verifyTrue(userPostDetailPO.isPostInforDisplayedWithPostBody(postTitle,postBody)); //post content
		verifyTrue(userPostDetailPO.isPostInforDisplayedWithPostAuthor(postTitle,author)); //post author
		verifyTrue(userPostDetailPO.isPostInforDisplayedWithPostCurrentDate(postTitle,currentDate)); //post date


	}
	@Test
	public void Post_03_Edit_Post() {


	}
	@Test
	public void Post_04_Delete_Post() {


	}


	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	
}
