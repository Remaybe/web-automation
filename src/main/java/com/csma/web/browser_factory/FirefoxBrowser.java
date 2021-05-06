package com.csma.web.browser_factory;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.VERSION;

@Slf4j
public class FirefoxBrowser extends Browser {

    private FirefoxOptions options = new FirefoxOptions();

    @Override
    public WebDriver getDriver(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
        options.setCapability(BROWSER_NAME, "firefox");
        options.setCapability(VERSION, "86.0");
        Browser.driver = new FirefoxDriver(options);
        preSets();
        return Browser.driver;
    }
}