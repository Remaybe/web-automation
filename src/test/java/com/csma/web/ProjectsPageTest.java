package com.csma.web;

import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.csma.utils.Comboboxes;

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

    @DataProvider(name = "Comboboxes")
    public static Object[][] comboboxes(){
        return new Object[][] {
                {Comboboxes.TECHNOLOGY},
                {Comboboxes.PROJECT}
        };
    }

    @Test(dataProvider = "ProjectFilters",
            description = "Discards input values in combobox by itself")
    public void discardFiltersInsideCombobox(List<String> value){
        projectsPage.addFiltersFromDataprovider(value, Comboboxes.PROJECT)
                .isProjectListUpdated(
                        () -> projectsPage.clearCmbbxValues(), softAssertions)
                .verifiesDiscardingFields(Comboboxes.PROJECT, softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(description = "Search for project using comboboxes")
    public void searchUsingCmbbxs(){
        String prjctName = "Mobile app";
        projectsPage.isProjectListUpdated(
                        () -> projectsPage.filterByCmbbxValue(Comboboxes.ACCOUNT, "Coleman"), softAssertions)
                .isProjectListUpdated(
                        () -> projectsPage.filterByCmbbxValue(Comboboxes.PROJECT, prjctName), softAssertions)
                .verifySrchblProject(prjctName, softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(description = "Tests 'clear' button without filter values")
    public void clrButtonStatusWithoutValues(){
        projectsPage.verifyClrButtonStatus(false);
    }

    @Test(description = "Filtering by selecting all checkboxes")
    public void filterWithCheckboxes(){
        projectsPage.isProjectListUpdated(
                        () -> projectsPage.selectAllCheckboxes(), softAssertions)
                .verifySelectedCheckboxes(true, softAssertions);
    }

    @Test(description = "Equals input value and appeared from list in current combobox")
    public void checkCmbbxValueFromList(){
        String value = "React";
        projectsPage.inputCmbbxValue(Comboboxes.TECHNOLOGY, value)
                .verifyInputAndAppearedValueEquals(value);
    }

    @Test(description = "Verifies if filters of headers were cleared")
    public void discardFiltersByColumnHeaders(){
        projectsPage.isProjectListUpdated(
                        () -> projectsPage.filterByStatusHeader(), softAssertions)
                .isProjectListUpdated(
                        () -> projectsPage.filterByStatusHeader(), softAssertions)
                .filterByStatusHeader()
                .verifyHeadingsClearFilters(softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(description =
            "Checks usability of 'Only With Case Studies' checkbox and ability to discard checkbox selection by 'Clear' button")
    public void studiesOnlyCheckboxCheck(){
        projectsPage.selectCaseStudyCheckbox()
                .waitTillLoad()
                .verifyIsListContainsProjectsWithCaseStudiesOnly(softAssertions)
                .isProjectListUpdated(
                        () -> projectsPage.clearFilters(), softAssertions)
                .verifySelectedCheckboxes(false, softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(description = "Checks usability of 'Only Active Projects' checkbox")
    public void activeOnlyProjectsCheckboxCheck(){
        projectsPage.isProjectListUpdated(
                        () -> projectsPage.selectActiveProjectsCheckbox(), softAssertions)
                .waitTillLoad()
                .verifyIsOnlyActiveProjectsAppears(softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(description =
            "Checks usability of 'Only Active Projects' and 'Only With Case Studies' checkboxes by selecting them at the same time")
    public void filterByActiveAndStudiesCheckboxes(){
        projectsPage.isProjectListUpdated(
                        () -> projectsPage.selectActiveProjectsCheckbox(), softAssertions)
                .isProjectListUpdated(
                        () -> projectsPage.selectCaseStudyCheckbox(), softAssertions)
                .waitTillLoad()
                .verifyIsOnlyActiveProjectsAppears(softAssertions)
                .verifyIsListContainsProjectsWithCaseStudiesOnly(softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(description = "Discard filter by deselecting chosen item in combobox")
    public void discardFilterByDeselecting(){
        String projectName = "Align";
        projectsPage.isProjectListUpdated(
                        () -> projectsPage.filterByCmbbxValue(Comboboxes.PROJECT, projectName), softAssertions)
                .verifySrchblProject(projectName, softAssertions)
                .discardCmbbxValueByXmark(projectName)
                .verifiesDiscardingFields(Comboboxes.PROJECT, softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(description = "Verifies 'Last Page' button in pagination on the last page")
    public void checkNextPageButtonOnLastPage(){
        projectsPage.isProjectListUpdated(
                        () -> projectsPage.navigateToOrderedPage(LAST_PROJECTS_PAGE), softAssertions)
                .verifyIfNextPageButtonDisabled(softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(description = "Verifies 'First Page' button in pagination")
    public void paginationButtonsCheck(){
        projectsPage.isProjectListUpdated(
                        () -> projectsPage.navigateToOrderedPage(THIRD_PROJECTS_PAGE), softAssertions)
                .isProjectListUpdated(
                        () -> projectsPage.clickFirstPage(), softAssertions)
                .verifyIfFirstPageButtonSelected(softAssertions)
                .verifyAll(softAssertions);
    }

    @Test(dataProvider = "Comboboxes",
            description = "Checks changing of values of list from chosen combobox popper according to previous set ups in 'account' combobox")
    public void relationsOfValuesListFromComboboxes(Comboboxes cmbbx){
        List<String> listFromPopper = projectsPage.getListOfCmbbxPopper(cmbbx);
        projectsPage.clickCmbbxButton(cmbbx)
                .filterByCmbbxValue(Comboboxes.ACCOUNT, "Apple")
                .compareListsOfValues(listFromPopper, cmbbx, softAssertions)
                .clickCmbbxButton(cmbbx)
                .verifyAll(softAssertions);
    }
}