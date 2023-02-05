package pageUIs.wordpress.user;

public class UserHomePageUI {
    public static final String POST_TITLE_TEXT = "xpath=//article//h2/a[text()='%s']";
    public static final String POST_CURRENT_DATE_TEXT_BY_POST_TITLE = "xpath=//article//h2/a[text()='%s']/parent::h2/following-sibling::div//time[text()='%s']";// %s đầu = post title %s sau = currentDate
    public static final String POST_BODY_TEXT = "xpath=//article//h2/a[text()='%s']/ancestor::header/following-sibling::div/p[text()='%s']";// %s đầu = post title %s sau = post body
    public static final String POST_BODY_AUTHOR = "xpath=//article//h2/a[text()='%s']/ancestor::header//a[text()='%s']";// %s đầu = post title %s sau = post author
    public static final String SEARCH_BUTTON = "xpath=//input[@name='s']/ancestor::form/parent::section[@class='widget widget_search']//input[@class='search-submit']";
    public static final String SEARCH_TEXTBOX = "xpath=//section[@class='widget widget_search']//input[@name='s']";




}
