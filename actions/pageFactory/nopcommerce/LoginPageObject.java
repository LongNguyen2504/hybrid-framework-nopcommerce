package pageFactory.nopcommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageObjects.nopcommerce.PageGeneratorManager;

public class LoginPageObject extends BasePageFactory{
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
		// para this là để hàm này khởi tạo object của HomePageObject,
		//sau khi init dc chạy, nó sẽ liên kết các webElement và các locator tương ứng đã khai báo(xem như biến global) nhưng chưa findElement,
		//khi có method nào trong đây dc gọi bên testcase thì nó mới findElement của method đó gọi tới -> lưu ý : lúc init chưa findElement mà là lúc gọi method
	}
	@CacheLookup // khi method nào gọi tới thì chỉ cần findElement 1 lần thay vì 2 lần nếu ở method có implement 2 line code gọi đến element này chỉ phù hợp với element nào cố định trên DOM (với element k cố định trên DOM sẽ ko work)
	@FindBy(className  = "email")
	private WebElement emailTxtBox;
	@FindBy(className  = "password")
	private WebElement passwordTxtBox;
	@FindBy(xpath  = "//button[@class='button-1 login-button']")
	private WebElement loginButton;
	@FindBy(xpath  = "//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	@FindBy(xpath  = "//div[@class='message-error validation-summary-errors']")
	private WebElement unsuccessEmailMessage;


	public void clickToLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		
	}

	public String getErrorMessageAtEmailTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
		
	}

	public void inputToEmailTxtBox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, emailTxtBox);
		sendkeyToElement(driver, emailTxtBox,email);
		
	}
	
	public void inputToPasswordTxtBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, passwordTxtBox);
		sendkeyToElement(driver, passwordTxtBox, password);
	}

	public String getUnsuccessErrorMessageAtEmailTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, unsuccessEmailMessage);
		return getElementText(driver, unsuccessEmailMessage);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
