package pageUIs.sauceLab.sort;

public class LoginPageUI {
	//biến static cho phép dùng chung giữa tất cả các instances của 1 class,chỉ khởi tạo 1 vùng nhớ duy nhất 1 lần ->tiết kiệm,tối ưu
	//biến static có thể chia sẻ dữ liệu giữa nhiều thread khác nhau -> Parallel testing
	//Biến static dc phép truy cập bởi tenclass.tenbien mà k cần tạo instance của class đó (lưu ý trong method static chỉ dc dùng biến static)
	public static final String USERNAME_TEXTBOX = "xpath=//input[@id='user-name']";
	public static final String PASSWORD_TEXTBOX = "xpath=//input[@id='password']";
	public static final String LOGIN_BUTTON = "xpath=//input[@id='login-button']";

}
