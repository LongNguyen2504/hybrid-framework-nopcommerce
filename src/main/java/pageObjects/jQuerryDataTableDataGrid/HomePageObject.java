package pageObjects.jQuerryDataTableDataGrid;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuerryDataTableDataGrid.HomePageUI;

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

    public void enterToTxtBoxByColumnNameAtRowNumber(String columnName, String rowIndex, String keyToSend) {
        int columnIndex = getListElementSize(driver,HomePageUI.COLUMN_INDEX_BY_NAME,columnName) +1; //đếm index hiện tại của Column title và gán vào columnIndex có thể gán vào HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX và lấy ra các td theo một cột.(+1 là tổng quát(

        //Send key vào row nào column nào (<tr> index nào và <td> index nào)
        waitForElementVisible(driver,HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
        sendkeyToElement(driver,HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX,keyToSend,rowIndex,String.valueOf(columnIndex));

    }

    public void selectDropDownByColumnNameAtRowNumber(String columnName, String rowIndex, String valueToSelect) {
        int columnIndex = getListElementSize(driver,HomePageUI.COLUMN_INDEX_BY_NAME,columnName) +1;
        waitForElementClickable(driver,HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
        selectItemDefaultDropdown(driver,HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX,valueToSelect,rowIndex,String.valueOf(columnIndex));


    }

    public void clickToDataLoadButton() {
        waitForElementClickable(driver,HomePageUI.BUTTON_LOAD_DATA);
        clickToElement(driver,HomePageUI.BUTTON_LOAD_DATA);
    }

    public void checkToCheckboxByColumnNameAtRowNumber(String columnName, String rowIndex) {

        int columnIndex = getListElementSize(driver,HomePageUI.COLUMN_INDEX_BY_NAME,columnName) +1;
        waitForElementClickable(driver,HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
        checkToDefaultCheckboxRadio(driver,HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));

    }
    public void checkToUnCheckboxByColumnNameAtRowNumber(String columnName, String rowIndex) {

        int columnIndex = getListElementSize(driver,HomePageUI.COLUMN_INDEX_BY_NAME,columnName) +1;
        waitForElementClickable(driver,HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
        checkToDefaultCheckboxRadio(driver,HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));

    }

    public void clickToIconAtRowNumberWithTitle(String rowIndex, String iconTitile) {
        waitForElementClickable(driver,HomePageUI.ICON_BY_ROW_INDEX_WITH_TITLE,rowIndex,iconTitile);
        clickToElement(driver,HomePageUI.ICON_BY_ROW_INDEX_WITH_TITLE,rowIndex,iconTitile);
    }
}
