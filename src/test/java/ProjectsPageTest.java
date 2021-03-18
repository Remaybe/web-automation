import org.testng.annotations.Test;

public class ProjectsPageTest extends BaseTest {

    @Test
    public void searchUsingCmbbxs(){
        projectsPage
                .filterByCmbbxValue(Comboboxes.ACCOUNTS, "Raymond James")
                .filterByCmbbxValue(Comboboxes.PROJECTS, "106 BOM - Trade Corrections CO2")
                .filterByCmbbxValue(Comboboxes.AREAS, "Fraud Protection")
                .filterByCmbbxValue(Comboboxes.TECHNOLOGIES, "Azure");
    }

    @Test
    public void clrButtonStatusWithoutValues(){
        projectsPage.chckButtonStatus();
    }

}