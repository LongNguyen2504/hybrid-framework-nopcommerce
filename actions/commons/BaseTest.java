package commons;
//Chứa các hàm dùng chung cho cả tầng testcases

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
import org.testng.annotations.BeforeSuite;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserRegisterPageObject;

public class BaseTest {
	private WebDriver driverBaseTest;
	protected final Log log;
//	private String projectPath = System.getProperty("user.dir");

	@BeforeSuite
	public void initBeforeSuite(){
		deleteAllureReport(); //Xóa các file json trong folder allure-results của suite cũ trước khi chạy suite để tránh lưu lại các file json của suite đã chạy trước đó
	}
	protected BaseTest(){
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		System.out.println("Run on "+ browserName);
		
		if(browserList == BrowserList.FIREFOX) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDriver\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup(); // tự tải driver tương ứng và thay thế step setProperty
			//Lấy log in console và lưu vào .log để xem lại nhằm mục đích review resources của app và tối ưu thời gian load
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,GlobalConstants.PROJECT_PATH + "/browserLog/FirefoxLog.log");
			//Setting browser capability
			FirefoxOptions opt = new FirefoxOptions();
			opt.addArguments("--disable-notifications"); // chặn popup notification
			//Chạy ẩn danh
			opt.addArguments("-private");

			driverBaseTest = new FirefoxDriver(opt);
		}
		else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			//Disable log in console
			System.setProperty("webdriver.chrome.args","--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput","true");

			//Setting browser capability
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--lang=vi");
			opt.addArguments("--disable-notifications"); // chặn popup notification
			opt.addArguments("--disable-geolocation"); //disable geo location
			//Disable khung "Chrome is being controlled by automated test software"
			opt.setExperimentalOption("useAutomationExtension",false);
			opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			//Disable save password prompted
			Map<String,Object> prefs = new HashMap<String,Object>();
			prefs.put("credentials_enable_service",false);
			prefs.put("profile.password_manager_enabled",false);
			opt.setExperimentalOption("prefs",prefs);
			//Auto save file dowload when prompted
/*			prefs.put("profile.default_content_settings.popups",0);
			prefs.put("dowload.default_directory",GlobalConstants.PROJECT_PATH + "/dowloadFiles");*/

			//Chạy ẩn danh
//			opt.addArguments("--incognito");

			driverBaseTest = new ChromeDriver(opt);
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
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true); // by pass ssl certificate if prompted
			//Lấy log in console và lưu vào .log để xem lại nhằm mục đích review resources của app và tối ưu thời gian load
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,GlobalConstants.PROJECT_PATH + "/browserLog/FirefoxLog.log");

			driverBaseTest = new FirefoxDriver(options);
		}else if (browserList == BrowserList.HEAD_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driverBaseTest = new FirefoxDriver(options);
		}
		else if (browserList == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true); // by pass ssl certificate if prompted
			//Disable log in console
			System.setProperty("webdriver.chrome.args","--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput","true");

			driverBaseTest = new ChromeDriver(options);
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

	public WebDriver getDriverInstance(){ // tạo method này để reportNG có thể gọi dc
		return this.driverBaseTest;
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
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
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
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public void deleteAllureReport(){
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-results";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if(listOfFiles[i].isFile()){
					//System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}

			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = GlobalConstants.OS_NAME;
			log.info("OS name = " + osName);

			String driverInstanceName = driverBaseTest.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\""; // trong cmd gõ trực tiếp thì chỉ gõ taskkill /F /FI "IMAGENAME eq " + browserDriverName + "*"
			} else {
				cmd = "pkill " + browserDriverName; // cmd này cho mac/linux
			}

			/*Case này dành cho IE vì sau khi tắt browser thì IE vẫn giữ cookie của session trước vừa chạy cho classtest*/
			if (driverBaseTest != null) {
				driverBaseTest.manage().deleteAllCookies();
				driverBaseTest.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected int randNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return day + "";
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return month + "";
	}

	protected String getCurrentYear() {
		//DateTime now = new DateTime(DateTimeZone.UTC); // dùng DateTimeZone.UTC để get DateTime theo UTC,nếu không dùng thì sẽ tự get date của local máy
		DateTime now = new DateTime();
		return now.getYear() + "";
	}

	protected String getCurrentDate() {
		return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}

}
