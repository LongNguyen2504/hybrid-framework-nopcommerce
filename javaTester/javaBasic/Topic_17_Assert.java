package javaBasic;

import commons.BaseTest;
import commons.MethodListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(commons.MethodListener.class) //giúp chỉ ra cụ thể trong log  step nào bị fail trong khi run
public class Topic_17_Assert extends BaseTest {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_ValidateCurrentUrl() {
        // Login Page Url matching
        System.out.println("Assert 01: Passed");
        String loginPageUrl = driver.getCurrentUrl();
        verifyEquals(loginPageUrl, "https://www.facebook.com/");

        // Login Page title
        System.out.println("Assert 02:-Leave Failed intentionally");
        String loginPageTitle = driver.getTitle();
        verifyEquals(loginPageTitle, "Facebook – log in or sign up..*&^."); // Hard Assert sẽ fail ở đây và 03,04,05 sẽ không dc chạy còn soft Assert sẽ chạy hết tất cả và báo fail trong log

        // Login form displayed
        System.out.println("Assert 03: Passed");
        verifyTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());

        System.out.println("Assert 04: Leave Failed intentionally");
        verifyTrue(driver.findElement(By.xpath("//input[@name='login_source']")).isDisplayed());//login button bị ẩn trên source -> khiến testcase fail

        // Login form displayed
        System.out.println("Assert 05: Passed");
        verifyTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());
    }

    /*@Test
    public void TC_02_ValidatePageTitle() {

    }

    @Test
    public void TC_03_LoginFormDisplayed() {

    }*/

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}
