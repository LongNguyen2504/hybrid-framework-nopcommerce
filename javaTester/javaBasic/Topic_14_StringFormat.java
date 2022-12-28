package javaBasic;

public class Topic_14_StringFormat {
	//3 links tuong tu format => 3 locators , 100 links tuong tu => 100 locators ?
	public static String REWARD_POINT_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Reward points']";
	public static String CUSTOMER_INFO_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Customer info']";
	public static String ADDRESS_LINK = "//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
	
	//Nhan xet : cac link locator tren chi khac nhau nhau tai 2 cho,ta tao ra 1 string format tong quat co the truyen tham so cho cac locator tuong tu
	
	public static String DYNAMIC_LINK_BY_PAGE_NAME_1_PARAMETER = "//div[contains@class,'account-navigation')]//a[text()='%s']"; // truong hop 1 tham so %s
	public static String DYNAMIC_LINK_BY_PAGE_NAME_2_PARAMETER = "//div[contains(@class,'%s')]//a[text()='%s']"; // %s tuong ung tham so cua String truyen vao
	
	//
	
	public static void main(String[] args) {
		
		clickToLink(DYNAMIC_LINK_BY_PAGE_NAME_1_PARAMETER,"Reward points");
		
		clickToLink(DYNAMIC_LINK_BY_PAGE_NAME_2_PARAMETER, "account-navigation", "Addresses");
		clickToLink(DYNAMIC_LINK_BY_PAGE_NAME_2_PARAMETER, "account-navigation", "Customer info");
		
		
	 
		
		
	} 
	//1 tham so dong
//	public static void clickToLink(String dynamicLocator, String pageName) {
//		String locator = String.format(dynamicLocator,pageName); // 
//		System.out.println("Click to: " + locator);
//	}
//	//2 tham so dong
//	public static void clickToLink(String dynamicLocator, String areaName, String pageName) {
//		String locator = String.format(dynamicLocator,areaName,pageName); // 2 tham so  areaName tuong ung cho %s dau tien tu trai qua phai,pageName tuong ung %s thu 2 trong string format tong quat DYNAMIC_LINK_BY_PAGE_NAME
//		System.out.println("Click to: " + locator);
//	}
	
	//Khi comment 2 method tren thi cac line 18,20,21 se tu dong run vao method dung n tham so dong vi ta da truyen vao  DYNAMIC_LINK_BY_PAGE_NAME_1_PARAMETER(format 1 tham so %s tuong ung 1 value truyen vao) va DYNAMIC_LINK_BY_PAGE_NAME_2_PARAMETER(format 2 tham so %s tuong ung 2 value truyen vao)
	//rest parameter se map cac value truyen vao tuong ung voi vi tri cua cac %s tu trai sang phai
	
	
	//n tham so dong - khi dùng method này thì nên xóa 2 method bên trên vì method này đã cover n tham số

	public static void clickToLink(String dynamicLocator, String... params) { // ki hieu ... dai dien cho 1 mang cac object tuong tu String hoac bat ky kieu data nao dung truoc VD int... ; char...;
		String locator = String.format(dynamicLocator,(Object[])params); //phai cast params sang Object[]
		System.out.println("Click to: " + locator);
	}
	
	
}
