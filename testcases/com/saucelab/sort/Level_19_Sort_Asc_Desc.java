package com.saucelab.sort;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.sauceLab.LoginPagePO;
import pageObjects.sauceLab.PageGeneratorManager;
import pageObjects.sauceLab.ProductPO;


//Apply POM cho test case

public class Level_19_Sort_Asc_Desc extends BaseTest{
	private WebDriver driver;
	private LoginPagePO loginPagePO;
	private ProductPO productPO;

	@Parameters({"browser","sauceLabUrl"})
	@BeforeClass
	public void beforeClass(String browserName,String sauceLabUrl) {
		driver = getBrowserDriver(browserName,sauceLabUrl);


		loginPagePO = PageGeneratorManager.getLoginPagePO(driver);
		loginPagePO.enterToUsernameTextbox("standard_user");
		loginPagePO.enterToPaswordTextbox("secret_sauce");
		productPO = loginPagePO.clickToLoginButton();


	}
	 

	@Test
	public void Sort_01_Name() {
		//Để các log này được chạy thì cần file log config để biết ghi log vào file nào,testcase nào trong package nào sẽ được apply log này
		//log.info("Register - Step 01: Open 'Register' page"); //log.info() = description note khi in ra log,dùng tên chức năng như tên testcase - Step x - mô tả nội dung step(navigate,action,close,...) + data

		//Ascending
		productPO.selectItemInProductSOrtDropdown("Name (A to Z)");
		productPO.sleepInSecond(3);
		Assert.assertTrue(productPO.isProductNameSortedByAscending());


		//Descending
		productPO.selectItemInProductSOrtDropdown("Name (Z to A)");
		productPO.sleepInSecond(3);
		Assert.assertTrue(productPO.isProductNameSortedByDescending());



	}
	@Test
	public void Sort_02_Price() {
		//log.info("Register - Step 09: Click to Continue button");

		//Ascending
		productPO.selectItemInProductSOrtDropdown("Price (low to high)");
		Assert.assertTrue(productPO.isProductPriceSortedByAscending());
		//Descending
		productPO.selectItemInProductSOrtDropdown("Price (high to low)");
		Assert.assertTrue(productPO.isProductPriceSortedByDescending());



	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	
}
