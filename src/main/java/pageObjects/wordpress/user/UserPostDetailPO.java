package pageObjects.wordpress.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.user.UserHomePageUI;
import pageUIs.wordpress.user.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage {
    WebDriver driver;
    public UserPostDetailPO(WebDriver driver){
        this.driver = driver;
    }


    public boolean isPostInforDisplayedWithPostTitle(String postTitle) {
        waitForElementVisible(driver, UserPostDetailPageUI.POST_TITLE_TEXT,postTitle);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_TITLE_TEXT,postTitle);

    }

    public boolean isPostInforDisplayedWithPostBody(String postTitle,String postBody) {
        waitForElementVisible(driver, UserPostDetailPageUI.POST_BODY_TEXT,postTitle,postBody);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_BODY_TEXT,postTitle,postBody);
    }

    public boolean isPostInforDisplayedWithPostAuthor(String postTitle,String author) {
        waitForElementVisible(driver, UserPostDetailPageUI.POST_BODY_AUTHOR,postTitle,author);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_BODY_AUTHOR,postTitle,author);
    }

    public boolean isPostInforDisplayedWithPostCurrentDate(String postTitle,String currentDate) {
        waitForElementVisible(driver, UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE,postTitle,currentDate);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE,postTitle,currentDate);
    }
}
