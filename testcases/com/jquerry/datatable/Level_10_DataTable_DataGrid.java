package com.jquerry.datatable;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuerry.HomePageObject;
import pageObjects.jQuerry.PageGeneratorManager;
import pageObjects.user.nopCommerce.*;

import java.util.List;


//Apply POM cho test case

public class Level_10_DataTable_DataGrid extends BaseTest{
	private WebDriver driver;
	private HomePageObject homePage;
	List<String> actualAllCountryValue;

	@Parameters({"browser","urlName"})
	@BeforeClass
	public void beforeClass(String browserName,String urlName) {
		driver = getBrowserDriver(browserName,urlName);
		homePage = PageGeneratorManager.getHomePage(driver);

	}
	 

	//@Test
	public void User_01_() {
		homePage.getPagingByPageNumber("10");
		Assert.assertTrue(homePage.isPageNumberActive("10"));
		homePage.sleepInSecond(2);
		homePage.getPagingByPageNumber("3");
		Assert.assertTrue(homePage.isPageNumberActive("3"));
		homePage.sleepInSecond(2);
		homePage.getPagingByPageNumber("5");
		Assert.assertTrue(homePage.isPageNumberActive("5"));
		homePage.sleepInSecond(2);

	}
	//@Test
	public void User_02_() {
		homePage.refreshPage(driver);
		homePage.enterToHeaderTxtboxByLabel("Females","338282");
		homePage.sleepInSecond(3);
		homePage.enterToHeaderTxtboxByLabel("Country","Argentina");
		homePage.sleepInSecond(3);
		homePage.enterToHeaderTxtboxByLabel("Males","349238");
		homePage.sleepInSecond(3);
		homePage.enterToHeaderTxtboxByLabel("Total","687522");


		homePage.sleepInSecond(3);

		homePage.enterToHeaderTxtboxByLabel("Females","276880");
		homePage.sleepInSecond(3);
		homePage.enterToHeaderTxtboxByLabel("Country","Angola");
		homePage.sleepInSecond(3);
		homePage.enterToHeaderTxtboxByLabel("Males","276472");
		homePage.sleepInSecond(3);
		homePage.enterToHeaderTxtboxByLabel("Total","553353");

	}
	@Test
	public void User_03_Get_Data_Each_Paqge() {

		//Đọc dữ liệu của file country.txt ra -> lưu vào 1 List<String> = expected value = expectedAllCountryValues và so sánh với actualAllCountryValue đã lấy ra bên dưới

		actualAllCountryValue = homePage.getDataValueEachRowAtAllPage();

		//Assert.assertTrue(actualAllCountryValue,expectedAllCountryValues);



		//Chạy qua từng page(for)
		//Lấy dữ liệu cell/row/column lưu vào List(ArrayList)

		
	}

	//@Test
	public void User_03_Dynamic_Page_II() {

		
	}
	
	@Test
	public void User_05_Switch_Role() {
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
