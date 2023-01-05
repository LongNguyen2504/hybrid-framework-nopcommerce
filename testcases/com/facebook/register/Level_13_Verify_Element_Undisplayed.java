package com.facebook.register;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;


//Apply POM cho test case

public class Level_13_Verify_Element_Undisplayed extends BaseTest{
	private WebDriver driver;
	private String email,firstName,lastName,validPassword,confirmPassword;
	private LoginPageObject loginPage;
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName,String url) {
		driver = getBrowserDriver(browserName,url);
		loginPage = PageGeneratorManager.getLoginPageObject(driver);

	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountBtn();
		verifyTrue(loginPage.isEmailAddressTxtBoxDisplayed());

	}@Test
	public void TC_02_Verify_Element_Undisplayed() {
		//Verify true cho hàm trả về undisplayed (mặc định confirm email txtbox là undisplayed và có trong DOM)
		verifyTrue(loginPage.isConfirmEmailAddressTxtBoxUndisplayed());

	}@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		//Close pop-up register
		loginPage.clickToCloseRegisterFormIcon();
		loginPage.sleepInSecond(3);

		//(mô phỏng test case chức năng của web orangeHRM với role admin và role user khi role admin sẽ có tab assign task còn role user không có)
		//Verify confirm email txt box không còn hiển thị và không còn trong DOM
		//Hàm isDisplay() : không kiểm tra dc element ko có trong DOM được(vì có hàm driver.findElement built in nên phải chạy wait để tìm element trong DOM trước khi check .isDisplayed()) -> Phần học wait có giải thích
		//Khi dùng find.Element ko có trong DOM thì : chờ 30s implicit,throw noSuchElement,fail testcase tại step này
		//Giải pháp :
		//cách 1 : Dùng driver.findElements thay thế để trả về list và getSize để check có display(listSize > 0) hay ko display(listSize = 0)
		//Cách 2 : custom hàm isDisplay với  try catch để return false trong catch tránh throw lỗi -> Khuyết điểm : phải chờ hết timeout của implicitWait thì mới xong testcase -> tìm số lượng lớn element thì sẽ tốn time hoặc test trên nhiều browser cũng sẽ tốn time
		//Cách 3 : Gán lại time của implicit wait chỉ cần 5s để tìm element -> Sau khi tìm xong trả về false(=ko hiển thị) và gán lại time implicit = 30s -> viết hàm riêng để không conflict với 2 case verify trên vì 2 case trên cần wait 30s để chờ element có trong DOM thay vì 5s
		//kết hợp cách 1 và 3 để build method checkUndisplay và checkDisplay


		verifyTrue(loginPage.isConfirmEmailTxtBoxUndisplayedWithTimoutOverride()); //do đã override timeout từ 30s về 5s nên cách này chỉ mất 5s để check undisplay của element thay vì dùng cách 2 như trên

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
