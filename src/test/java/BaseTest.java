import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

@Feature("Authorization on site")
public class BaseTest {
    protected WebDriver driver;
    protected ProjectsPage projectsPage;

    @BeforeMethod
    @Story("Logins on site with corporate mail")
    public void setUp(){
        driver = ChromeBrowser.createBrowser();
        ChromeBrowser.setProp(driver);
        driver.get("https://csma-staging.griddynamics.net/projects");
        AuthPage authPage = new AuthPage(driver);
        authPage.auth("emaznev", "@85411321eGo885441113221");
        projectsPage = new ProjectsPage(driver);
    }

    @AfterMethod
    @Story("Closes the browser")
    public void tearDown(){
        driver.quit();
    }
}
