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

import pageObjects.nopcommerce.AddressPageObject;
import pageObjects.nopcommerce.CustomerInfoPageObject;
import pageObjects.nopcommerce.RewardPointPageObject;
import pageUIs.nopcommerce.BasePageUI;
import pageUIs.nopcommerce.CustomerInfoPageUI;

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
	protected void openPageUrl(WebDriver driver,String pageURL) {
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
	
	
	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	
	
	private WebElement getWebElement(WebDriver driver,String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	private List<WebElement> getListWebElement(WebDriver driver,String xpathLocator){
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	
	protected void clickToElement(WebDriver driver,String xpathLocator) {
		getWebElement(driver,xpathLocator).click();
	}
	
	protected void sendkeyToElement(WebDriver driver,String xpathLocator,String keysToSend) {
		WebElement element = getWebElement(driver,xpathLocator);
		element.clear();
		element.sendKeys(keysToSend);
	}
	
	protected String getElementText(WebDriver driver,String xpathLocator) {
		return getWebElement(driver,xpathLocator).getText();
	}
	
	protected void selectItemDefaultDropdown(WebDriver driver,String xpathLocator,String textItem) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		select.selectByVisibleText(textItem);
	}
	
	protected String getSelectedItemDefaultDropdown(WebDriver driver,String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	protected boolean isDropdownMultiple(WebDriver driver,String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.isMultiple();
	}
	
	
	
	protected void selectItemInCustomDropList(WebDriver driver,String parentLocatorXpath,String childLocatorXpath,String value) {
		getWebElement(driver, parentLocatorXpath).click();
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocatorXpath)));
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
		
	protected String getElementAttribute(WebDriver driver,String xpathLocator) {
		return getWebElement(driver, xpathLocator).getAttribute(xpathLocator);
	}
	
	protected String getElementCSSValue(WebDriver driver,String xpathLocator,String cssValue) {
		return getWebElement(driver, xpathLocator).getCssValue(cssValue);
	}

	protected String getHexaColorFromRGBA(String rgbaColor) {
		return Color.fromString(rgbaColor).asHex();
	}
	
	protected int getListElementSize(WebDriver driver,String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}
	
	protected void checkToDefaultCheckboxRadio(WebDriver driver,String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if(!element.isSelected()) {
			element.click();
		}
		
	}
	protected void unCheckToDefaultCheckboxRadio(WebDriver driver,String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if(element.isSelected()) {
			element.click();
		}
		
	}
	
	protected boolean isElementDisplayed(WebDriver driver,String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}
	
	protected boolean isElementEnabled(WebDriver driver,String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	
	protected boolean isElementIsSelected(WebDriver driver,String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}
	
	protected void switchToFrameIframe(WebDriver driver,String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	protected void hoverMouseToElement(WebDriver driver,String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}
	
	//Upload sẽ học trong framework sau

	



	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	protected void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}


	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
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

	protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}


	protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	protected void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	protected void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,xpathLocator)));
	}
	
	protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}


	//----Các hàm switch page này cần để modifier public để testcase có thể truy cập qua instance của pageObject(đã extends basepage này)-----------------
	public AddressPageObject openAddressPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		// TODO Auto-generated method stub
		return new AddressPageObject(driver);
	}
	public RewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		// TODO Auto-generated method stub
		return new RewardPointPageObject(driver);
	}
	public CustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementVisible(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		// TODO Auto-generated method stub
		return new CustomerInfoPageObject(driver);
	}
	//--------------------------------------------------------------------------------------------------------------

}
