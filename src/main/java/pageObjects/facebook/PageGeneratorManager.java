package pageObjects.facebook;

import org.openqa.selenium.WebDriver;
import pageObjects.facebook.LoginPageObject;

public class PageGeneratorManager {
	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}

}
