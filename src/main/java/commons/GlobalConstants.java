package commons;

import java.io.File;

//Chứa các biến dùng chung,hằng số dùng chung cho tất cả các class trong framework
public class GlobalConstants {

	public static final String PORTAL_PAGE = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String OS_NAME = System.getProperty("os.name");

	//File.separator cho phép thay đổi '/' (cho MAC/LINUX/WINDOW) hoặc '\' (chỉ dành cho Window)
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWLOAD_FILE = PROJECT_PATH + File.separator + "dowloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLog" + File.separator;
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTING_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;


	// Database Account/User/Pass/Port
	public static final String DB_DEV_URL = "192.168.1.15:9860"; // tùy đang setup ở local hay public
	public static final String DB_DEV_USER = "automationFC";
	public static final String DB_DEV_PASSWORD = "123!@#Abc";

	public static final String DB_TEST_URL = "32.18.195.23:9860"; // tùy đang setup ở local hay public
	public static final String DB_TEST_USER = "automationFC";
	public static final String DB_TEST_PASSWORD = "123!@#Abc";

	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
	public static final long RETRY_TEST_FAIL = 3;

	//For cloud testing demo(BrowserStack)
	public static final String BROWSER_USERNAME = "automationfc1"; // coi trong page profile account để lấy
	public static final String BROWSER_AUTOMATE_KEY = "HzcRC4Q1fzuQhRJYSkhz";// coi trong page profile account để lấy
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	//For cloud testing demo(Saucelab)
	public static final String SAUCE_USERNAME = "automationfc";
	public static final String SAUCE_AUTOMATE_KEY = "f8117ac5-9793-4f8d-89ca-6a3c1d7216a5";
	public static final String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_AUTOMATE_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

	//For cloud testing demo(CrossBrowser)
	public static final String CROSS_USERNAME = "damdm@vntesters.com".replaceAll("@", "%40");
	public static final String CROSS_ACCESS_KEY = "uad436a40401a0c2";
	public static final String CROSS_URL = "http://" + CROSS_USERNAME + ":" + CROSS_ACCESS_KEY + "@hub.crossbrowsertesting.com:80/wd/hub"; // đây là http không như https của saucelab và browserstack

	//For cloud testing demo(Lambda)
	public static final String LAMBDA_USERNAME = "automationfc.com";
	public static final String LAMBDA_ACCESS_KEY = "fillhere";
	public static final String LAMBDA_URL = "https://" + LAMBDA_USERNAME + ":" + LAMBDA_ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

}
