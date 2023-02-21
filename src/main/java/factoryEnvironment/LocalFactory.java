package factoryEnvironment;

import commons.BrowserList;
import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class LocalFactory {
    private WebDriver driver;
    private String browserName;

    public LocalFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver createDriver(){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        System.out.println("Run on "+ browserName);

        if(browserList == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup(); // tự tải driver tương ứng và thay thế step setProperty
            FirefoxOptions options = new FirefoxOptions();
            options.setAcceptInsecureCerts(true); // by pass ssl certificate if prompted
            //Lấy log in console và lưu vào .log để xem lại nhằm mục đích review resources của app và tối ưu thời gian load
 //           System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
 //           System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "/browserLog/FirefoxLog.log");

            driver = new FirefoxDriver(options);
        }else if (browserList == BrowserList.HEAD_FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);
        }
        else if (browserList == BrowserList.CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true); // by pass ssl certificate if prompted
            //Disable log in console
 //           System.setProperty("webdriver.chrome.args","--disable-logging");
 //           System.setProperty("webdriver.chrome.silentOutput","true");

            driver = new ChromeDriver(options);
        }else if (browserList == BrowserList.HEAD_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--headless");
            option.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(option);
        }else if (browserList == BrowserList.EDGE) { //selenium 3.x hết hỗ trợ edge
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }else if (browserList == BrowserList.OPERA) {
            //Selenium 4 không hỗ trợ opera và phantomJS chỉ có selenium 3.x
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }else if (browserList == BrowserList.IE) {
            WebDriverManager.iedriver().arch32().setup();
            driver = new InternetExplorerDriver();
        }else if (browserList == BrowserList.COCCOC) {
            WebDriverManager.chromedriver().driverVersion("x").setup(); // x = version chrome driver trước 6 ver của CocCoc browser
            ChromeOptions opt = new ChromeOptions();
            opt.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
            driver = new ChromeDriver(opt);
        }else {
            throw new RuntimeException("Browser name invalid");
        }
        return driver;
    }
}
