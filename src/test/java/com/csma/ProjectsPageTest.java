package com.csma;

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
        projectsPage
                .addFiltersFromDataprovider(value, Comboboxes.PROJECT)
                .isProjectListUpdated(
                        () -> projectsPage.clearCmbbxValues())
                .verifiesDiscardingFields(Comboboxes.PROJECT)
                .verifyAll();
    }

    @Test(description = "Search for project using comboboxes")
    public void searchUsingCmbbxs(){
        String prjctName = "Mobile app";
        projectsPage
                .isProjectListUpdated(
                        () -> projectsPage.filterByCmbbxValue(Comboboxes.ACCOUNT, "Coleman"))
                .isProjectListUpdated(
                        () -> projectsPage.filterByCmbbxValue(Comboboxes.PROJECT, prjctName))
                .verifySrchblProject(prjctName)
                .verifyAll();
    }

    @Test(description = "Tests 'clear' button without filter values")
    public void clrButtonStatusWithoutValues(){
        projectsPage.verifyClrButtonStatus(false);
    }

    @Test(description = "Filtering by selecting all checkboxes")
    public void filterWithCheckboxes(){
        projectsPage
                .isProjectListUpdated(
                        () -> projectsPage.selectAllCheckboxes())
                .verifySelectedCheckboxes(true);
    }

    @Test(description = "Equals input value and appeared from list in current combobox")
    public void checkCmbbxValueFromList(){
        String value = "React";
        projectsPage
                .inputCmbbxValue(Comboboxes.TECHNOLOGY, value)
                .verifyInputAndAppearedValueEquals(value);
    }

    @Test(description = "Verifies if filters of headers were cleared")
    public void discardFiltersByColumnHeaders(){
        projectsPage
                .isProjectListUpdated(
                        () -> projectsPage.filterByStatusHeader())
                .isProjectListUpdated(
                        () -> projectsPage.filterByStatusHeader())
                .filterByStatusHeader()
                .verifyHeadingsClearFilters()
                .verifyAll();
    }

    @Test(description = "Checks usability of 'only with case study' checkbox")
    public void studiesOnlyCheckboxCheck(){
        projectsPage
                .selectCaseStudyCheckbox()
                .verifyIsListContainsProjectsWithCaseStudiesOnly()
                .isProjectListUpdated(
                        () -> projectsPage.clearFilters())
                .verifySelectedCheckboxes(false)
                .verifyAll();
    }

    @Test(description = "Discard filter by deselecting chosen item in combobox")
    public void discardFilterByDeselecting(){
        String projectName = "Align";
        projectsPage
                .isProjectListUpdated(
                        () -> projectsPage.filterByCmbbxValue(Comboboxes.PROJECT, projectName))
                .verifySrchblProject(projectName)
                .discardCmbbxValueByXmark(projectName)
                .verifiesDiscardingFields(Comboboxes.PROJECT)
                .verifyAll();
    }

    @Test(description = "Verifies 'Last Page' button in pagination on the last page")
    public void checkNextPageButtonOnLastPage(){
        projectsPage
                .isProjectListUpdated(
                        () -> projectsPage.navigateToOrderedPage(LAST_PROJECTS_PAGE))
                .verifyIfNextPageButtonDisabled()
                .verifyAll();
    }

    @Test(description = "Verifies 'First Page' button in pagination")
    public void paginationButtonsCheck(){
        projectsPage
                .isProjectListUpdated(
                        () -> projectsPage.navigateToOrderedPage(THIRD_PROJECTS_PAGE))
                .isProjectListUpdated(
                        () -> projectsPage.clickFirstPage())
                .verifyIfFirstPageButtonSelected()
                .verifyAll();
    }
}