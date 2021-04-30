package com.csma.web.browser_factory;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class Browser {
    
    protected static WebDriver driver;

    public void preSets() {
        if (Objects.nonNull(driver)) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
    }

    public static WebDriver getCurrentDriver() {
        return Browser.driver;
    }

    public abstract WebDriver getDriver();
}
