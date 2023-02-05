package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

public class UserPageGeneratorManager {
	public static UserPostDetailPO getUserPostDetailPO(WebDriver driver) {
		return new UserPostDetailPO(driver);
	}
	public static UserHomePO getUserHomePO(WebDriver driver) {
		return new UserHomePO(driver);
	}
	public static UserSearchPostPO getUserSearchPostPO(WebDriver driver) {
		return new UserSearchPostPO(driver);
	}


}
