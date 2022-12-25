package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.HomePageUI;
//HomePageObject Factory
public class HomePageObject extends BasePageFactory{
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
		// para this là để hàm này khởi tạo object của HomePageObject,
		//sau khi init dc chạy, nó sẽ liên kết các webElement và các locator tương ứng đã khai báo(xem như biến global) nhưng chưa findElement,
		//khi có method nào trong đây dc gọi bên testcase thì nó mới findElement của method đó gọi tới -> lưu ý : lúc init chưa findElement mà là lúc gọi method
	}
	
	//Page UI define lại
	//Đây là cach viết với POM + Factory,không dc viết UI riêng 1 class
	//Với cách viết này phải define lại các hàm trong basePage với tham số là WebElement thay vì String để truyền locator
	//Do đó,nếu chọn cách viết POM + Factory phải được define từ đầu dự án để implement chính xác và tiết kiệm thời gian
	//Tùy công ty,dự án sẽ dùng cách viết POM thuần hoặc POM + Factory
	@FindBy(how = How.XPATH, using = "//a[@class='ico-register']")
	private WebElement registerLink;

	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement loginLink;
	
	@FindBy(css = "a.ico-account")
	private WebElement myAccountLink;

	// Page Object/Action define lại

	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink); // gọi lên line 29 để tìm element registerLink  lần thứ 1
		clickToElement(driver, registerLink); // gọi lên line 29 để tìm element registerLink lần thứ 2 => pageFactory ko hiệu quả bằng POM thuần
	}
	
	public void clickToLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(driver, loginLink);
	}
	
	public void clickMyAccountLink() {
		waitForElementClickable(driver, myAccountLink);
		clickToElement(driver, myAccountLink);
	}

	public boolean isMyAccountLinkDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, myAccountLink);
		return isElementDisplayed(driver, myAccountLink);
	}
	
	











}
