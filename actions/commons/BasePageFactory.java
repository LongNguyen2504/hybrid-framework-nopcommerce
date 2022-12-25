package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
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

	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForAllElementVisible(WebDriver driver, List<WebElement> listElements) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(listElements));
	}

	protected void waitForElementInvisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	protected void waitForAllElementInvisible(WebDriver driver, List<WebElement> listElements) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(listElements));
	}
	
	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected void clickToElement(WebDriver driver,WebElement element) {
		element.click();
	}
	
	protected void sendkeyToElement(WebDriver driver,WebElement element,String keysToSend) {
		
		element.clear();
		element.sendKeys(keysToSend);
	}

	protected String getElementText(WebDriver driver,WebElement element) {
		return element.getText();
	}
	
	protected boolean isElementDisplayed(WebDriver driver,WebElement element) {
		return element.isDisplayed();
	}




}
