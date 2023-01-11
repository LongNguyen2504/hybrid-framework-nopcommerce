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
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;;
	public static final String DOWLOAD_FILE = PROJECT_PATH + File.separator + "dowloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLog";
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



}
