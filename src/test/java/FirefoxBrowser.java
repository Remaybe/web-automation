import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public class FirefoxBrowser extends Browser {

    public static WebDriver createBrowser(){
        DesiredCapabilities caps = setCap();
        caps.setBrowserName("firefox");
        caps.setVersion("86.0");
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
        log.info("Creates and set ups browser according to FireFox driver");
        return new FirefoxDriver(caps);
    }
}