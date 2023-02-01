package pageObjects.wordpress.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.user.UserHomePageUI;

public class UserHomePO extends BasePage {
    WebDriver driver;
    public UserHomePO(WebDriver driver){
        this.driver = driver;
    }


    public UserPostDetailPO clickToPostTitle(String postTitle) {
        waitForElementClickable(driver,UserHomePageUI.POST_TITLE_TEXT,postTitle);
        clickToElement(driver,UserHomePageUI.POST_TITLE_TEXT,postTitle);
        return UserPageGeneratorManager.getUserPostDetailPO(driver);
    }

    public boolean isPostInforDisplayedWithPostTitle(String postTitle) {
        waitForElementVisible(driver, UserHomePageUI.POST_TITLE_TEXT,postTitle);
        return isElementDisplayed(driver, UserHomePageUI.POST_TITLE_TEXT,postTitle);
    }

    public boolean isPostInforDisplayedWithPostBody(String postTitle,String postBody) {
        waitForElementVisible(driver, UserHomePageUI.POST_BODY_TEXT,postTitle,postBody);
        return isElementDisplayed(driver, UserHomePageUI.POST_BODY_TEXT,postTitle,postBody);
    }

    public boolean isPostInforDisplayedWithPostAuthor(String postTitle,String author) {
        waitForElementVisible(driver, UserHomePageUI.POST_BODY_AUTHOR,postTitle,author);
        return isElementDisplayed(driver, UserHomePageUI.POST_BODY_AUTHOR,postTitle,author);
    }

    public boolean isPostInforDisplayedWithPostCurrentDate(String postTitle,String currentDate) {
        waitForElementVisible(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE,postTitle,currentDate);
        return isElementDisplayed(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE,postTitle,currentDate);
    }
}
