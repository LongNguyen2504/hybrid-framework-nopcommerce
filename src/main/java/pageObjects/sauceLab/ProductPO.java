package pageObjects.sauceLab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.sauceLab.sort.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPO extends BasePage{
	WebDriver driver;

	public ProductPO(WebDriver driver) {
		this.driver = driver;
	}


	public void selectItemInProductSOrtDropdown(String textItem) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN,textItem);

	}

	public boolean isProductNameSortedByAscending() {
		//Khai báo array list chứa các elements sau khi sort(dùng locator cha - tổng quát)
		List<WebElement> productNameText = getListWebElement(driver,ProductPageUI.PRODUCT_NAME_TEXT);
		//Khai báo array list chứa tên các item trong list element trên
		List<String> productUIList = new ArrayList<String>();
		for (WebElement webElement : productNameText) {
			productUIList.add(webElement.getText());
		}
		//copy list productUIList sang 1 list productSortList(chứa tên các item) tạm để sort list này
		List<String> productSortList = new ArrayList<String>();
		for (String itemName : productUIList) {
			productSortList.add(itemName);
		}

		//sort list productSortList
		Collections.sort(productSortList);

		//So sánh list productSortList(sau khi sort) và list productUIList để check xem ban đầu dev có sort đúng chưa
		return  productSortList.equals(productUIList);


	}
	//Viết theo kiểu lambda
	public boolean isProductNameSortedByAscendingLambda() {
		List<WebElement> productNameText = getListWebElement(driver,ProductPageUI.PRODUCT_NAME_TEXT);
		List<String> names = productNameText.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<String>(names);
		Collections.sort(sortedNames);
		return names.equals(sortedNames);

	}



	public boolean isProductNameSortedByDescending() {
		//Khai báo array list chứa các elements sau khi sort(dùng locator cha - tổng quát)
		List<WebElement> productNameText = getListWebElement(driver,ProductPageUI.PRODUCT_NAME_TEXT);
		//Khai báo array list chứa tên các item trong list element trên
		List<String> productUIList = new ArrayList<String>();
		for (WebElement webElement : productNameText) {
			productUIList.add(webElement.getText());
		}
		//copy list productUIList sang 1 list productSortList(chứa tên các item) tạm để sort list này
		List<String> productSortList = new ArrayList<String>();
		for (String itemName : productUIList) {
			productSortList.add(itemName);
		}

		//sort list productSortList
		Collections.sort(productSortList); //sort ascending trước
		Collections.reverse(productSortList); //reverse lại thành descending

		//So sánh list productSortList(sau khi sort) và list productUIList để check xem ban đầu dev có sort đúng chưa
		return  productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortedByAscending() {
		//Khai báo array list chứa các elements sau khi sort(dùng locator cha - tổng quát)
		List<WebElement> productNameText = getListWebElement(driver,ProductPageUI.PRODUCT_PRICE_TEXT);
		//Khai báo array list chứa price các item trong list element trên
		List<Float> productUIList = new ArrayList<Float>();
		for (WebElement webElement : productNameText) {
			productUIList.add(Float.parseFloat(webElement.getText().replace("$","")));
		}
		//copy list productUIList sang 1 list productSortList(chứa price các item) tạm để sort list này
		List<Float> productSortList = new ArrayList<Float>();
		for (Float itemPrice : productUIList) {
			productSortList.add(itemPrice);
		}

		//sort list productSortList
		Collections.sort(productSortList);

		//So sánh list productSortList(sau khi sort) và list productUIList để check xem ban đầu dev có sort đúng chưa
		return  productSortList.equals(productUIList);

	}

	public boolean isProductPriceSortedByDescending() {
		List<WebElement> productNameText = getListWebElement(driver,ProductPageUI.PRODUCT_PRICE_TEXT);
		List<Float> productUIList = new ArrayList<Float>();
		for (WebElement webElement : productNameText) {
			productUIList.add(Float.parseFloat(webElement.getText().replace("$","")));
		}
		List<Float> productSortList = new ArrayList<Float>();
		for (Float itemPrice : productUIList) {
			productSortList.add(itemPrice);
		}

		Collections.sort(productSortList);
		Collections.reverse(productSortList);

		return  productSortList.equals(productUIList);
	}
}
