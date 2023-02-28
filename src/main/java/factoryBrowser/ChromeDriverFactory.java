package factoryBrowser;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.HashMap;

public class ChromeDriverFactory implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setExperimentalOption("useAutomationExtension",false);
        opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        opt.addArguments("--disable-notifications");
        opt.addArguments("--disable-geolocation");

        HashMap<String, Object> chromePrefs = new HashMap<String,Object>();
        chromePrefs.put("profile.default_content_settings.popups",0);
        chromePrefs.put("dowload.default_directory", GlobalConstants.DOWLOAD_FILE);
        opt.setExperimentalOption("prefs",chromePrefs);

        //opt.addArguments("--igcognito");
        System.setProperty("webdriver.chrome.args","--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput","true");

        return new ChromeDriver(opt);
    }
}
