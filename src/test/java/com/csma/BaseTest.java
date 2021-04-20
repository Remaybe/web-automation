package com.csma;

import browser.factory.Browser;
import browser.factory.BrowserFactory;
import browser.factory.BrowserType;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import other.utils.SessionListener;
import page.objects.CaseStudiesPage;
import page.objects.ProjectsPage;

import java.util.ArrayList;
import java.util.List;

@Feature("Authorization on site")
@Slf4j
public class BaseTest {

    protected WebDriver driver;
    protected static ProjectsPage projectsPage;
    private final int PREVIOUS_TAB = 0;
    private final int NEW_TAB = 1;
    protected SoftAssertions softAssertions;

    @BeforeTest
    @Story("Logins on site with corporate mail")
    public void setUp(){
        Browser browser = new BrowserFactory().createBrowser(BrowserType.CHROME);
        driver = browser.getDriver();
        EventFiringDecorator handler = new EventFiringDecorator(new SessionListener());
        projectsPage = new ProjectsPage(handler.decorate(driver));
        log.info("Sets up chosen browser from factory, opens the site and input value for logging in");
    }

    @BeforeMethod
    public void update(){
        driver.get("https://csma-staging.griddynamics.net/projects");
        softAssertions = new SoftAssertions();
        if (!driver.getCurrentUrl().contains("sso.griddynamics.net")) projectsPage.waitTillLoad();
    }

    @AfterMethod
    public void newTab(){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(NEW_TAB));
        driver.switchTo().window(tabs.get(PREVIOUS_TAB));
        driver.close();
        driver.switchTo().window(tabs.get(NEW_TAB));
    }

    @AfterTest
    @Story("Closes the browser")
    public void tearDown(){
        driver.quit();
        log.info("Closes the browser after test method");
    }
}
