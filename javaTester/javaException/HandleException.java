package javaException;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class HandleException {
    public static void main(String[] args) {


        //Check Error = Exception casually happens in compile stage
        //For example:
        //String fullName = 15; compile error not exception
        //checkedErrorDemo()

        //Uncheck Error = Exception casually happens in runtime stage
        WebDriver driver = null; // throwed nullpointerException
        driver.quit();

    }

    static void  checkedErrorDemo() throws FileNotFoundException, InterruptedException {
        File file = new File("F:\\Be Coding\\QA automation");
        FileOutputStream fileOS = new FileOutputStream(file); //would throw checkedError if there is no throws... in line 20
        Thread.sleep(5000);
    }
}
