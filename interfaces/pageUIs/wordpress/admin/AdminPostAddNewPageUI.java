package pageUIs.wordpress.admin;

public class AdminPostAddNewPageUI {
    public static final String TITLE_TEXTBOX = "xpath=//h1[@aria-label='Add title']";
    public static final String BODY_BUTTON = "css=p.block-editor-default-block-appender__content";
    public static final String BODY_TEXTBOX = "css=p.wp-block-paragraph";
    public static final String PUBLISH_BUTTON = "xpath=//div[@class='edit-post-header__settings']/button[text()='Publish']";
    public static final String CONFIRM_PUBLISH_BUTTON = "xpath=//div[@class='editor-post-publish-panel__header-publish-button']/button";
    public static final String PUBLISH_MESSAGE = "xpath=//div[@class='components-snackbar__content' and text()='%s']";


}
