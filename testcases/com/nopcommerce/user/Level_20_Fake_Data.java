package com.nopcommerce.user;

import com.nopcommerce.data.UserData;
import com.nopcommerce.data.UserDataMapper;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.nopCommerce.*;
import utilities.DataHelper;

//Class này demo cho cả phần manage data test và data driven test
//Apply POM cho test case

public class Level_20_Fake_Data extends BaseTest{
	private WebDriver driver;
	private DataHelper dataFaker;
	private String email,firstName,lastName,validPassword,confirmPassword,date,month,year;
	private String userName,pass;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserDataMapper userDataMapper;
	private UserAddressPageObject addressPage;
	private UserRewardPointPageObject rewardPointPage;
	private UserMyProductReviewPageObject myProductReviewPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		dataFaker = DataHelper.getData();
		homePage = PageGeneratorManager.getUserHomePageObject(driver);
		//Dưới đây là cách gọi data thứ 1 (best practice) coi trong bài manage data test
		firstName = dataFaker.getFirstName();
		lastName = dataFaker.getLastName() ;
		validPassword = dataFaker.getPassword();
		confirmPassword = validPassword ;
		email = dataFaker.getEmailAddress();
		date = "10";
		month = "August";
		year = "1996";

		//Cách gọi data test từ folfer testdata(cách 3 bài manage test data)
		/*firstName = UserData.FakeData.FIRST_NAME;
		lastName = UserData.FakeData.LAST_NAME;*/ // tương tự cho các biến còn lại

		//Cách dùng class UserDataMapper để map dữ liệu từ json sang class test
/*		userDataMapper = UserDataMapper.getUserData();
		firstName = userDataMapper.getFirstName();
		lastName = userDataMapper.getLastName();
		email = userDataMapper.getEmailAddress();
		validPassword = userDataMapper.getPassword();
		confirmPassword = validPassword;
		date = userDataMapper.getDate();
		month = userDataMapper.getMonth();
		year = userDataMapper.getYear();
		userName = userDataMapper.getUsername();
		pass = userDataMapper.getPass();

	*/


	}
	 

	@Test
	public void User_01_User_Register() {
		//Để các log này được chạy thì cần file log config để biết ghi log vào file nào,testcase nào trong package nào sẽ được apply log này
		log.info("Register - Step 01: Open 'Register' page"); //log.info() = description note khi in ra log,dùng tên chức năng như tên testcase - Step x - mô tả nội dung step(navigate,action,close,...) + data
		registerPage = homePage.clickToRegisterLink();

		registerPage.clickToRadioButtonByLabel(driver,"Male");

		log.info("Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToTextboxWithID(driver,"FirstName",firstName); //Pattern Object

		log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToTextboxWithID(driver,"LastName",lastName); //Pattern Object

		log.info("Register - Step 03: Enter date of birth with value is '" + date + "'");
		registerPage.selectToDropdownByName(driver,"DateOfBirthDay",date);

		log.info("Register - Step 03: Enter month of birth with value is '" + month + "'");
		registerPage.selectToDropdownByName(driver,"DateOfBirthMonth",month);

		log.info("Register - Step 03: Enter year of birth with value is '" + year + "'");
		registerPage.selectToDropdownByName(driver,"DateOfBirthYear",year);

		log.info("Register - Step 04: Enter to Email address textbox with value is '" + email + "'");
		registerPage.inputToTextboxWithID(driver,"Email",email); //Pattern Object

		registerPage.clickToCheckboxByLabel(driver,"Newsletter");

		log.info("Register - Step 05: Enter to Pasword textbox with value is '" + validPassword + "'");
		registerPage.inputToTextboxWithID(driver,"Password",validPassword); //Pattern Object

		log.info("Register - Step 06: Enter to Confirm Pasword textbox with value is '" + confirmPassword + "'");
		registerPage.inputToTextboxWithID(driver,"ConfirmPassword",confirmPassword); //Pattern Object


		log.info("Register - Step 07: Click to Register button");
		registerPage.clickToButtonByText(driver,"Register"); //Pattern Object

		log.info("Register - Step 08: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}
	@Test
	public void User_02_User_Login() {
		log.info("Register - Step 09: Click to Continue button");
		homePage = registerPage.clickToContinueButton();

		log.info("Login - Step 01: Navigate to Login page");
		loginPage = homePage.clickToLoginLink();

		log.info("Login - Step 02: Enter to Email address textbox with value is '" + email + "'");
		loginPage.inputToTextboxWithID(driver,"Email",email);

		log.info("Login - Step 03: Enter to Pasword textbox with value is '" + validPassword + "'");
		loginPage.inputToTextboxWithID(driver,"Password",validPassword);

		log.info("Login - Step 04: Click to Login button");
		loginPage.clickToButtonByText(driver,"Log in");
		homePage = PageGeneratorManager.getUserHomePageObject(driver);

		log.info("Login - Step 05: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

		log.info("Login - Step 06: Navigte to 'My Account' link");
		customerInfoPage = homePage.clickToMyAccountLink();


		/*Veify bằng pattern object*/
		log.info("Login - Step 06: Verify 'First name' value");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver,"FirstName"),firstName);

		log.info("Login - Step 07: Verify 'Last name' value");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver,"LastName"),lastName);

		log.info("Login - Step 08: Verify 'Email name' value");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver,"Email"),email);

		log.info("Login - Step 09: Verify to 'Customer Info' link");
		Assert.assertFalse(customerInfoPage.isCustomerInfoPageDisplayed()); // set false intentionally to capture screen shot at fail step

		/*Note :
		* Tại sao các hàm sau không thể gộp thành pattern object :
		* -homePage.isMyAccountLinkDisplayed()
		* -customerInfoPage.isCustomerInfoPageDisplayed()
		* Vì các hàm MyAccountLink và CustomerInfoPage chỉ xuất hiện ở mỗi màn hình của nó,không xuất hiện ở các màn hình khác nên vẫn viết theo POM
		*
		* */

	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	
}
