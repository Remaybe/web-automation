import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Feature("Filtering the projects")
public class ProjectsPageTest extends BaseTest {

    @DataProvider(name = "AreasFilters")
    public static Object[][] filtersAreasData(){
        return new Object[][] {
                {Arrays.asList("PMO")},
                {Arrays.asList("PMO", "e-commerce", "Test")}
        };
    }

    @Test(dataProvider = "AreasFilters",
            description = "Discards input values in combobox by itself")
    public void discardFiltersInsideCombobox(List<String> value){
        value.stream().forEach(v -> projectsPage.filterByCmbbxValue(Comboboxes.AREAS, v));
        projectsPage.clearCmbbxValues()
                .verifiesDiscardingFields(Comboboxes.AREAS, false);
    }

    @Test(description = "Search for project using comboboxes")
    public void searchUsingCmbbxs(){
        String prjctName = "Mobile app";
        projectsPage
                .filterByCmbbxValue(Comboboxes.ACCOUNTS, "Coleman")
                .filterByCmbbxValue(Comboboxes.PROJECTS, prjctName)
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
        String value = "Chose";
        projectsPage.inputCmbbxValue(Comboboxes.AREAS, value)
                .verifyInputAndAppearedValueEquals(value);
    }

    @Test(description = "Verifies if filters of headers were cleared")
    public void discardFiltersByColumnHeaders(){
        projectsPage.filterByStatusHeader()
                .filterByAreaHeader()
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
        projectsPage.filterByCmbbxValue(Comboboxes.AREAS, "Test")
                .discardCmbbxValueByXmark("Test")
                .verifiesDiscardingFields(Comboboxes.AREAS, false);
    }

    @Test(description = "Verifies 'Last Page' button in pagination on the last page")
    public void checkNextPageButtonOnLastPage(){
        projectsPage.navigateToOrderedPage(84)
                .verifyIfNextPageButtonDisabled();
    }

    @Test(description = "Verifies 'First Page' button in pagination")
    public void paginationButtonsCheck(){
        projectsPage.navigateToOrderedPage(3)
                .clickFirstPage()
                .verifyIfFirstPageButtonSelected();
    }
}