package pageUIs.sauceLab.sort;

public class ProductPageUI {
	//biến static cho phép dùng chung giữa tất cả các instances của 1 class,chỉ khởi tạo 1 vùng nhớ duy nhất 1 lần ->tiết kiệm,tối ưu
	//biến static có thể chia sẻ dữ liệu giữa nhiều thread khác nhau -> Parallel testing
	//Biến static dc phép truy cập bởi tenclass.tenbien mà k cần tạo instance của class đó (lưu ý trong method static chỉ dc dùng biến static)
	public static final String PRODUCT_CONTAINER_DROPDOWN = "xpath=//select[@class='product_sort_container']";
	public static final String PRODUCT_NAME_TEXT = "xpath=//div[@class='inventory_item_name']";
	public static final String PRODUCT_PRICE_TEXT = "xpath=//div[@class='inventory_item_price']";

}
