package pageObjects.wordpress.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.admin.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
    WebDriver driver;
    public AdminPostSearchPO(WebDriver driver){
        this.driver = driver;
    }

    public AdminPostAddNewPO clickToAddNewButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        return AdminPageGeneratorManager.getAdminPostAddNewPO(driver);

    }

    public void enterToSearchTextbox(String postTitle) {
        waitForElementVisible(driver,AdminPostSearchPageUI.SEARCH_TEXTBOX);
        sendkeyToElement(driver,AdminPostSearchPageUI.SEARCH_TEXTBOX,postTitle);
    }

    public void clickToSearchPostsButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.SEARCH_BUTTON);
    }

    public boolean isPostSearchTableDisplayed(String headerID, String cellValue) {
        int headerIndex = getListElementSize(driver,AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME,headerID) + 1;
        waitForElementVisible(driver,AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX,String.valueOf(headerIndex),cellValue);
        return isElementDisplayed(driver,AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX,String.valueOf(headerIndex),cellValue);
    }

    public AdminPostAddNewPO clickToPostTitleLink(String postTitle) {

        waitForElementClickable(driver, AdminPostSearchPageUI.ROW_TABLE_DETAIL_BY_POST_TITLE,postTitle);
        clickToElement(driver, AdminPostSearchPageUI.ROW_TABLE_DETAIL_BY_POST_TITLE,postTitle);
        return AdminPageGeneratorManager.getAdminPostAddNewPO(driver);
    }

    public void selectPostCheckboxByTitle(String editTitle) {
        waitForElementClickable(driver,AdminPostSearchPageUI.ROW_CHECKBOX_BY_TITLE_NAME,editTitle);
        checkToDefaultCheckboxRadio(driver,AdminPostSearchPageUI.ROW_CHECKBOX_BY_TITLE_NAME,editTitle);

    }

    public void selectTextItemInActionDropdown(String dropDownItemText) {
        waitForElementClickable(driver,AdminPostSearchPageUI.ACTION_DROPDOWN);
        selectItemDefaultDropdown(driver,AdminPostSearchPageUI.ACTION_DROPDOWN,dropDownItemText);
    }

    public void clickToApplyButton() {
        waitForElementClickable(driver,AdminPostSearchPageUI.APPLY_BUTTON);
        clickToElement(driver,AdminPostSearchPageUI.APPLY_BUTTON);
    }

    public boolean isMoveToTrashMessageDisplayed(String moveToTrashMessage) {
        waitForElementVisible(driver,AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE,moveToTrashMessage);
        return isElementDisplayed(driver,AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE,moveToTrashMessage);
    }

    public boolean isNoPostFoundMessageDisplayed(String message) {
        waitForElementVisible(driver,AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE,message);
        return isElementDisplayed(driver,AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE,message);
    }
}
