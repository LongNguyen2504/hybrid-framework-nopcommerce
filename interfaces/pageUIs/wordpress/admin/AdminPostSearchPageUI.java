package pageUIs.wordpress.admin;

public class AdminPostSearchPageUI {
    public static final String ADD_NEW_BUTTON = "xpath=//a[@class='page-title-action']";
    public static final String SEARCH_TEXTBOX = "xpath=//input[@id='post-search-input']";
    public static final String SEARCH_BUTTON = "xpath=//input[@id='search-submit']";
    public static final String TABLE_HEADER_INDEX_BY_HEADER_NAME = "xpath=//table[contains(@class,'table-view-list')]/thead//th[@id='%s']/preceding-sibling::*";
    public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "xpath=//table[contains(@class,'table-view-list')]/tbody/tr/*[%s]//a[text()='%s']"; //$s vì ta sẽ truyền index lấy dc từ TABLE_HEADER_INDEX_BY_HEADER_NAME -> là 1+1=2,%s sau là post title hoặc cell value

    
}
