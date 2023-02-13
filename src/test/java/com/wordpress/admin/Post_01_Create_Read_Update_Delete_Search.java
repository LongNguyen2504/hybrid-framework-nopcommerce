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
import pageObjects.wordpress.user.UserSearchPostPO;


//Apply POM cho test case

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest{
	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPO;
	private AdminPostSearchPO adminPostSearchPO;
	private AdminPostAddNewPO adminPostAddNewPO;
	private UserPostDetailPO userPostDetailPO;
	private UserSearchPostPO userSearchPostPO;
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
		log.info("Edit_Post - Step 01: Open Admin Site");
		adminDashboardPO = userPostDetailPO.openAdminDashboardSite(driver,this.urlAdmin);

		log.info("Edit_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPO =adminDashboardPO.clickToPostMenuLink();

		log.info("Edit_Post - Step 03: Enter to 'Search' textbox");
		adminPostSearchPO.enterToSearchTextbox(postTitle);

		log.info("Edit_Post - Step 04: Enter to 'Search Posts' button");
		adminPostSearchPO.clickToSearchPostsButton();

		log.info("Edit_Post - Step 05: Click to Post Title and navigate to Post Edit Page");
		adminPostAddNewPO = adminPostSearchPO.clickToPostTitleLink(postTitle); // vì page add new post = page edit post nên dùng lại POM và method

		log.info("Edit_Post - Step 06: Enter to post title");
		adminPostAddNewPO.enterToPostTitle(editPostTitle);

		log.info("Edit_Post - Step 07: Enter to body");
		adminPostAddNewPO.enterToPostBody(editPostBody);

		log.info("Edit_Post - Step 08: Click to 'Update' button");
		adminPostAddNewPO.clickToUpdateButton();

		log.info("Edit_Post - Step 09: Verify 'Post updated.' message is displayed");
		verifyTrue(adminPostAddNewPO.isPostUpdatedMessageDisplay("Post updated."));

		log.info("Edit_Post - Step 10: Verify at Admin Search Post page");

		log.info("Edit_Post - Step 11: Open 'Search Post' page");
		//Open String viewPostUrl
		adminPostSearchPO = adminPostAddNewPO.openSearchPostPageUrl(searchPostUrl);

		log.info("Edit_Post - Step 12: Enter to 'Search' textbox");
		adminPostSearchPO.enterToSearchTextbox(editPostTitle);

		log.info("Edit_Post - Step 13: Enter to 'Search Posts' button");
		adminPostSearchPO.clickToSearchPostsButton();

		log.info("Edit_Post - Step 14: Verify Search Table contains post title : '" + editPostTitle + "'");
		verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed(headerTitleID,editPostTitle)); // truyền vào id của cột trong table data và value cần verify

		log.info("Edit_Post - Step 15: Verify Search Table contains author name :'" + author + "'");
		verifyTrue(adminPostSearchPO.isPostSearchTableDisplayed(headerAuthorID,author));

		log.info("Edit_Post - Step 16: Open 'End User' site");
		userHomePO = adminPostSearchPO.openEndUserSite(driver,this.urlUser);


		log.info("Edit_Post - Step 17: Verify Post information displayed at Home Page");
		verifyTrue(userHomePO.isPostInforDisplayedWithPostTitle(editPostTitle)); //post title
		verifyTrue(userHomePO.isPostInforDisplayedWithPostBody(editPostTitle,editPostBody)); //post content
		verifyTrue(userHomePO.isPostInforDisplayedWithPostAuthor(editPostTitle,author)); //post author
		verifyTrue(userHomePO.isPostInforDisplayedWithPostCurrentDate(editPostTitle,currentDate)); //post date


		log.info("Edit_Post - Step 18: Click to Post Title");
		userPostDetailPO = userHomePO.clickToPostTitle(editPostTitle);


		log.info("Edit_Post - Step 19: Verify Post information displayed at Post Detail page");
		verifyTrue(userPostDetailPO.isPostInforDisplayedWithPostTitle(editPostTitle));//post title
		verifyTrue(userPostDetailPO.isPostInforDisplayedWithPostBody(editPostTitle,editPostBody)); //post content
		verifyTrue(userPostDetailPO.isPostInforDisplayedWithPostAuthor(editPostTitle,author)); //post author
		verifyTrue(userPostDetailPO.isPostInforDisplayedWithPostCurrentDate(editPostTitle,currentDate)); //post date


	}
	@Test
	public void Post_04_Delete_Post() {
		log.info("Delete_Post - Step 01: Open Admin Site");
		adminDashboardPO = userPostDetailPO.openAdminDashboardSite(driver,this.urlAdmin);

		log.info("Delete_Post - Step 02: Click to 'Posts' menu link");
		adminPostSearchPO =adminDashboardPO.clickToPostMenuLink();

		log.info("Delete_Post - Step 03: Enter to 'Search' textbox");
		adminPostSearchPO.enterToSearchTextbox(editPostTitle);

		log.info("Delete_Post - Step 04: Enter to 'Search Posts' button");
		adminPostSearchPO.clickToSearchPostsButton();

		log.info("Delete_Post - Step 05: Select Post Detail checkbox");
		adminPostSearchPO.selectPostCheckboxByTitle(editPostTitle);

		log.info("Delete_Post - Step 06: Select 'Move to trash' item in dropdown");
		adminPostSearchPO.selectTextItemInActionDropdown("Move to Trash");

		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearchPO.clickToApplyButton();


		//1 post moved to the Trash
		log.info("Delete_Post - Step 08: Verify Message 'post moved to the Trash' displayed");
		verifyTrue(adminPostSearchPO.isMoveToTrashMessageDisplayed("1 post moved to the Trash."));

		log.info("Delete_Post - Step 09: Enter to 'Search' textbox at Admin Site");
		adminPostSearchPO.enterToSearchTextbox(editPostTitle);

		log.info("Delete_Post - Step 10: Enter to 'Search Posts' button at Admin Site");
		adminPostSearchPO.clickToSearchPostsButton();

		log.info("Delete_Post - Step 11: Verify 'No posts found.' message displayed at Admin Site");
		verifyTrue(adminPostSearchPO.isNoPostFoundMessageDisplayed("No posts found."));

		log.info("Edit_Post - Step 12: Open 'End User' site");
		userHomePO = adminPostSearchPO.openEndUserSite(driver,this.urlUser);

		log.info("Delete_Post - Step 13: Verify Post title undisplayed at User Home Page");
		verifyTrue(userHomePO.isPostInforUndisplayedWithPostTitle(editPostTitle));

		log.info("Delete_Post - Step 14: Enter to 'Search' textbox at User Home Page");
		userHomePO.enterToSearchTextbox(editPostTitle);

		log.info("Delete_Post - Step 15: Enter to 'Search Posts' button at User Home Page");
		userSearchPostPO = userHomePO.clickToSearchPostsButton();

		log.info("Delete_Post - Step 16: Verify 'Nothing Found' message displayed at User Search Post ");
		verifyTrue(userSearchPostPO.isNothingFoundMessageDisplayed("Nothing Found"));






	}


	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	
}
