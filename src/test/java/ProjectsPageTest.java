import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import other.utils.Comboboxes;

import java.util.Arrays;
import java.util.List;

@Feature("Filtering the projects")
public class ProjectsPageTest extends BaseTest {

    private static final int LAST_PROJECTS_PAGE = 84;
    private static final int THIRD_PROJECTS_PAGE = 3;

    @DataProvider(name = "ProjectFilters")
    public static Object[][] filtersProjectData(){
        return new Object[][] {
                {Arrays.asList("PMO SOW8")},
                {Arrays.asList("PMO SOW8", "Cisco DevTest", "Administration")}
        };
    }

    @Test(dataProvider = "ProjectFilters",
            description = "Discards input values in combobox by itself")
    public void discardFiltersInsideCombobox(List<String> value){
        value.stream().forEach(v -> projectsPage.filterByCmbbxValue(Comboboxes.PROJECT, v));
        projectsPage.clearCmbbxValues()
                .verifiesDiscardingFields(Comboboxes.PROJECT);
    }

    @Test(description = "Search for project using comboboxes")
    public void searchUsingCmbbxs(){
        String prjctName = "Mobile app";
        projectsPage
                .filterByCmbbxValue(Comboboxes.ACCOUNT, "Coleman")
                .filterByCmbbxValue(Comboboxes.PROJECT, prjctName)
                .verifySrchblProject(prjctName);
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
        String value = "React";
        projectsPage.inputCmbbxValue(Comboboxes.TECHNOLOGY, value)
                .verifyInputAndAppearedValueEquals(value);
    }

    @Test(description = "Verifies if filters of headers were cleared")
    public void discardFiltersByColumnHeaders(){
        projectsPage.filterByStatusHeader()
                .clearFilters()
                .verifyHeadingsClearFilters();
    }

    @Test(description = "Discard filter by clearing combobox input values with X-marker")
    public void discardCheckboxFilter(){
        projectsPage.selectActiveProjectsCheckbox()
                .clearFilters()
                .verifySelectedCheckboxes(false);
    }

    @Test(description = "Discard filter by deselecting chosen item in combobox")
    public void discardFilterByDeselecting(){
        projectsPage.filterByCmbbxValue(Comboboxes.PROJECT, "Administration")
                .discardCmbbxValueByXmark("Administration")
                .verifiesDiscardingFields(Comboboxes.PROJECT);
    }

    @Test(description = "Verifies 'Last Page' button in pagination on the last page")
    public void checkNextPageButtonOnLastPage(){
        projectsPage.navigateToOrderedPage(LAST_PROJECTS_PAGE)
                .verifyIfNextPageButtonDisabled();
    }

    @Test(description = "Verifies 'First Page' button in pagination")
    public void paginationButtonsCheck(){
        projectsPage.navigateToOrderedPage(THIRD_PROJECTS_PAGE)
                .clickFirstPage()
                .verifyIfFirstPageButtonSelected();
    }
}