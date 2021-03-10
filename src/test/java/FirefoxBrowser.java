import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxBrowser extends Browser {

    public static WebDriver createBrowser(){
        DesiredCapabilities caps = setCap();
        caps.setBrowserName("firefox");
        caps.setVersion("86.0");
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
        return new FirefoxDriver(caps);
    }
}