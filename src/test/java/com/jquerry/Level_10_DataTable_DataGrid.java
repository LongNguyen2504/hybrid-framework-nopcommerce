package com.jquerry;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuerryDataTableDataGrid.HomePageObject;
import pageObjects.jQuerryDataTableDataGrid.PageGeneratorManager;

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
	public void Table_01() {
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
	public void Table_02_Enter_To_Table_Cell() {
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
	//@Test
	public void Table_03_Get_Data_Each_Paqge() {

		//Đọc dữ liệu của file country.txt ra -> lưu vào 1 List<String> = expected value = expectedAllCountryValues và so sánh với actualAllCountryValue đã lấy ra bên dưới

		actualAllCountryValue = homePage.getDataValueEachRowAtAllPage();

		//Assert.assertTrue(actualAllCountryValue,expectedAllCountryValues);



		//Chạy qua từng page(for)
		//Lấy dữ liệu cell/row/column lưu vào List(ArrayList)

		
	}

	@Test
	public void Table_03_Enter_To_Txtbox_At_Any_Row() {

		//Value để nhập dữ liệu - tham số 1
		//Row number: tại row nào
		//Ex: nhập vào textbox tại dòng số 3/5/2
		//Column name Album/Artist/Year/Price
		//homePage.enterToTxtBoxByColumnNameAtRowNumber("Company","1","Michael 97");

		//Action tại td ở 1 row cụ thể
		//homePage.selectDropDownByColumnNameAtRowNumber("Country","1","Germany");
		//homePage.sleepInSecond(3);
		//homePage.selectDropDownByColumnNameAtRowNumber("Country","1","Japan");

		//Demo
		homePage.clickToDataLoadButton();
		homePage.enterToTxtBoxByColumnNameAtRowNumber("Company","1","Michael 97");
		homePage.enterToTxtBoxByColumnNameAtRowNumber("Company","2","LCS");
		homePage.enterToTxtBoxByColumnNameAtRowNumber("Company","3","DEMO");
		homePage.selectDropDownByColumnNameAtRowNumber("Country","1","Japan");
		homePage.selectDropDownByColumnNameAtRowNumber("Country","2","Hong Kong");
		homePage.selectDropDownByColumnNameAtRowNumber("Country","3","Taiwan");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?","1");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?","2");
		homePage.checkToCheckboxByColumnNameAtRowNumber("NPO?","3");
		homePage.checkToUnCheckboxByColumnNameAtRowNumber("NPO?","1");
		homePage.checkToUnCheckboxByColumnNameAtRowNumber("NPO?","2");

		homePage.clickToIconAtRowNumberWithTitle("1","Insert Row Above");




	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
