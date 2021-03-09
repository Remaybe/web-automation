import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum Browsers {
    FIREFOX {
        public WebDriver create() {
            System.setProperty("webdriver.gecko.driver", "/Users/emaznev/Downloads/geckodriver");
            return new FirefoxDriver();
        }
    },
    CHROME {
        public WebDriver create() {
            System.setProperty("webdriver.chrome.driver", "/Users/emaznev/Downloads/chromedriver-2");
            return new ChromeDriver();
        }
    };
    public WebDriver create() { return null; }
}
