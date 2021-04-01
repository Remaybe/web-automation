import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;

@Slf4j
public class FirefoxBrowser extends Browser {

    public static WebDriver createBrowser(){
        DesiredCapabilities caps = Browser.setCap();
        caps.setBrowserName("firefox");
        caps.setVersion("86.0");
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
        log.info("Creates and set ups browser according to FireFox driver");
        Browser.driver = new FirefoxDriver(caps);
        return Browser.driver;
    }
    
    WebDriver getCurrentDriver() {
        return Objects.nonNull(Browser.driver) ? Browser.driver : createBrowser();
    }

    
}