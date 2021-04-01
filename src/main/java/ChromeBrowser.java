import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Objects;

@Slf4j
public class ChromeBrowser extends Browser {

    public static WebDriver createBrowser(){
        DesiredCapabilities caps = Browser.setCap();
        caps.setBrowserName("chrome");
        caps.setVersion("88.0.4324.96");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver-2");
        log.info("Creates and set ups browser according to Chrome driver");
        Browser.driver = new ChromeDriver(caps);
        return Browser.driver;
    }
    
    public static WebDriver getCurrentDriver() {
        return Objects.nonNull(Browser.driver) ? Browser.driver : createBrowser();
    }
}