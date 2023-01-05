package commons;
//Chứa các hàm dùng chung cho cả tầng testcases

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Reporter;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserRegisterPageObject;

public class BaseTest {
	private WebDriver driverBaseTest;
//	private String projectPath = System.getProperty("user.dir");

	public WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		System.out.println("Run on "+ browserName);
		
		if(browserList == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup(); // tự tải driver tương ứng và thay thế step setProperty
			driverBaseTest = new FirefoxDriver();
		}
		else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();
		}else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();
		}else if (browserList == BrowserList.OPERA) {
			//Selenium 4 không hỗ trợ opera và phantomJS chỉ có selenium 3.x
			WebDriverManager.operadriver().setup();
			driverBaseTest = new OperaDriver();
		}else if (browserList == BrowserList.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driverBaseTest = new InternetExplorerDriver();
		}else if (browserList == BrowserList.COCCOC) {
			WebDriverManager.chromedriver().driverVersion("x").setup(); // x = version chrome driver trước 6 ver của CocCoc browser
			ChromeOptions opt = new ChromeOptions();
			if(GlobalConstants.OS_NAME.startsWith("Windows")){
				opt.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			}else{
				opt.setBinary("C://...");
			}

			driverBaseTest = new ChromeDriver(opt);
		}else {
			throw new RuntimeException("Browser name invalid");
		}
		//Driver action here
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(GlobalConstants.PORTAL_PAGE);
		return driverBaseTest;
	}


/*	public WebDriver getBrowserDriver(String browserName,String environmentName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		System.out.println("Run on "+ browserName);

		if(browserList == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup(); // tự tải driver tương ứng và thay thế step setProperty
			driverBaseTest = new FirefoxDriver();
		}
		else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();
		}else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();
		}else if (browserList == BrowserList.OPERA) {
			//Selenium 4 không hỗ trợ opera và phantomJS chỉ có selenium 3.x
			WebDriverManager.operadriver().setup();
			driverBaseTest = new OperaDriver();
		}else if (browserList == BrowserList.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driverBaseTest = new InternetExplorerDriver();
		}else if (browserList == BrowserList.COCCOC) {
			WebDriverManager.chromedriver().driverVersion("x").setup(); // x = version chrome driver trước 6 ver của CocCoc browser
			ChromeOptions opt = new ChromeOptions();
			opt.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driverBaseTest = new ChromeDriver(opt);
		}else {
			throw new RuntimeException("Browser name invalid");
		}
		//Driver action here
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(getEnvironmentUrl(environmentName));
		return driverBaseTest;
	}*/

	public WebDriver getBrowserDriver(String browserName,String urlName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		System.out.println("Run on "+ browserName);

		if(browserList == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup(); // tự tải driver tương ứng và thay thế step setProperty
			driverBaseTest = new FirefoxDriver();
		}else if (browserList == BrowserList.HEAD_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driverBaseTest = new FirefoxDriver(options);
		}
		else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			driverBaseTest = new ChromeDriver();
		}else if (browserList == BrowserList.HEAD_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--headless");
			option.addArguments("window-size=1920x1080");
			driverBaseTest = new ChromeDriver(option);
		}else if (browserList == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driverBaseTest = new EdgeDriver();
		}else if (browserList == BrowserList.OPERA) {
			//Selenium 4 không hỗ trợ opera và phantomJS chỉ có selenium 3.x
			WebDriverManager.operadriver().setup();
			driverBaseTest = new OperaDriver();
		}else if (browserList == BrowserList.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driverBaseTest = new InternetExplorerDriver();
		}else if (browserList == BrowserList.COCCOC) {
			WebDriverManager.chromedriver().driverVersion("x").setup(); // x = version chrome driver trước 6 ver của CocCoc browser
			ChromeOptions opt = new ChromeOptions();
			opt.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driverBaseTest = new ChromeDriver(opt);
		}else {
			throw new RuntimeException("Browser name invalid");
		}
		//Driver action here
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(urlName);
		return driverBaseTest;
	}


	private String getEnvironmentUrl(String environmentName){
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(environmentName.toUpperCase());
		if(environment == EnvironmentList.DEV){
			envUrl = "https://demo.nopcommerce.com/";
		}else if (environment == EnvironmentList.TESTING){
			envUrl = "https://admin-demo.nopcommerce.com/";
		}else if (environment == EnvironmentList.STAGING){
			envUrl = "https://staging.orangehrmlive.com/";
		}else if (environment == EnvironmentList.PRODUCTION){
			envUrl = "https://production.orangehrmlive.com/";
		}
		return envUrl;
	}


	/*Custom Soft Assert/Verify methods*/
	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}


	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}


	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
			//log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			//log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}




	protected int randNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
