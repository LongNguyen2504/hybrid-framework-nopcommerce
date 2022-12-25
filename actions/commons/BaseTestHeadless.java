package commons;
//Chứa các hàm dùng chung cho cả tầng testcases

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class BaseTestHeadless {
	private WebDriver driverBaseTest;
	private String projectPath = System.getProperty("user.dir");

	public WebDriver getBrowserDriver(String browserName) {
		System.out.println("Run on " + browserName);

		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			driverBaseTest = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("h_firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			FirefoxOptions opt = new FirefoxOptions();
			opt.addArguments("--headless");
			opt.addArguments("window-size=1920x1080");
			driverBaseTest = new FirefoxDriver(opt);
		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
			driverBaseTest = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("h_chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--headless");
			opt.addArguments("window-size=1920x1080");
			driverBaseTest = new ChromeDriver(opt);
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDriver\\msedgedriver.exe");
			driverBaseTest = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver", projectPath + "\\browserDriver\\operadriver.exe");
			driverBaseTest = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("CocCoc")) {
			// Coccoc browser trừ đi 6 version = drivercoccoc version -> CocCoc dùng chromnium nên dùng chung chromedriver
			// Brave browser cũng dùng chromnium -> dùng chromedriver version mới nhất
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDriver\\chromedriver.exe");
			ChromeOptions opt = new ChromeOptions();
			opt.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driverBaseTest = new ChromeDriver(opt);
		} else {
			throw new RuntimeException("Browser name invalid");
		}
		// Driver action here
		driverBaseTest.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driverBaseTest.get("https://demo.nopcommerce.com/");
		return driverBaseTest;

	}

}
