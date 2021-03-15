import com.google.common.base.Verify;
import org.testng.annotations.Test;

public class ProjectsPageTest extends BaseTest {

    @Test
    public void searchUsingCmbbxs(){
        projectsPage
                .filterByCmbbxValue(Comboboxes.ACCOUNTS, "Raymond James")
                .filterByCmbbxValue(Comboboxes.PROJECTS, "106 BOM - Trade Corrections CO2")
                .filterByCmbbxValue(Comboboxes.AREAS, "Fraud Protection")
                .filterByCmbbxValue(Comboboxes.TECHNOLOGIES, "Azure");
        Verify.verify(projectsPage.getProjectHeader().equals("106 BOM - Trade Corrections CO2"));
    }

    @Test
    public void clrButtonStatusWithoutValues(){
        Verify.verify(projectsPage.chckButtonStatus(), "Button should be non-clickable");
    }

}