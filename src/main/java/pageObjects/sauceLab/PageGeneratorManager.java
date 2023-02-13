package pageObjects.sauceLab;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static LoginPagePO getLoginPagePO(WebDriver driver) {
		return new LoginPagePO(driver);
	}
	public static ProductPO getProductPO(WebDriver driver) {
		return new ProductPO(driver);
	}


}
