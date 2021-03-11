import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CaseStudiesPageTest {
    private WebDriver driver;
    private CaseStudiesPage caseStudies;

    @BeforeTest
    public void setUp(){
        driver = ChromeBrowser.createBrowser();
        ChromeBrowser.setProp(driver);
        driver.get("https://csma-staging.griddynamics.net/projects/364");
        AuthPage authPage = new AuthPage(driver);
        authPage.auth("emaznev", "@85411321eGo885441113221");
        caseStudies = new CaseStudiesPage(driver);
    }

    @Test
    public void firstTest(){

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
