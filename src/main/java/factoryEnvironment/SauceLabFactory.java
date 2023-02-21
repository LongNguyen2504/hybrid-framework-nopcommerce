package factoryEnvironment;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SauceLabFactory {
    private WebDriver driver;
    private String browserName;
    private String osName;

    public SauceLabFactory(String browserName, String osName) {
        this.browserName = browserName;
        this.osName = osName;
    }

    public WebDriver createDriver(){
        DesiredCapabilities capability = null;
        //ChromeOptions browserOptions = new ChromeOptions(); // không cần line này vì đã có tham số browserName map từ .xml qua và map vào capability như dòng dưới
        capability.setCapability("browserName",browserName);
        capability.setCapability("platformName", "Windows 10"); //-> osName ở saucelab gọi là platformName
        capability.setCapability("browserVersion", "latest");
        Map<String, Object> sauceOptions = new HashMap<>(); // dùng để map screen solution vào capability
        if (osName.contains("windows")) {
            sauceOptions.put("screenResolution","1920x1080"); // xem các option configure có sẵn của saucelab để configure cho code mình
        } else {
            sauceOptions.put("screenResolution","1920x1440");
        }
        sauceOptions.put("build", "<your build id>");
        sauceOptions.put("name", "<your test name>");
        capability.setCapability("sauce:options", sauceOptions);
        capability.setCapability("name", "Run on " + osName + " and " + browserName );
        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.SAUCE_URL), capability);


        }catch(MalformedURLException e){e.printStackTrace();}

        return driver;
    }
}
