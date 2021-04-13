package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import other.utils.AllureHelper;
import other.utils.Comboboxes;
import other.utils.WaitUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectsPage extends BasePage {

    @FindBy(xpath = "//ul[contains(@class, 'Pagination')]/li[1]/button")
    private WebElement paginationFirstPage;

    @FindBy(xpath = "//ul[contains(@class, 'Pagination')]/li[8]/button")
    private WebElement paginationNextPage;

    @FindBy(xpath = "//h6[contains(@class, 'colorTextPrimary')]")
    private WebElement projectHeader;

    @FindBy(xpath = "//div[contains(@class, '3mHQO')]/button")
    private WebElement clrButton;

    @FindBy(xpath = "//tbody//a[contains(@class, 'underline')]/h6")
    private WebElement searchableProjectFromList;

    @FindBy(xpath = "//span[text()='With case studies only']/..//input")
    private WebElement caseStudyCheckbox;

    @FindBy(xpath = "//span[text()='Only active projects']/..//input")
    private WebElement activeProjectsCheckbox;

    @FindBy(xpath = "//span[text()='Managed by me']/..//input")
    private WebElement managedByMeCheckbox;

    @FindBy(xpath = "//span[text()='Status']")
    private WebElement statusHeaderOfTable;

    @FindBy(xpath = "//span[contains(@class, 'sortLabel')]/*")
    private WebElement filterMarkerOfTableHeader;

    @FindBy(xpath = "//button[@title='Clear']")
    private List<WebElement> cmbbxClearButtons;

    @FindBy(xpath = "//h6[contains(@class, 'colorTextPrimary')]")
    private List<WebElement> searchableProjectsByName;

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Returns 'false' there are any projects with no 'Case Studies' in it, else returns 'true'")
    public boolean checksIfEveryProjectHasStudies(){
        for (WebElement account : searchableProjectsByName) {
            try {
                account.findElements(By.xpath("//ancestor::tr//td[contains(@class, 'caseStudiesColumn')]//button"));
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    @Step("Checks is list of projects updated due to 'Case Studies Only' condition")
    public ProjectsPage verifyIsListContainsProjectsWithCaseStudiesOnly(){
        softAssertions.assertThat(checksIfEveryProjectHasStudies())
                .as("All projects in list must contain at least one 'Case Study'")
                .isTrue();
        return this;
    }

    @Step("Adds filters to chosen combobox due to dataprovider")
    public ProjectsPage addFiltersFromDataprovider(List<String> value, Comboboxes cmbbx){
        value.forEach(v -> this.filterByCmbbxValue(cmbbx, v));
        return this;
    }

    @Step("Gets WebElement of input of chosen combobox")
    public WebElement getCmmbxInput(Comboboxes cmbbx){
        return driver.findElement(By.xpath("//label[text()='" + cmbbx.getString() + "']/following-sibling::div/input"));
    }

    @Step("Gets WebElement of button of chosen combobox")
    public WebElement getCmbbxButton(Comboboxes cmbbx){
        return driver.findElement(By.xpath("//label[text()='" + cmbbx.getString() + "']/following-sibling::div//button[contains(@class, 'popup')]"));
    }

    @Step("Clicks 'Clear' button")
    public ProjectsPage clearFilters(){
        clrButton.click();
        return this;
    }

    @Step("Checks 'clear button' status")
    public void verifyClrButtonStatus(boolean expectedResult){
        assertThat(clrButton.isEnabled())
                .as("'Clear' button should be disabled when there are no filtered values")
                .isEqualTo(expectedResult);
    }

    @Step("Gets name of searchable project")
    public String getProjectHeader() {
        return projectHeader.getText();
    }

    @Step("Opens list of values in chosen combobox")
    public ProjectsPage clickCmbbxButton(Comboboxes cmbbx){
        WaitUtils.waitForVisibilityElement(getCmbbxButton(cmbbx));
        getCmbbxButton(cmbbx).click();
        return this;
    }

    @Step("Fills chosen combobox field with some text")
    public ProjectsPage inputCmbbxValue(Comboboxes cmbbx, String value){
        getCmmbxInput(cmbbx).sendKeys(value);
        return this;
    }

    @Step("Filters projects by using chosen combobox and its value from list")
    public ProjectsPage filterByCmbbxValue(Comboboxes cmbbx, String value) {
        inputCmbbxValue(cmbbx, value)
                .chooseElement(value)
                .clickCmbbxButton(cmbbx);
        return this;
    }

    @Step("Verifies value of input and appeared element")
    public void verifyInputAndAppearedValueEquals(String value)
    {
        assertThat(driver.findElement(By.xpath("//*[contains(text(), '" + value + "')]/..//input")).getText())
                .as("Input and appeared values in combobox should be matched")
                .contains(driver.findElement(getElementFromList(value)).getText());
    }

    @Step("Chooses searchable element from list in combobox")
    public ProjectsPage chooseElement(String nameOfElement) {
        driver.findElement(getElementFromList(nameOfElement)).click();
        return this;
    }

    @Step("Gets web element of searchable value from combobox")
    private By getElementFromList(String nameOfElement){
        return By.xpath("//li[contains(text(), '" + nameOfElement + "')]//input");
    }

    @Step("Opens project from project's table")
    public CaseStudiesPage openProject(){
        WaitUtils.waitForVisibilityElement(searchableProjectFromList);
        searchableProjectFromList.click();
        return new CaseStudiesPage(driver);
    }

    @Step("Verify if project has been found")
    public ProjectsPage verifySrchblProject(String header){
        WaitUtils.waitByExpectedElementText(projectHeader, header);
        softAssertions.assertThat(projectHeader.getText())
                .as("Searchable project by chosen name should exist at the projects list")
                .isEqualTo(header);
        return this;
    }

    @Step("Verifies if list of projects was updated after some action")
    public ProjectsPage isProjectListUpdated(Supplier<?> action){
        AtomicReference<String> defaultPageSource = new AtomicReference<>();
        AtomicReference<Boolean> result = new AtomicReference<>();
        AllureHelper.addStep("Sets current page source of project's list",
                () -> defaultPageSource.set(getPageSource()));
        AllureHelper.addStep("Runs a chosen user's action", action);
        AllureHelper.addStep("Compares default page source with received after the action, sets 'True' if they are not equal",
                () -> result.set(!getPageSource().equals(defaultPageSource.get())));
        softAssertions.assertThat(result.get())
                .as("List of project's should be updated")
                .isTrue();
        return this;
    }

    @Step("Collects and asserts all soft assertions")
    public void verifyAll(){
        softAssertions.assertAll();
    }

    @Step("Gets current page source")
    public String getPageSource(){
        return driver.getPageSource();
    }

    @Step("Selects 'With case studies only' checkbox")
    public ProjectsPage selectCaseStudyCheckbox(){
        caseStudyCheckbox.click();
        return this;
    }

    @Step("Selects 'Only active projects' checkbox")
    public ProjectsPage selectActiveProjectsCheckbox(){
        activeProjectsCheckbox.click();
        return this;
    }

    @Step("Selects 'Managed by me' checkbox")
    public ProjectsPage selectManagedByMeCheckbox(){
        managedByMeCheckbox.click();
        return this;
    }

    @Step("Filters by selecting all existing checkboxes")
    public ProjectsPage selectAllCheckboxes(){
        this.selectCaseStudyCheckbox()
                .selectActiveProjectsCheckbox()
                .selectManagedByMeCheckbox();
        return this;
    }

    @Step("Verifies, are checkboxes selected or not")
    public ProjectsPage verifySelectedCheckboxes(boolean expectedResult){
        softAssertions.assertThat(managedByMeCheckbox.isSelected() && caseStudyCheckbox.isSelected() && activeProjectsCheckbox.isSelected())
                .as("Checkboxes selected incorrectly")
                .isEqualTo(expectedResult);
        return this;
    }

    @Step("Filters column header by its status")
    public ProjectsPage filterByStatusHeader(){
        statusHeaderOfTable.click();
        return this;
    }

    @Step("Verifies clear status after discarding the filters of column headers")
    public ProjectsPage verifyHeadingsClearFilters(){
        softAssertions.assertThat(filterMarkerOfTableHeader.isDisplayed())
                .as("Marker should disappear after discarding")
                .isFalse();
        return this;
    }

    @Step("Clears chosen filter by value in combobox field")
    public ProjectsPage discardCmbbxValueByXmark(String value){
        driver.findElement(By.xpath("//span[text()='" + value + "']/following-sibling::*")).click();
        return this;
    }

    @Step("Clears existing combobox values from its fields")
    public ProjectsPage clearCmbbxValues(){
        for (WebElement element : cmbbxClearButtons) {
            if (element.isDisplayed()) {
                element.click();
            }
        }
        return this;
    }

    @Step("Returns 'False', if there is no values in chosen combobox field")
    public boolean checksElementInCmbbxFld(Comboboxes cmbbx){
        boolean actualResult;
        try {
            driver.findElement(By.xpath("//label[contains(text(), '" + cmbbx.getString() + "')]/..//input/../div[1][@role='button']"));
            actualResult = true;
        } catch (NoSuchElementException e) {
            actualResult = false;
        }
        return actualResult;
    }

    @Step("Verifies discards of clear operation")
    public ProjectsPage verifiesDiscardingFields(Comboboxes cmbbx){
        softAssertions.assertThat(checksElementInCmbbxFld(cmbbx))
                .as("Filtered element from list shouldn't be present on page")
                .isFalse();
        return this;
    }

    @Step("Return button to navigate on page by its number")
    public WebElement getPageByNumber(int numberOfPage){
        return driver.findElement(By.xpath("//ul[contains(@class, 'Pagination')]/li/button[text()='" + numberOfPage + "']"));
    }

    @Step("Move to ordered page using pagination element")
    public ProjectsPage navigateToOrderedPage(int numberOfPage){
        Runnable action = () -> this.getPageByNumber(numberOfPage).click();
        AllureHelper.addStep("Move to ordered page using pagination element", action);
        action.run();
        return this;
    }

    @Step("Verifies if navigate button clickable in pagination")
    public void verifyIfNavigateButtonClickable(int numberOfPage, boolean expectedResult){
        assertThat(getPageByNumber(numberOfPage).isEnabled())
                .as("Button should be clickable if weren't selected")
                .isEqualTo(expectedResult);
    }

    @Step("Verifies if 'Next Page' button is disabled")
    public ProjectsPage verifyIfNextPageButtonDisabled(){
        softAssertions.assertThat(paginationNextPage.isEnabled())
                .as("Button shouldn't be enabled")
                .isFalse();
        return this;
    }

    @Step("Verifies if 'First Page' button is disabled")
    public ProjectsPage verifyIfFirstPageButtonSelected(){
        softAssertions.assertThat(paginationNextPage.isEnabled())
                .as("Button shouldn't be enabled")
                .isFalse();
        return this;
    }

    @Step("Moves to first page of projects list")
    public ProjectsPage clickFirstPage(){
        paginationFirstPage.click();
        return this;
    }
}
