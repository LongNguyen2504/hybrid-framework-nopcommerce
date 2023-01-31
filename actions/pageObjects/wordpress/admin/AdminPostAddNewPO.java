package pageObjects.wordpress.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.admin.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage {
    WebDriver driver;
    public AdminPostAddNewPO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToPostTitle(String postTitle) {
        waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX);
        sendkeyToElement(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX,postTitle);
    }

    public void enterToPostBody(String postBody) {
        //Wait for post body button (before sendkey) -> vì có sự thay đổi locator với cơ chế trước và sau khi click -> cần click vào body text BODY_BUTTON trước nếu ko sẽ k tìm dc locator BODY_TEXTBOX
        waitForElementClickable(driver,AdminPostAddNewPageUI.BODY_BUTTON);
        clickToElement(driver,AdminPostAddNewPageUI.BODY_BUTTON);
        //Wait for body textbox to sendkey
        waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        sendkeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX,postBody);
    }

    public void clickToPublishButton() {
        waitForElementClickable(driver,AdminPostAddNewPageUI.PUBLISH_BUTTON);
        clickToElement(driver,AdminPostAddNewPageUI.PUBLISH_BUTTON);
    }
    public void clickToConfirmPublishButton() {
        waitForElementClickable(driver,AdminPostAddNewPageUI.CONFIRM_PUBLISH_BUTTON);
        clickToElement(driver,AdminPostAddNewPageUI.CONFIRM_PUBLISH_BUTTON);
    }
    public boolean isPostPublishedMessageDisplay(String postPublishedMessage) {
        waitForElementVisible(driver,AdminPostAddNewPageUI.PUBLISH_MESSAGE,postPublishedMessage);
        return isElementDisplayed(driver,AdminPostAddNewPageUI.PUBLISH_MESSAGE,postPublishedMessage);
    }

    public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
        openPageUrl(driver,searchPostUrl);
        return AdminPageGeneratorManager.getAdminPostSearchPO(driver);

    }


}
