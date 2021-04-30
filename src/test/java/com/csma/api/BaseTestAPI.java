package com.csma.api;
import com.csma.web.browser_factory.BrowserFactory;
import com.csma.web.browser_factory.BrowserType;
import com.csma.web.page_objects.ProjectsPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTestAPI {

    protected static String currentCookie;

    @BeforeClass
    public void beforeAll() {
        WebDriver driver = new BrowserFactory().createBrowser(BrowserType.CHROME).getDriver();
        driver.get("https://csma-staging.griddynamics.net/projects");
        currentCookie = ProjectsPage.getCookies(driver);
        driver.quit();
    }
}
