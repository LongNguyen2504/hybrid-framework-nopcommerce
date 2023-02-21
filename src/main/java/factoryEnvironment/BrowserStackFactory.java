package factoryEnvironment;

import commons.GlobalConstants;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackFactory {
    private WebDriver driver;
    private String browserName;
    private String osVersion;
    private String osName;

    public BrowserStackFactory(String browserName, String osName, String osVersion) {
        this.browserName = browserName;
        this.osVersion = osVersion;
        this.osName = osName;
    }

    public WebDriver createDriver(){
        DesiredCapabilities capability = null;
        capability.setCapability("os", osName);
        capability.setCapability("os_version", osVersion);
        capability.setCapability("browser", browserName);
        capability.setCapability("browser_version", "latest");
        capability.setCapability("browserstack.debug", "true");
        capability.setCapability("project", "Fill here");
        if (osName.contains("windows")) {
            capability.setCapability("resolution","1920x1080"); // xem các option configure có sẵn của browser stack để configure cho code mình
        } else {
            capability.setCapability("resolution","1920x1080");
        }
        capability.setCapability("name", "Run on " + osName + " and " + browserName );

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}
