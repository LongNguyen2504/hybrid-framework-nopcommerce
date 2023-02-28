package factoryBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Architecture;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

public class IEDriverFactory implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        if(!IS_OS_WINDOWS){
            throw new BrowserNotSupportedException("IE is not supported on " + System.getProperty("os.name"));
        }
        WebDriverManager.iedriver().architecture(Architecture.X32).setup();
        InternetExplorerOptions opt = new InternetExplorerOptions();
        opt.setCapability(CapabilityType.ACCEPT_SSL_CERTS,"true");
        opt.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR,"true");
        opt.setCapability(InternetExplorerDriver.NATIVE_EVENTS,"true");
        opt.setCapability("ignoreProtectedModeSettings","true");
        opt.setCapability("ignoreZoomSetting","true");
        opt.setCapability("requireWindowFocus","true");
        opt.setCapability("enableElementCacheCleanup","true");

        return new InternetExplorerDriver(opt);
    }
}
