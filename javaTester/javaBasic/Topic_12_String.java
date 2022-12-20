package javaBasic;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_12_String {
	// focus các hàm builin để dùng cho framework sau này gồm : equalIgnoreCase,startwith,contain,endwith,split,replace,lowercase,uppercase,trim,format
	
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver",".\\browserDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		String temp = "Automation Testing Framework";
//		System.out.println(temp.toCharArray());
//		System.out.println( instanceof char);
		System.out.println(temp.codePointBefore(1));
//		char a[] = {'A','u','t','o','m','a','t','i','o','n',' ','T','e','s','t','i','n','g'};
		int count = 0;
		for (int i = 0; i < temp.length(); i++) {
			if(temp.charAt(i) >= 65 && temp.charAt(i) <= 90) {
				count++;
			}
		}
		System.out.println("in hoa "+count);
		
//		Dynamic locator 
//		Đại diện cho 1 chuỗi : %s
//		%b %t %d -> đại diện cho số
		String dynamicButtonXpath = "//button[@id='%s']";
//		login sẽ map với %d trong String dynamicButtonXpath -> dùng trong framework dynamic locator -> tiết kiệm thời gian
		System.out.println("Click to button = " + dynamicButtonXpath.format(dynamicButtonXpath, "login"));
		
//		Sắp xếp sort Data (Asc/Desc)
		String productPrice = "$100.00";
//		float productPriceF = Float.parseFloat(productPrice); // parse string sang float để sort
		
//		productPrice = String.valueOf(productPriceF); // parse float sang string
		
//		hàm split,substring -> tách chuỗi sang mảng các chuỗi để verify với text của button nào đó -> vd verify số trang hiện tại user đang chọn
//		kết hợp với các hàm contains/startWith,endWith để verify text
		
		
//		toán tử == sẽ so sánh địa chỉ ô nhớ và giá trị của ô nhớ nó trỏ tới -> khác với method equal,.. builtin của String chỉ so sánh giá trị của chuỗi
		
		
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		//Window 10
		//Handle multi OS : MAC/Windows (Actions - keys - ctrl/command -> 2 hệ máy map key khác nhau)
		if(osName.toLowerCase().contains("windows")) { // dùng toLowerCase để xử lý chuỗi osName (có W in hoa ban đầu -> w) trước khi so sánh contain tránh sai sót
			Keys key = Keys.CONTROL;
		}else {
			Keys key = Keys.COMMAND;
		}
		
		
		//Handle multiple browser : dùng toUpperCase -> firefox = FIREFOX (Enum)
		
		
		
		//FirefoxDriver : firefox on WINDOWS ()
		// Close browser/driver => trong driver.toString sẽ chứa tên browser và OS + id browser để dùng cho việc handle driver trên nhiều os,..
		String driverInstanceName = driver.toString(); // string này chứa tên browser để mình xử lý close driver nó khi run testcase (nếu testcase fail thì driver vẫn còn chạy chưa bị đóng trong tiến trình)
		System.out.println(driverInstanceName); // output FirefoxDriver: firefox on WINDOWS (859af85a-b8b8-4dd2-8f08-4ff35203801e)
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
