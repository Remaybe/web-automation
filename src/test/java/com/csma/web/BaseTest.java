package com.csma.web;

import com.csma.browser_factory.Browser;
import com.csma.browser_factory.BrowserFactory;
import com.csma.browser_factory.BrowserType;
import com.csma.utils.AllureHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.csma.utils.SessionListener;
import com.csma.page_objects.ProjectsPage;

import java.util.ArrayList;
import java.util.List;

@Feature("Authorization on site")
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
        AllureHelper.addStep("Sets up chosen browser from factory, opens the site and input value for logging in");
    }

    @BeforeMethod
    public void update(){
        driver.get("https://csma-staging.griddynamics.net/projects");
        softAssertions = new SoftAssertions();
        if (!driver.getCurrentUrl().contains("sso.griddynamics.net")) projectsPage.waitTillLoad();
        AllureHelper.addStep("Updates current web page on starting page");
    }

    @AfterMethod
    public void newTab(){
        ((JavascriptExecutor)driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(NEW_TAB));
        driver.switchTo().window(tabs.get(PREVIOUS_TAB));
        driver.close();
        driver.switchTo().window(tabs.get(NEW_TAB));
        AllureHelper.addStep("Opens new tab, where will site open");
    }

    @AfterTest
    @Story("Closes the browser")
    public void tearDown(){
        driver.quit();
        AllureHelper.addStep("Closes the browser after test method");
    }
}
