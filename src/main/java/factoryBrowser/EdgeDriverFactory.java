package factoryBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static org.apache.commons.lang3.SystemUtils.*;

public class EdgeDriverFactory implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        if( IS_OS_LINUX){
            throw new BrowserNotSupportedException("Edge is not supported on " + System.getProperty("os.name"));
        }
        WebDriverManager.edgedriver().setup();
        EdgeOptions opt = new EdgeOptions();
        return new EdgeDriver(opt);
    }
}
