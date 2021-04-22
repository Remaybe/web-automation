package com.csma.browser_factory;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;
import static org.openqa.selenium.remote.CapabilityType.VERSION;

@Slf4j
public class ChromeBrowser extends Browser {

    @Override
    public WebDriver getDriver(){
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver-2");
        options.setCapability(BROWSER_NAME, "chrome");
        options.setCapability(VERSION, "88.0.4324.96");
        Browser.driver = new ChromeDriver(options);
        preSets();
        return Browser.driver;
    }
}