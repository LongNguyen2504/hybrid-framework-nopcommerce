package pageObjects.wordpress.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.user.UserHomePageUI;
import pageUIs.wordpress.user.UserSearchPostPageUI;

public class UserSearchPostPO extends BasePage {
    WebDriver driver;
    public UserSearchPostPO(WebDriver driver){
        this.driver = driver;
    }


    public boolean isNothingFoundMessageDisplayed(String nothing_found) {
        waitForElementVisible(driver,UserSearchPostPageUI.NOTHING_FOUND_MESSAGE,nothing_found);
        return isElementDisplayed(driver,UserSearchPostPageUI.NOTHING_FOUND_MESSAGE,nothing_found);
    }
}
