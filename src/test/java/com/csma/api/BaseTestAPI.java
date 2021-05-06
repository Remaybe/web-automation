package com.csma.api;
import com.csma.api.steps.StudiesSteps;
import com.csma.web.browser_factory.BrowserFactory;
import com.csma.web.browser_factory.BrowserType;
import com.csma.web.page_objects.ProjectsPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;


public class BaseTestAPI {

    private static final WebDriver DRIVER = new BrowserFactory().createBrowser(BrowserType.CHROME).getDriver();
    protected static StudiesSteps studiesSteps;

    @BeforeClass
    public void beforeAll() {
        DRIVER.get("https://csma-staging.griddynamics.net/projects");
        studiesSteps = new StudiesSteps(ProjectsPage.getCookies(DRIVER));
        DRIVER.quit();
    }
}
