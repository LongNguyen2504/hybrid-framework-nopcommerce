package pageObjects.jQuerry;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuerry.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    public void getPagingByPageNumber(String number) {
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER,number);
        clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER,number);
    }

    public void enterToHeaderTxtboxByLabel(String headerLabel, String keyToSend) {
        waitForElementVisible(driver,HomePageUI.HEADER_TEXTBOX_BY_LABEL,headerLabel);
        sendkeyToElement(driver,HomePageUI.HEADER_TEXTBOX_BY_LABEL,keyToSend,headerLabel);
        pressKeyToElement(driver,HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER,headerLabel);




    }

    public boolean isPageNumberActive(String pageNumber) {
        waitForElementVisible(driver,HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER,pageNumber);
        return isElementDisplayed(driver,HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER,pageNumber);

    }

    public List<String> getDataValueEachRowAtAllPage() {
        int totalPage = getListElementSize(driver,HomePageUI.TOTAL_PAGINATION);
        List<String> listOfRowData = new ArrayList<String>(); //list để lấy data theo row
        List<String> listOfColumnData = new ArrayList<String>();//list để lấy data theo column với title là dynamicValue - Nếu không muốn lưu trùng value thì khai báo Set<String> listOfColumnData = new Hashset<>();

        for (int i = 0;i < totalPage ; i++){
            getPagingByPageNumber(String.valueOf(i+1));

            //lấy data của từng row qua element <tr>.getText()
/*            List<WebElement> listTableData = getListWebElement(driver,HomePageUI.ALL_ROW_EACH_PAGE);
            for (WebElement rowData : listTableData) {
                listOfRowData.add(rowData.getText());
            }*/
            //Lấy data cảu từng column theo title của column qua td[@data-key='%s'] với %s tương ứng title Females,COuntry,Males,Total
            List<WebElement> listTableData = getListWebElement(driver,HomePageUI.ALL_COLUMN_EACH_PAGE,"Country"); // dùng dynamic Locator thì testcase sẽ chạy lâu hơn là ko dùng
            for (WebElement columnData : listTableData) {
                listOfColumnData.add(columnData.getText());
            }

        }

/*        for ( String s :listOfRowData) {
            System.out.println("-----------------------");
            System.out.println(s);
        }*/

        for ( String s :listOfColumnData) {
            System.out.println("-----------------------");
            System.out.println(s);
        }
        //return listOfRowData;
        return listOfColumnData;


    }
}
