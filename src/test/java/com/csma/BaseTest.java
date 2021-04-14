package com.csma;

import browser.factory.Browser;
import browser.factory.BrowserFactory;
import browser.factory.BrowserType;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.objects.AuthPage;
import page.objects.ProjectsPage;

@Feature("Authorization on site")
@Slf4j
public class BaseTest {
    protected WebDriver driver;
    protected static ProjectsPage projectsPage;

    @BeforeMethod
    @Story("Logins on site with corporate mail")
    public void setUp(){
        Browser browser = new BrowserFactory().createBrowser(BrowserType.CHROME);
        driver = browser.getDriver();
        driver.get("https://csma-staging.griddynamics.net/projects");
        AuthPage authPage = new AuthPage(driver);
        authPage.auth("emaznev", "@85411321eGo885441113221");
        projectsPage = new ProjectsPage(driver);
        log.info("Sets up chosen browser from factory, opens the site and input value for logging in");
    }

    @AfterMethod
    @Story("Closes the browser")
    public void tearDown(){
        driver.quit();
        log.info("Closes the browser after test method");
    }
}
