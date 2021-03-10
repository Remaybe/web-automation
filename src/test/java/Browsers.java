import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum Browsers {

    FIREFOX {
        public WebDriver create() {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
            return new FirefoxDriver();
        }
    },
    CHROME {
        public WebDriver create() {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver-2");
            return new ChromeDriver();
        }
    };
    public WebDriver create() { return null; }
}
