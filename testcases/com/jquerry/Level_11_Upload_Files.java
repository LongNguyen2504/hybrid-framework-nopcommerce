package com.jquerry;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuerryUploadFile.HomePageObject;
import pageObjects.jQuerryUploadFile.PageGeneratorManager;


//Apply POM cho test case

public class Level_11_Upload_Files extends BaseTest{
	private WebDriver driver;
	private HomePageObject homePageUploadFile;
	String TestFileName01 = "uploadTest.png";
	String TestFileName02 = "uploadTest01.png";
	String TestFileName03 = "uploadTest02.png";
	String[] multiFileNames = {"uploadTest.png","uploadTest01.png","uploadTest02.png"}; // tên file phải có đuôi hoặc chỉ có tên file tùy vào property setting của máy


	@Parameters({"browser","urlName"})
	@BeforeClass
	public void beforeClass(String browserName,String urlName) {
		driver = getBrowserDriver(browserName,urlName);
		homePageUploadFile = PageGeneratorManager.getHomePage(driver);

	}
	 

	//@Test
	public void Upload_01_Single_File() {
		//Step 01 : Load single file
		homePageUploadFile.uploadMultipleFiles(driver, TestFileName01);
		//Step 02 : Verify single file loaded success
		Assert.assertTrue(homePageUploadFile.isFileNameLoadedByName(TestFileName01));
		//Step 03 : Click to upload button
		homePageUploadFile.clickToStartButton();
		//Step 04 : Verify single file link uploaded success
		Assert.assertTrue(homePageUploadFile.isFileLinkUploadedByName(TestFileName01));
		//Step 05 : Verify single file image uploaded success
		Assert.assertTrue(homePageUploadFile.isFileImageUploadedByName(TestFileName01));

	}



	@Test
	public void Upload_02_Multi_File() {
		//Step 01 : Load multi file
		homePageUploadFile.uploadMultipleFiles(driver,multiFileNames);
		//Step 02 : Verify multi file loaded success
		Assert.assertTrue(homePageUploadFile.isFileNameLoadedByName(TestFileName01));
		Assert.assertTrue(homePageUploadFile.isFileNameLoadedByName(TestFileName02));
		Assert.assertTrue(homePageUploadFile.isFileNameLoadedByName(TestFileName03));
		//Step 03 : Click to upload button
		homePageUploadFile.clickToStartButton();
		//Step 04 : Verify multi file link uploaded success
		Assert.assertTrue(homePageUploadFile.isFileLinkUploadedByName(TestFileName01));
		Assert.assertTrue(homePageUploadFile.isFileLinkUploadedByName(TestFileName02));
		Assert.assertTrue(homePageUploadFile.isFileLinkUploadedByName(TestFileName03));
		//Step 05 : Verify multi file image uploaded success
		Assert.assertTrue(homePageUploadFile.isFileImageUploadedByName(TestFileName01));
		Assert.assertTrue(homePageUploadFile.isFileImageUploadedByName(TestFileName02));
		Assert.assertTrue(homePageUploadFile.isFileImageUploadedByName(TestFileName03));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
