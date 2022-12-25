package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory{
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
		// para this là để hàm này khởi tạo object của HomePageObject,
		//sau khi init dc chạy, nó sẽ liên kết các webElement và các locator tương ứng đã khai báo(xem như biến global) nhưng chưa findElement,
		//khi có method nào trong đây dc gọi bên testcase thì nó mới findElement của method đó gọi tới -> lưu ý : lúc init chưa findElement mà là lúc gọi method
	}
	
	@FindBy(xpath  = "//input[@id='FirstName']")
	private WebElement firstNameTxtBox;
	
	@FindBy(xpath  = "//input[@id='LastName']")
	private WebElement lastNameTxtBox;
	
	@FindBy(xpath  = "//input[@id='Email']")
	private WebElement emailTxtBox;
	
	@FindBy(xpath  = "//input[@id='Password']")
	private WebElement passwordTxtBox;
	
	@FindBy(xpath  = "//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTxtBox;
	
	@FindBy(xpath  = "//button[@id='register-button']")
	private WebElement registerButton;
	
	@FindBy(xpath  = "//span[@id='FirstName-error']")
	private WebElement firstNameErrorMessage;
	
	@FindBy(xpath  = "//span[@id='LastName-error']")
	private WebElement lastNameErrorMessage;
	
	@FindBy(xpath  = "//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath  = "//span[@id='Password-error']")
	private WebElement passwordErrorMessage;
	
	@FindBy(xpath  = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMessage;
	
	@FindBy(xpath  = "//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath  = "//div[@class='message-error validation-summary-errors']")
	private WebElement existingEmailErrorMessage;
	
	@FindBy(xpath  = "//a[@class='ico-login']")
	private WebElement loginLink;
	
	@FindBy(xpath  = "//div[@class='buttons']/a")
	private WebElement continueButton;
	
	
	public void clickToRegisterButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);

		
	}

	public String getErrorMessageAtFirstnameTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageAtLastnameTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);

	}

	public String getErrorMessageAtEmailTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);

	}

	public String getErrorMessageAtPaswordTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);

	}

	public String getErrorMessageAtConfirmPasswordTxtBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);

	}
	
	public String getErrorExistingEmailMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);

	}

	public void inputToFirstnameTxtBox(String firstName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, firstNameTxtBox);
		sendkeyToElement(driver, firstNameTxtBox, firstName);
		
	}

	public void inputToLastnameTxtBox(String lastName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, lastNameTxtBox);
		sendkeyToElement(driver, lastNameTxtBox,lastName);

		
	}

	public void inputToEmailTxtBox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, emailTxtBox);
		sendkeyToElement(driver, emailTxtBox, email);

		
	}

	public void inputToPasswordTxtBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, passwordTxtBox);
		sendkeyToElement(driver, passwordTxtBox, password);

		
	}

	public void inputToConfirmPasswordTxtBox(String confirmPassword) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, confirmPasswordTxtBox);
		sendkeyToElement(driver, confirmPasswordTxtBox, confirmPassword);

		
	}

	public String getRegisterSuccessMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}


	public void clickToContinueButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, continueButton);
		clickToElement(driver, continueButton);
		
	}


	

}
