package pageUIs.wordpress.user;

public class UserPostDetailPageUI {
    public static final String POST_TITLE_TEXT = "xpath=//article//h1[text()='%s']"; //%s = post title
    public static final String POST_CURRENT_DATE_TEXT_BY_POST_TITLE = "xpath=//article//h1[text()='%s']/parent::header//time[text()='%s']";// %s đầu = post title %s sau = currentDate
    public static final String POST_BODY_TEXT = "xpath=//article//h1[text()='%s']/parent::header/following-sibling::div/p[text()='%s']";// %s đầu = post title %s sau = post body
    public static final String POST_BODY_AUTHOR = "xpath=//article//h1[text()='%s']/ancestor::header//a[text()='%s']";// %s đầu = post title %s sau = post author
    
}
