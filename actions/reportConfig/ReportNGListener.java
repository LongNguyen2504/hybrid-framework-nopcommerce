package reportConfig;

import commons.BaseTest;
import commons.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportNGListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    //Class ITestResult dành cho các method/testcase
    public void onTestFailure(ITestResult result) { // chỉ cần override lại method onTestFailure này để attach ảnh error
        System.setProperty("org.uncommons.reportng.escape-output", "false"); // mặc định reportNG set escape-output = true để tránh SQL Injection khi có ký tự > hoặc & (ta dùng > ở line 40) -> phải set sang false

        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriverInstance(); //get driver từ basetest để có thể dùng ở captureScreenshot()

        String screenshotPath = captureScreenshot(webDriver, result.getName()); //result.getName() -> lấy ra testcase name để truyền vào para screenshotName của captureScreenshot() và + "_" + ngày tháng giờ phút fail test case + ".png"
        Reporter.getCurrentTestResult();
        /*Chọn 1 trong 2 sau đây để chọn source ảnh là local/online hay base64 */
        //Img local/online
/*
        Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///" + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
*/
        //Img base64
        Reporter.log("<br><a target=\"_blank\" href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
        Reporter.setCurrentTestResult(null);

    }

    public String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); //OutputType.FILE là dùng ảnh có path local hoặc online,ngoài ra có OutputType.BASE64 sẽ dùng ảnh base64 để attach
            String screenPath = GlobalConstants.REPORTING_SCREENSHOT + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
            FileUtils.copyFile(source, new File(screenPath)); //copy file
            return screenPath; // trả về file path của ảnh
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String captureScreenshotBase64(WebDriver driver, String screenshotName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }




    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
