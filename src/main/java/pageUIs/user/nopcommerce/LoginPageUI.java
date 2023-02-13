package pageUIs.user.nopcommerce;

public class LoginPageUI {
	//biến static cho phép dùng chung giữa tất cả các instances của 1 class,chỉ khởi tạo 1 vùng nhớ duy nhất 1 lần ->tiết kiệm,tối ưu
	//biến static có thể chia sẻ dữ liệu giữa nhiều thread khác nhau -> Parallel testing
	//Biến static dc phép truy cập bởi tenclass.tenbien mà k cần tạo instance của class đó (lưu ý trong method static chỉ dc dùng biến static)
	public static final String LOGIN_BUTTON = "xpath=//button[@class='button-1 login-button']";
	public static final String EMAIL_TXTBOX = "xpath=//input[@class='email']";
	public static final String PASSWORD_TXTBOX = "xpath=//input[@class='password']";
	
	
	public static final String EMAIL_ERROR_MESSAGE = "xpath=//span[@id='Email-error']";
	public static final String UNSUCCESS_EMAIL_MESSAGE = "xpath=//div[@class='message-error validation-summary-errors']";

}
