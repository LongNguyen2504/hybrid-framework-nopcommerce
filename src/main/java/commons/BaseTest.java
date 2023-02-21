package commons;
//Chứa các hàm dùng chung cho cả tầng testcases
//Selenium ver 3 no longer supporting Edge Chromium -> refactor

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import factoryEnvironment.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

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

	public WebDriver getDriverInstance(){ // tạo method này để reportNG có thể gọi dc
		return this.driverBaseTest;
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
		} else if (browserList == BrowserList.SAFARI) {
			//WebDriverManager.safaridriver().setup();// safari driver đã dc installed sẵn trong MacOS,chỉ cần active trong cmd là có thể dùng nên k cần line này
			driverBaseTest = new SafariDriver();
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


	public WebDriver getBrowserDriver(String browserName,String appURL) {
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
		driverBaseTest.get(appURL);
		return driverBaseTest;
	}

/*	public WebDriver getBrowserDriver(String browserName,String environmentName) { //demo cho Level_21_Multiple_Environment_LibOwner
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
		driverBaseTest.get(getEnvironmentUrl(environmentName));
		return driverBaseTest;
	}*/

	//Của bài selenium grid
	public WebDriver getBrowserDriverGrid(String browserName,String environmentName,String osName,String ipAddress,String portNumber) {
//		đoạn này chưa refactor cho method này
/*		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
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
		}*/

		DesiredCapabilities capability = null;
		Platform platform = null;

		if (osName.contains("windows")) {
			platform = Platform.WINDOWS;
		} else {
			platform = Platform.MAC;
		}

		switch (browserName) {
			case "firefox" :
				capability = DesiredCapabilities.firefox();
				capability.setBrowserName("firefox");
				capability.setPlatform(platform);

				FirefoxOptions fOptions = new FirefoxOptions();
				fOptions.merge(capability);
				break;
			case "chrome" :
				capability = DesiredCapabilities.chrome();
				capability.setBrowserName("chrome");
				capability.setPlatform(platform);

				ChromeOptions cOptions = new ChromeOptions();
				cOptions.merge(capability);
				break;
			case "edge" :
				capability = DesiredCapabilities.edge();
				capability.setBrowserName("edge");
				capability.setPlatform(platform);

				EdgeOptions eOptions = new EdgeOptions();
				eOptions.merge(capability);
				break;
			default :
				throw new RuntimeException("Browser is not valid!");
		}

		try {
			driverBaseTest = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability); // https wont work , truyền ipAddress và portNumber của máy hub
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		//Driver action here
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(getEnvironmentUrl(environmentName));
		driverBaseTest.manage().window().maximize(); // phóng to screen để hạn chế bị fail click element
		return driverBaseTest;
	}

	//For Cloud testing demo(Browserstack,còn thiếu saucelab,crossbrowser và lambda nên đã implement trong package factoryEnvironment)
	public WebDriver getBrowserDriverCloudBrowserStack(String browserName, String environmentName, String osName, String osVersion) {
		System.out.println("Run on "+ browserName);


		DesiredCapabilities capability = null;
		capability.setCapability("os", osName);
		capability.setCapability("os_version", osVersion);
		capability.setCapability("browser", browserName);
		capability.setCapability("browser_version", "latest");
		capability.setCapability("browserstack.debug", "true");
		capability.setCapability("project", "Fill here");
		if (osName.contains("windows")) {
			capability.setCapability("resolution","1920x1080"); // xem các option configure có sẵn của browser stack để configure cho code mình
		} else {
			capability.setCapability("resolution","1920x1080");
		}
		capability.setCapability("name", "Run on " + osName + " and " + browserName );

		try {
			driverBaseTest = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}


		//Driver action here
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(getEnvironmentUrl(environmentName));
		driverBaseTest.manage().window().maximize(); // phóng to screen để hạn chế bị fail click element
		return driverBaseTest;
	}


	//getBrowserDriverFinal demo tại bài [Online 18] - Topic 80 (Framework - Refactor Multiple Environment)
	protected WebDriver getBrowserDriverFinal(String browserName,String serverName,String envName,String ipAddress,String portNumber,String osName,String osVersion){
		switch (envName){ // envName = "local","grid","browserStack","Saucelab","CrossBrowserTesting",...
			case "local":
				driverBaseTest = new LocalFactory(browserName).createDriver();
				break;
			case "grid":
				driverBaseTest = new GridFactory(browserName,ipAddress,portNumber,osName).createDriver();
				break;
			case "browserStack":
				driverBaseTest = new BrowserStackFactory(browserName,osName,osVersion).createDriver();
				break;
			case "sauceLab":
				driverBaseTest = new SauceLabFactory(browserName,osName).createDriver();
				break;
			case "crossBrowser":
				driverBaseTest = new CrossBrowserFactory(browserName,osName).createDriver();
				break;
			case "lambda":
				driverBaseTest = new LambdaFactory(browserName,osName).createDriver();
				break;
			default:
				driverBaseTest = new LocalFactory(browserName).createDriver();
				break;

		}
		driverBaseTest.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driverBaseTest.get(getEnvironmentUrl(serverName)); // serverName = TEST,STAGING,PRODUCT,DEV,...
		return driverBaseTest;
	}

	private String getEnvironmentUrl(String environmentName){ // get STAGING,PRODUCTION,TESTING,DEV,..environment a.k.a serverName
		String envUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(environmentName.toUpperCase());
		if(environment == EnvironmentList.DEV){
			envUrl = "https://demo.nopcommerce.com/";
		}else if (environment == EnvironmentList.TESTING){
			envUrl = "https://admin-demo.nopcommerce.com/";
		}else if (environment == EnvironmentList.STAGING){
			envUrl = "https://staging.orangehrmlive.com/";
		}else if (environment == EnvironmentList.PROD){
			envUrl = "https://production.orangehrmlive.com/";
		}else {
			envUrl = null;
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

			if (osName.contains("Window")) {
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

	//Get random number by date time minute second(No duplicate)
	protected static long getRandomNumberByDateTime(){
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}

	//Random email (for better fake data please use generatedata.com,etc...)
	protected static String getRandomEmail(){
		return "automation" + getRandomNumberByDateTime() + "@live.com";
	}

}
