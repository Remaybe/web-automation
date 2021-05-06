package com.csma.web.utils;

import com.csma.web.browser_factory.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriver driver = Browser.getCurrentDriver();

    private static FluentWait getWait() {
        Duration duration = Duration.ofSeconds(20);
        Duration pollingDuration = Duration.ofSeconds(5);
        return new FluentWait<WebDriver>(driver)
                .withTimeout(duration)
                .pollingEvery(pollingDuration)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public static void waitForInvisibilityElement(WebElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForVisibilityElement(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitByExpectedElementText(WebElement element, String text){
        getWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}
