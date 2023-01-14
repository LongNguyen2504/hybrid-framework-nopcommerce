package pageUIs.user.nopcommerce;

public class BasePageUI {
	//biến static cho phép dùng chung giữa tất cả các instances của 1 class,chỉ khởi tạo 1 vùng nhớ duy nhất 1 lần ->tiết kiệm,tối ưu
	//biến static có thể chia sẻ dữ liệu giữa nhiều thread khác nhau -> Parallel testing
	//Biến static dc phép truy cập bởi tenclass.tenbien mà k cần tạo instance của class đó (lưu ý trong method static chỉ dc dùng biến static)
	public static final String REWARD_POINT_LINK = "xpath=//div[@class='listbox']//a[text()='Reward points']";
	public static final String CUSTOMER_INFO_LINK = "xpath=//div[@class='listbox']//a[text()='Customer info']";
	public static final String ADDRESS_LINK = "xpath=//div[@class='listbox']//a[text()='Addresses']";
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[@class='listbox']//a[text()='%s']";
	
	
	
	
	public static final String USER_HOME_LOGOUT_LINK = "xpath=//div[@class='header-links']//a[text()='Log out']";
	public static final String ADMIN_HOME_LOGOUT_LINK = "xpath=//div[@id='navbarText']//a[text()='Logout']']";

	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']"; //Demo pattern Object ở Level_18_Pattern_Object tương tự dynamic locator tại line 10
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL = "xpath=//label[contains(text(),'%s')]/following-sibling::input";
}
