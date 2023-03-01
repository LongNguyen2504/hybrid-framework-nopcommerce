package commons;

import java.io.File;

//Chứa các biến dùng chung,hằng số dùng chung cho tất cả các class trong framework
//Class này đã apply singleton để refactor
//usage : GlobalConstantsFinal.getGlobalConstants().getLongTimeOut()
//Tips: use lombok to create setter getter by annotation @Setter @Getter instead of inserting getter and setter by Inteliji mechanism
public class GlobalConstantsFinal {
	private static GlobalConstantsFinal globalInstance;

	private GlobalConstantsFinal(){

	}

	public static synchronized GlobalConstantsFinal getGlobalConstants(){
		if(globalInstance != null){
			globalInstance = new GlobalConstantsFinal();
		}
		return globalInstance;
	}

	public String getPORTAL_PAGE() {
		return PORTAL_PAGE;
	}

	public String getADMIN_PAGE_URL() {
		return ADMIN_PAGE_URL;
	}

	public String getPROJECT_PATH() {
		return PROJECT_PATH;
	}

	public String getJAVA_VERSION() {
		return JAVA_VERSION;
	}

	public String getOS_NAME() {
		return OS_NAME;
	}

	public String getUPLOAD_FILE() {
		return UPLOAD_FILE;
	}

	public String getDOWLOAD_FILE() {
		return DOWLOAD_FILE;
	}

	public String getBROWSER_LOG() {
		return BROWSER_LOG;
	}

	public String getDRAG_DROP_HTML5() {
		return DRAG_DROP_HTML5;
	}

	public String getAUTO_IT_SCRIPT() {
		return AUTO_IT_SCRIPT;
	}

	public String getREPORTING_SCREENSHOT() {
		return REPORTING_SCREENSHOT;
	}

	public String getDB_DEV_URL() {
		return DB_DEV_URL;
	}

	public String getDB_DEV_USER() {
		return DB_DEV_USER;
	}

	public String getDB_DEV_PASSWORD() {
		return DB_DEV_PASSWORD;
	}

	public String getDB_TEST_URL() {
		return DB_TEST_URL;
	}

	public String getDB_TEST_USER() {
		return DB_TEST_USER;
	}

	public String getDB_TEST_PASSWORD() {
		return DB_TEST_PASSWORD;
	}

	public long getSHORT_TIMEOUT() {
		return SHORT_TIMEOUT;
	}

	public long getLONG_TIMEOUT() {
		return LONG_TIMEOUT;
	}

	public long getRETRY_TEST_FAIL() {
		return RETRY_TEST_FAIL;
	}

	public String getBROWSER_USERNAME() {
		return BROWSER_USERNAME;
	}

	public String getBROWSER_AUTOMATE_KEY() {
		return BROWSER_AUTOMATE_KEY;
	}

	public String getBROWSER_STACK_URL() {
		return BROWSER_STACK_URL;
	}

	public String getSAUCE_USERNAME() {
		return SAUCE_USERNAME;
	}

	public String getSAUCE_AUTOMATE_KEY() {
		return SAUCE_AUTOMATE_KEY;
	}

	public String getSAUCE_URL() {
		return SAUCE_URL;
	}

	public String getCROSS_USERNAME() {
		return CROSS_USERNAME;
	}

	public String getCROSS_ACCESS_KEY() {
		return CROSS_ACCESS_KEY;
	}

	public String getCROSS_URL() {
		return CROSS_URL;
	}

	public String getLAMBDA_USERNAME() {
		return LAMBDA_USERNAME;
	}

	public String getLAMBDA_ACCESS_KEY() {
		return LAMBDA_ACCESS_KEY;
	}

	public String getLAMBDA_URL() {
		return LAMBDA_URL;
	}

	private   final String PORTAL_PAGE = "https://demo.nopcommerce.com/";
	private  final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";

	private  final String PROJECT_PATH = System.getProperty("user.dir");
	private  final String JAVA_VERSION = System.getProperty("java.version");
	private  final String OS_NAME = System.getProperty("os.name");

	//File.separator cho phép thay đổi '/' (cho MAC/LINUX/WINDOW) hoặc '\' (chỉ dành cho Window)
	private  final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	private  final String DOWLOAD_FILE = PROJECT_PATH + File.separator + "dowloadFiles";
	private  final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLog" + File.separator;
	private  final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	private  final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	private  final String REPORTING_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGImages" + File.separator;


	// Database Account/User/Pass/Port
	private  final String DB_DEV_URL = "192.168.1.15:9860"; // tùy đang setup ở local hay public
	private  final String DB_DEV_USER = "automationFC";
	private  final String DB_DEV_PASSWORD = "123!@#Abc";

	private  final String DB_TEST_URL = "32.18.195.23:9860"; // tùy đang setup ở local hay public
	private  final String DB_TEST_USER = "automationFC";
	private  final String DB_TEST_PASSWORD = "123!@#Abc";

	private  final long SHORT_TIMEOUT = 5;
	private  final long LONG_TIMEOUT = 30;
	private  final long RETRY_TEST_FAIL = 3;

	//For cloud testing demo(BrowserStack)
	private  final String BROWSER_USERNAME = "automationfc1"; // coi trong page profile account để lấy
	private  final String BROWSER_AUTOMATE_KEY = "HzcRC4Q1fzuQhRJYSkhz";// coi trong page profile account để lấy
	private  final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	//For cloud testing demo(Saucelab)
	private  final String SAUCE_USERNAME = "automationfc";
	private  final String SAUCE_AUTOMATE_KEY = "f8117ac5-9793-4f8d-89ca-6a3c1d7216a5";
	private  final String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_AUTOMATE_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

	//For cloud testing demo(CrossBrowser)
	private  final String CROSS_USERNAME = "damdm@vntesters.com".replaceAll("@", "%40");
	private  final String CROSS_ACCESS_KEY = "uad436a40401a0c2";
	private  final String CROSS_URL = "http://" + CROSS_USERNAME + ":" + CROSS_ACCESS_KEY + "@hub.crossbrowsertesting.com:80/wd/hub"; // đây là http không như https của saucelab và browserstack

	//For cloud testing demo(Lambda)
	private  final String LAMBDA_USERNAME = "automationfc.com";
	private  final String LAMBDA_ACCESS_KEY = "fillhere";
	private  final String LAMBDA_URL = "https://" + LAMBDA_USERNAME + ":" + LAMBDA_ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

}
