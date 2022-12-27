package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.user.nopCommerce.UserAddressPageObject;
import pageObjects.user.nopCommerce.UserCustomerInfoPageObject;
import pageObjects.user.nopCommerce.UserRewardPointPageObject;
import pageUIs.user.nopcommerce.BasePageUI;
import pageUIs.user.nopcommerce.CustomerInfoPageUI;

//Chứa các hàm dùng chung cho các class ở tầng page objects(gọi là các page của webapp) với dạng automation testing theo mô hình modular
//Class basepage có thể dùng cho các dự án web tương tự hoặc import sang một thư viện để tái sử dụng,update,..




//Common class chứa các common functions
public class BasePage {
	private long longTimeout = 30;
//	private long shortTimeout = 5;
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	//Open Url
	public void openPageUrl(WebDriver driver,String pageURL) {
		driver.get(pageURL);
	}
	
	//Get Title
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	//Get Page Url
	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	//Get Page Source
	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	protected void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	protected void sendkeyToAlert(WebDriver driver,String keysToSend) {
		waitForAlertPresence(driver).sendKeys(keysToSend);
	}
	
	protected void switchIDTabWindow(WebDriver driver,String currentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if(!(id.equals(currentID))) {
				driver.switchTo().window(id);
				break;
				
			}
			
		}
	}
	

	protected void switchIDWindowByTitle(WebDriver driver,String titleToSwitch) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String currentTitle = driver.getTitle();
			if(currentTitle.equals(titleToSwitch)) {
				driver.switchTo().window(id);
				break;
				
			}
			
		}
	}
	

	protected void closeAllWindowsWithoutParentTab(WebDriver driver,String parentTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			if(!(driver.getTitle().equals(parentTitle))) {
				driver.close(); 
			}
		}
		switchIDWindowByTitle(driver,parentTitle);
	}
	
	
//	private By getByXpath(String locatorType) {
//		return By.xpath(locatorType);
//	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		if(locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
			by = By.id(locatorType.substring(3)); // locatorType = "id=username" -> locatorType.substring(3) -> locatorType = "username" (da bo 3 ky tu dau tien)
		}
		else if(locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
			by = By.className(locatorType.substring(6)); // locatorType = "id=username" -> locatorType.substring(3) -> locatorType = "username" (da bo 3 ky tu dau tien)
		}
		else if(locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
			by = By.name(locatorType.substring(5)); // locatorType = "id=username" -> locatorType.substring(3) -> locatorType = "username" (da bo 3 ky tu dau tien)
		}
		else if(locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
			by = By.cssSelector(locatorType.substring(4)); // locatorType = "id=username" -> locatorType.substring(3) -> locatorType = "username" (da bo 3 ky tu dau tien)
		}
		else if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6)); // locatorType = "id=username" -> locatorType.substring(3) -> locatorType = "username" (da bo 3 ky tu dau tien)
		}
		else {
			throw new RuntimeException("Locator type format is not supported!");
		}
		return by;
	}
	
	
	
	private WebElement getWebElement(WebDriver driver,String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	private List<WebElement> getListWebElement(WebDriver driver,String locatorType){
		return driver.findElements(getByLocator(locatorType));
	}
	
	
	protected void clickToElement(WebDriver driver,String locatorType) {
		getWebElement(driver,locatorType).click();
	}
	
	protected void sendkeyToElement(WebDriver driver,String locatorType,String keysToSend) {
		WebElement element = getWebElement(driver,locatorType);
		element.clear();
		element.sendKeys(keysToSend);
	}
	
	protected String getElementText(WebDriver driver,String locatorType) {
		return getWebElement(driver,locatorType).getText();
	}
	
	protected void selectItemDefaultDropdown(WebDriver driver,String locatorType,String textItem) {
		Select select = new Select(getWebElement(driver,locatorType));
		select.selectByVisibleText(textItem);
	}
	
	protected String getSelectedItemDefaultDropdown(WebDriver driver,String locatorType) {
		Select select = new Select(getWebElement(driver,locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	protected boolean isDropdownMultiple(WebDriver driver,String locatorType) {
		Select select = new Select(getWebElement(driver,locatorType));
		return select.isMultiple();
	}
	
	
	
	protected void selectItemInCustomDropList(WebDriver driver,String parentLocatorXpath,String childLocatorXpath,String value) {
		getWebElement(driver, parentLocatorXpath).click();
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocatorXpath)));
		List<WebElement> listItem =  driver.findElements(By.cssSelector(childLocatorXpath));
		for (WebElement webElement : listItem) {
			if(webElement.getText().trim().equals(value)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("argument[0].scrollIntoView(true)", webElement);
				sleepInSecond(1);
				webElement.click();
				break; 
			}
				
		}
	}
	
	protected void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000); // chờ load trang sau khi click -> thư viện của java
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());;
		}
	}
		
	protected String getElementAttribute(WebDriver driver,String locatorType) {
		return getWebElement(driver, locatorType).getAttribute(locatorType);
	}
	
	protected String getElementCSSValue(WebDriver driver,String locatorType,String cssValue) {
		return getWebElement(driver, locatorType).getCssValue(cssValue);
	}

	protected String getHexaColorFromRGBA(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}
	
	protected int getListElementSize(WebDriver driver,String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	
	protected void checkToDefaultCheckboxRadio(WebDriver driver,String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if(!element.isSelected()) {
			element.click();
		}
		
	}
	protected void unCheckToDefaultCheckboxRadio(WebDriver driver,String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if(element.isSelected()) {
			element.click();
		}
		
	}
	
	protected boolean isElementDisplayed(WebDriver driver,String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}
	
	protected boolean isElementEnabled(WebDriver driver,String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	
	protected boolean isElementIsSelected(WebDriver driver,String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	
	protected void switchToFrameIframe(WebDriver driver,String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	protected void hoverMouseToElement(WebDriver driver,String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	//Upload sẽ học trong framework sau

	



	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	protected void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	protected void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}


	protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}


	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	protected void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,locatorType)));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}


	//----Các hàm switch page này cần để modifier public để testcase có thể truy cập qua instance của pageObject(đã extends basepage này)-----------------
	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		// TODO Auto-generated method stub
		return PageGeneratorManager.getUserAddressPage(driver);
	}
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		// TODO Auto-generated method stub
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		// TODO Auto-generated method stub
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	//--------------------------------------------------------------------------------------------------------------

	
	
	
	
	
	
	
}
