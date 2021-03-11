import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ProjectsPageTest {

    private WebDriver driver;
    private ProjectsPage projectsPage;

    @BeforeTest
    public void setUp(){
        driver = ChromeBrowser.createBrowser();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://csma-staging.griddynamics.net/projects/364");
        AuthPage authPage = new AuthPage(driver);
        projectsPage = authPage.auth("emaznev", "@85411321eGo885441113221");
    }

    @Test
    public void firstTest(){

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
