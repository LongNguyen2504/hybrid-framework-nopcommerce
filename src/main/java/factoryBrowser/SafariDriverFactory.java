package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

public class SafariDriverFactory implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        if(!IS_OS_MAC){
            throw new BrowserNotSupportedException("Safari is not supported on " + System.getProperty("os.name"));
        }
        SafariOptions opt = new SafariOptions();
        opt.setCapability("safari.cleanSession","true");
        return new SafariDriver(opt);
    }
}
