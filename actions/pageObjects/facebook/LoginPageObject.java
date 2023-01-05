package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}


	public void clickToCreateNewAccountBtn() {
		waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public boolean isEmailAddressTxtBoxDisplayed() {
		waitForElementVisible(driver,LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplayed(driver,LoginPageUI.EMAIL_ADDRESS_TEXTBOX);

	}

	//Verify undisplayed thì k có wait
	public boolean isConfirmEmailAddressTxtBoxUndisplayed() {
		return isElementUndisplayed(driver,LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

	public void clickToCloseRegisterFormIcon() {
		waitForElementClickable(driver, LoginPageUI.CLOSE_REGISTER_FORM_ICON);
		clickToElement(driver, LoginPageUI.CLOSE_REGISTER_FORM_ICON);
	}



	public boolean isConfirmEmailTxtBoxUndisplayedWithTimoutOverride() {
		//Tại đây có thể gọi thêm explicitWait for element invisible để testcase ổn định hơn nhưng thời gian tổng cộng để check isConfirmEmailTxtBoxUndisplayedWithTimoutOverride sẽ = time implicit + time Explicit
		return isElementUndisplayedWithImplicitOverride(driver,LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
	}
}
