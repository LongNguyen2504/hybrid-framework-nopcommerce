package factoryBrowser;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions opt = new FirefoxOptions();
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.BROWSER_LOG + "Firefox.log");

        opt.addPreference("browser.dowload.folderList", 2);
        opt.addPreference("browser.dowload.dir",GlobalConstants.DOWLOAD_FILE);
        opt.addPreference("browser.dowload.useDowloadDir", true);
        opt.addPreference("browser.helperApps.neverAsk.saveToDisk",
                "multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html, text/plain,  application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");

        opt.addPreference("pdfjs.disabled", true);
        opt.addArguments("-private");
        return new FirefoxDriver(opt);
    }
}
