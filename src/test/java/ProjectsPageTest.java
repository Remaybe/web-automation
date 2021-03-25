import io.qameta.allure.Feature;
import lombok.SneakyThrows;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Feature("Filtering the projects")
public class ProjectsPageTest extends BaseTest {

    @SneakyThrows
    @Test(description = "Search for project using comboboxes")
    public void searchUsingCmbbxs(){
        String prjctName = "Mobile app";
        projectsPage
                .filterByCmbbxValue(Comboboxes.ACCOUNTS, "Coleman")
                .filterByCmbbxValue(Comboboxes.PROJECTS, prjctName);
        Thread.sleep(1000);
        projectsPage.verifySrchblProject(prjctName);
    }

    @Test(description = "Tests 'clear' button without filter values")
    public void clrButtonStatusWithoutValues(){
        projectsPage.verifyChckButtonStatus(false);
    }

    @Test(description = "Filtering by selecting all checkboxes")
    public void filterWithCheckboxes(){
        projectsPage.selectAllCheckboxes()
                .verifySelectedCheckboxes(true);
    }

    @Test(description = "Equals input value and appeared from list in current combobox")
    public void checkCmbbxValueFromList(){
        String value = "Chose";
        projectsPage.inputCmbbxValue(Comboboxes.AREAS, value)
                .verifyInputAndAppearedValueEquals(value);
    }

}