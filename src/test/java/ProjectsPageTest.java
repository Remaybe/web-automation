import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProjectsPageTest {

    private WebDriver driver;
    private ProjectsPage projectsPage;

    @BeforeTest
    public void setUp(){
        driver = ChromeBrowser.createBrowser();
        ChromeBrowser.setProp(driver);
        driver.get("https://csma-staging.griddynamics.net/projects");
        AuthPage authPage = new AuthPage(driver);
        authPage.auth("emaznev", "@85411321eGo885441113221");
        projectsPage = new ProjectsPage(driver);
    }

    @Test
    public void searchUsingCmbbxs(){
        projectsPage.filterByCmbbxValue(Comboboxes.ACCOUNTS, "Raymond James")
                .filterByCmbbxValue(Comboboxes.PROJECTS, "106 BOM - Trade Corrections CO2")
                .filterByCmbbxValue(Comboboxes.AREAS, "Fraud Protection")
                .filterByCmbbxValue(Comboboxes.TECHNOLOGIES, "Azure");
        Assert.assertEquals(projectsPage.getProjectHeader(),
                "106 BOM - Trade Corrections CO2");
    }

    @Test
    public void clrButtonStatusWithoutValues(){
        Assert.assertEquals(projectsPage.chckButtonStatus(), false);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
