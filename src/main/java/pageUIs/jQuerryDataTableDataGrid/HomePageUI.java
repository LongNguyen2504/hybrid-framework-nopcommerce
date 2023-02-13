package pageUIs.jQuerryDataTableDataGrid;

public class HomePageUI {
    public static final String PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
    public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%S']";

    public static final String TOTAL_PAGINATION = "css=ul.qgrd-pagination-ul>li";
    public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";
    public static final String ALL_COLUMN_EACH_PAGE = "xpath=//tbody/tr/td[@data-key='%s']";


    public static final String COLUMN_INDEX_BY_NAME = "xpath=//thead/tr/th[text()='%s']/preceding-sibling::th"; // lấy ra index của td chứa title của column và so sánh index của nó so với các preceding-sibling.Tùy html của table mà ta sẽ custom dynamicLocator này
    public static final String TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]/input";
    public static final String DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]//select";
    public static final String BUTTON_LOAD_DATA = "css=button#load";
    public static final String CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tbody/tr[%s]/td[%s]//input[@type='checkbox']";
    public static final String ICON_BY_ROW_INDEX_WITH_TITLE = "xpath=//tbody/tr[%s]//button[@title='%s']";




}
