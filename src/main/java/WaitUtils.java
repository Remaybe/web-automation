import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitUtils {

    private WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    private FluentWait getWait() {
        Duration duration = Duration.ofSeconds(20);
        Duration pollingDuration = Duration.ofSeconds(5);
        return new FluentWait<WebDriver>(driver)
                .withTimeout(duration)
                .pollingEvery(pollingDuration)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public void waitForInvisibilityElement(WebElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForVisibilityElement(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }
}
