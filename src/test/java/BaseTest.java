import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;
    protected ProjectsPage projectsPage;

    @BeforeTest
    public void setUp(){
        driver = ChromeBrowser.createBrowser();
        ChromeBrowser.setProp(driver);
        driver.get("https://csma-staging.griddynamics.net/projects");
        AuthPage authPage = new AuthPage(driver);
        authPage.auth("emaznev", "@85411321eGo885441113221");
        projectsPage = new ProjectsPage(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
