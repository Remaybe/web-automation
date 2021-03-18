import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Feature("Filtering the projects")
public class ProjectsPageTest extends BaseTest {

    @Test(description = "Search for project using comboboxes")
    public void searchUsingCmbbxs(){
        projectsPage
                .filterByCmbbxValue(Comboboxes.ACCOUNTS, "Raymond James")
                .filterByCmbbxValue(Comboboxes.PROJECTS, "106 BOM - Trade Corrections CO2")
                .filterByCmbbxValue(Comboboxes.AREAS, "Fraud Protection")
                .filterByCmbbxValue(Comboboxes.TECHNOLOGIES, "Azure");
    }

    @Test(description = "Tests 'clear' button without filter values")
    public void clrButtonStatusWithoutValues(){
        projectsPage.chckButtonStatus();
    }

}