package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.RegisterPageObject;

public class AdminPageGeneratorManager {
	public static AdminLoginPO getAdminLoginPO(WebDriver driver) {
		return new AdminLoginPO(driver);
	}
	public static AdminDashboardPO getAdminDashboardPO(WebDriver driver) {
		return new AdminDashboardPO(driver);
	}
	public static AdminPostAddNewPO getAdminPostAddNewPO(WebDriver driver) {
		return new AdminPostAddNewPO(driver);
	}
	public static AdminPostSearchPO getAdminPostSearchPO(WebDriver driver) {
		return new AdminPostSearchPO(driver);
	}


}
