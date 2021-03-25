import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
public class ChromeBrowser extends Browser {

    public static WebDriver createBrowser(){
        DesiredCapabilities caps = setCap();
        caps.setBrowserName("chrome");
        caps.setVersion("88.0.4324.96");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver-2");
        log.info("Creates and set ups browser according to Chrome driver");
        return new ChromeDriver(caps);
    }
}