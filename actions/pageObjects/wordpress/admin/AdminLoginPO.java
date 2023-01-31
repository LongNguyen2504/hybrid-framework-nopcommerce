package pageObjects.wordpress.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {

    WebDriver driver;
    public AdminLoginPO(WebDriver driver){
        this.driver = driver;
    }


    public void enterToUserNameTextbox(String adminUserName) {
        waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
        sendkeyToElement(driver,AdminLoginPageUI.USERNAME_TEXTBOX,adminUserName);
    }

    public void enterToPasswordTextbox(String adminPassword) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,AdminLoginPageUI.PASSWORD_TEXTBOX,adminPassword);
    }

    public AdminDashboardPO clickToLogInButton() {
        waitForElementClickable(driver,AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,AdminLoginPageUI.LOGIN_BUTTON);
        return AdminPageGeneratorManager.getAdminDashboardPO(driver);
    }
}
