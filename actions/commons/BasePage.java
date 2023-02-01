package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.admin.nopCommerce.AdminDashboardPageObject;
import pageObjects.user.nopCommerce.UserAddressPageObject;
import pageObjects.user.nopCommerce.UserCustomerInfoPageObject;
import pageObjects.user.nopCommerce.UserHomePageObject;
import pageObjects.user.nopCommerce.UserRewardPointPageObject;
import pageObjects.wordpress.user.UserHomePO;
import pageObjects.wordpress.user.UserPageGeneratorManager;
import pageUIs.jQuerryUploadFile.BasePageJQuerryUI;
import pageUIs.user.nopcommerce.BasePageUI;

//Chứa các hàm dùng chung cho các class ở tầng page objects(gọi là các page của webapp) với dạng automation testing theo mô hình modular
//Class basepage có thể dùng cho các dự án web tương tự hoặc import sang một thư viện để tái sử dụng,update,..




//Common class chứa các common functions
public class BasePage {
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	
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
	public String getPageUrl(WebDriver driver) {
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
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver,Set<Cookie> cookies){
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);

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
	
	//Nếu truyền vào locator type khác XPath sẽ sai vì hầu hết dynamic string locator đều tìm theo text
	private String getDynamicXpath(String locatorType,String... dynamicValues) {
		if(locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType,(Object[]) dynamicValues);
		}
//		System.out.println(locatorType);
		return locatorType;
	}
	
	private WebElement getWebElement(WebDriver driver,String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public void overrideImplicitTimeout(WebDriver driver, long timeOut){
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public List<WebElement> getListWebElement(WebDriver driver,String locatorType){
		return driver.findElements(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver,String locatorType,String... dynamicValues){
		return driver.findElements(getByLocator(getDynamicXpath(locatorType,dynamicValues)));
	}
	
	
	protected void clickToElement(WebDriver driver,String locatorType) {
		getWebElement(driver,locatorType).click();
	}
	protected void clickToElement(WebDriver driver,String locatorType,String... dynamicValues) {
		getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	protected void sendkeyToElement(WebDriver driver,String locatorType,String keysToSend) {
		WebElement element = getWebElement(driver,locatorType);
		element.clear();
		element.sendKeys(keysToSend);
	}
	protected void sendkeyToElement(WebDriver driver,String locatorType,String keysToSend,String... dynamicValues) {
		WebElement element = getWebElement(driver,getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(keysToSend);
	}
	
	protected String getElementText(WebDriver driver,String locatorType) {
		return getWebElement(driver,locatorType).getText();
	}
	protected String getElementText(WebDriver driver,String locatorType,String... dynamicValues) {
		return getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	
	protected void selectItemDefaultDropdown(WebDriver driver,String locatorType,String textItem) {
		Select select = new Select(getWebElement(driver,locatorType));
		select.selectByVisibleText(textItem);
	}
	protected void selectItemDefaultDropdown(WebDriver driver,String locatorType,String textItem,String... dynamicValues) {
		Select select = new Select(getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)));
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
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000); // chờ load trang sau khi click -> thư viện của java
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());;
		}
	}
		
	protected String getElementAttribute(WebDriver driver,String locatorType,String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	protected String getElementAttribute(WebDriver driver,String locatorType,String attributeName,String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
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
	protected int getListElementSize(WebDriver driver,String locatorType,String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	/*Check to radio/input if not checked*/
	protected void checkToDefaultCheckboxRadio(WebDriver driver,String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if(!element.isSelected()) {
			element.click();
		}
		
	}

	protected void checkToDefaultCheckboxRadio(WebDriver driver,String locatorType,String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
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

	protected void unCheckToDefaultCheckboxRadio(WebDriver driver,String locatorType,String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if(element.isSelected()) {
			element.click();
		}

	}

	/*------------------------------------------------------------------------------------------------------*/
	/*Apply cho element display or undisplayed nhưng vẫn có trong DOM*/
	protected boolean isElementDisplayed(WebDriver driver,String locatorType) {
		waitForElementVisible(driver, locatorType);
		return getWebElement(driver, locatorType).isDisplayed();
	}

	//Không display thì không cần wait trước hoặc có thể bổ sung wait explicit invisible
	protected boolean isElementUndisplayed(WebDriver driver,String locatorType) {
		if(getWebElement(driver,locatorType).isDisplayed()){
			return false;
		}
		return true;
	}

	protected boolean isElementDisplayed(WebDriver driver,String locatorType,String... dynamicValues) {
		waitForElementVisible(driver, getDynamicXpath(locatorType, dynamicValues));
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	protected boolean isElementUndisplayed(WebDriver driver,String locatorType,String... dynamicValues) {
		if(getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).isDisplayed()){
			return false;
		}
		return true;
	}
	/*------------------------------------------------------------------------------------------------------*/


	/*------------------------------------------------------------------------------------------------------*/
	/*Apply cho element display or undisplayed nhưng vẫn có trong DOM và case undisplay + không có trong DOM*/
	/*Lưu ý : chỉ nên truyền các locator có kết quả trả về tương ứng cho 1 element để truyền vào hàm này vì mục đích chỉ check 1 element displayed hay undisplayed*/
	public boolean isElementUndisplayedWithImplicitOverride(WebDriver driver,String locator){
		//Gán timeout mới = 5s để không phải chờ findElement 30s sau mới trả về vì đây có thể là trường hợp ko có trong DOM
		overrideImplicitTimeout(driver,shortTimeout);
		List<WebElement> elements = getListWebElement(driver,locator);
		//Phải trả về timeout implicit như cũ để các step sau có thể sử dụng
		overrideImplicitTimeout(driver,longTimeout);
		if(elements.size() == 0){
			/*Cho case ko display + không có trong DOM*/
			return true;
		}else if(elements.size() > 0 && !elements.get(0).isDisplayed()){
			/*Cho case không display + có trong DOM*/
			return true;
		}else {
			/*Cho case có display + có trong DOM -> case này nên dùng hàm isDisplayed bình thường vì không phải phụ thuộc implicit timout*/
			return false;
		}
	}

	/*Dùng dể check displayed/undisplayed của các elements có locator tương tự nhau như trên cùng 1 menu,..*/
	/*Lưu ý : chỉ nên truyền các locator có kết quả trả về tương ứng cho 1 element để truyền vào hàm này vì mục đích chỉ check 1 element displayed hay undisplayed*/
	public boolean isElementUndisplayedWithImplicitOverride(WebDriver driver,String locator,String... dynamicValues){
		//Gán timeout mới = 5s để không phải chờ findElement 30s sau mới trả về vì đây có thể là trường hợp ko có trong DOM
		overrideImplicitTimeout(driver,shortTimeout);
		List<WebElement> elements = getListWebElement(driver,getDynamicXpath(locator, dynamicValues));

		//Phải trả về timeout implicit như cũ để các step sau có thể sử dụng
		overrideImplicitTimeout(driver,longTimeout);
		if(elements.size() == 0){
			/*Cho case ko display + không có trong DOM*/
			return true;
		}else if(elements.size() > 0 && !elements.get(0).isDisplayed()){
			/*Cho case không display + có trong DOM*/
			return true;
		}else {
			/*Cho case có display + có trong DOM -> case này nên dùng hàm isDisplayed bình thường vì không phải phụ thuộc implicit timout*/
			return false;
		}
	}

	/*------------------------------------------------------------------------------------------------------*/



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

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key){
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver,locatorType),key).perform();
	}
	public void pressKeyToElement(WebDriver driver,String locatorType,Keys key,String... dynamicValues){
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)),key).perform();
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

	//Method này dùng để lấy các value attribute của các element dc code bởi các framework javascript như VueJS,... vì không thể getText() như bình thường
	public String getElementValueByJSWithXpath(WebDriver driver,String xpathLocator){
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath=",""); // bỏ đoạn xpath= ra khỏi locator trước khi pass vào line dưới
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XpathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
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
		return status;
	}
	public boolean isImageLoaded(WebDriver driver,String locatorType,String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)));
		return status;
	}


	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	protected void waitForElementVisible(WebDriver driver, String locatorType,String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	protected void waitForAllElementVisible(WebDriver driver, String locatorType,String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	/*Wait for element undisplayed in DOM or not in DOM and override implicit timeout*/
	protected void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver,shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver,longTimeout);
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType,String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	protected void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,locatorType)));
	}
	protected void waitForAllElementInvisible(WebDriver driver, String locatorType,String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,getDynamicXpath(locatorType, dynamicValues))));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	protected void waitForElementClickable(WebDriver driver, String locatorType,String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void uploadMultipleFiles(WebDriver driver,String... fileNames){
		//Đường dẫn thư mục uploadFile
		String filePath = GlobalConstants.UPLOAD_FILE;

		//Nhiều file : String[] fileNames = {"uploadTest","uploadTest01","uploadTest02"}
		String fullFileName = "";
		for (String file  : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim(); //firefox sẽ ko nhận \n và báo lỗi do đó cần dùng trim để cắt \n ở cuối chuỗi
		getWebElement(driver, BasePageJQuerryUI.UPLOAD_FILE).sendKeys(fullFileName);

	}



	//----Các hàm switch page này cần để modifier public để testcase có thể truy cập qua instance của pageObject(đã extends basepage này)-----------------
	//----Đã tối ưu ở Level_07_Switch_page
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
	
	//----Đã tối ưu ở Level_09_Dynamic_Locator
	//Vì các pageObject đã extends BasePage nên các pageObject cũng dc hiểu như instance của BasePage(tính kế thừa) nên ta có thể return về BasePage và khi gọi hàm để sử dụng ta sẽ ép về kiểu pageObject mong muốn
	public BasePage openPagesAtMyAccountByName(WebDriver driver,String pageName) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver); 
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver); 
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver); 
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver); 
		default:
			throw new RuntimeException("Invalid page name at My Account area.");
		}
	}
	//Khi app có nhiều link tương tư(số lượng quá lớn) thì ta sẽ k thể dùng switch case như trên,thay vào đó sẽ dùng method bên dưới và khởi tạo bên testcase sau
	public void openPagesAtMyAccountByPageName(WebDriver driver,String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
	}
	
	//--------------------------------------------------------------------------------------------------------------

	//---------------------------------Level_08_Switch_Role---------------------------------------------------------
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.USER_HOME_LOGOUT_LINK);
		clickToElement(driver, BasePageUI.USER_HOME_LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePageObject(driver);
	}
	public AdminDashboardPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADMIN_HOME_LOGOUT_LINK);
		clickToElement(driver, BasePageUI.ADMIN_HOME_LOGOUT_LINK);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}



	/*Pattern Objects*/

	/**
	 * Enter to dynamic Textbox by ID
	 * @param driver
	 * @param textboxID
	 * @param keyToSend
	 */
	public void inpuToTextboxByID(WebDriver driver,String textboxID, String keyToSend) {
		waitForAllElementVisible(driver,BasePageUI.DYNAMIC_TEXTBOX_BY_ID,textboxID);
		sendkeyToElement(driver,BasePageUI.DYNAMIC_TEXTBOX_BY_ID,keyToSend,textboxID);
	}

	/**
	 * Click to dynamic Button by Text
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver,String buttonText) {
		waitForElementClickable(driver,BasePageUI.DYNAMIC_BUTTON_BY_TEXT,buttonText);
		clickToElement(driver,BasePageUI.DYNAMIC_BUTTON_BY_TEXT,buttonText);
	}


	/**
	 * Select item in dropdown by Name Attribute
	 * @param driver
	 * @param dropdownAttributeName
	 * @param itemValue
	 */
	public void selectToDropdownByName(WebDriver driver, String dropdownAttributeName,String itemValue) {
		waitForElementClickable(driver,BasePageUI.DYNAMIC_DROPDOWN_BY_NAME,dropdownAttributeName);
		selectItemDefaultDropdown(driver,BasePageUI.DYNAMIC_DROPDOWN_BY_NAME,itemValue,dropdownAttributeName);


	}

	/**
	 * Click to Dynamic Radio Button by label
	 * @param driver
	 * @param radioLabelName
	 */
	public void clickToRadioButtonByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver,BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL,radioLabelName);
		checkToDefaultCheckboxRadio(driver,BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL,radioLabelName);
	}

	/**
	 * Click to dynamic checkbox by label name
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver,BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL,checkboxLabelName);
		checkToDefaultCheckboxRadio(driver,BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL,checkboxLabelName);

	}

	public String getTextboxValueByID(WebDriver driver ,String textboxID) {
		waitForElementVisible(driver,BasePageUI.DYNAMIC_TEXTBOX_BY_ID,textboxID);
		return getElementAttribute(driver,BasePageUI.DYNAMIC_TEXTBOX_BY_ID,"value",textboxID);
	}

	public UserHomePO openEndUserSite(WebDriver driver , String urlUser) {
		openPageUrl(driver,urlUser);
		return UserPageGeneratorManager.getUserHomePO(driver);
	}
}
