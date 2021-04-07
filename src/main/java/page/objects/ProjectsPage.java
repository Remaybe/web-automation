package page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import other.utils.Comboboxes;
import other.utils.WaitUtils;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectsPage extends BasePage {

    private WaitUtils waitUtils;

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

    @FindBy(xpath = "//span[text()='Area']")
    private WebElement areaHeaderOfTable;

    @FindBy(xpath = "//span[contains(@class, 'sortLabel')]/*")
    private WebElement filterMarkerOfTableHeader;

    @FindBy(xpath = "//button[@title='Clear']")
    private List<WebElement> cmbbxClearButtons;

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCmmbxInput(Comboboxes cmbbx){
        return driver.findElement(By.xpath("//label[text()='" + cmbbx.getString() + "']/following-sibling::div/input"));
    }

    public WebElement getCmbbxButton(Comboboxes cmbbx){
        return driver.findElement(By.xpath("//label[text()='" + cmbbx.getString() + "']/following-sibling::div//button[contains(@class, 'popup')]"));
    }

    @Step("Clicks 'Clear' button")
    public ProjectsPage clearFilters(){
        clrButton.click();
        return this;
    }

    @Step("Checks 'clear button' status")
    public void verifyChckButtonStatus(boolean expectedResult){
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
        this.inputCmbbxValue(cmbbx, value).chooseElement(value).clickCmbbxButton(cmbbx);
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
    public void verifySrchblProject(String header){
        WaitUtils.waitForVisibilityElement(projectHeader);
        Assert.assertEquals(projectHeader.getText(), header);
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
    public void verifySelectedCheckboxes(boolean expectedResult){
        assertThat(managedByMeCheckbox.isSelected() && caseStudyCheckbox.isSelected() && activeProjectsCheckbox.isSelected())
                .as("Checkboxes selected incorrectly")
                .isEqualTo(expectedResult);
    }

    @Step("Filters column header by its status")
    public ProjectsPage filterByStatusHeader(){
        statusHeaderOfTable.click();
        return this;
    }

    @Step("Filters column header by its area")
    public ProjectsPage filterByAreaHeader(){
        areaHeaderOfTable.click();
        return this;
    }

    @Step("Verifies clear status after discarding the filters of column headers")
    public void verifyHeadingsClearFilters(){
        assertThat(filterMarkerOfTableHeader.isDisplayed())
                .as("Marker should disappear after discarding")
                .isEqualTo(false);
    }

    @Step("Clears chosen filter by value in combobox field")
    public ProjectsPage discardCmbbxValueByXmark(String value){
        driver.findElement(By.xpath("//span[text()='" + value + "']/following-sibling::*")).click();
        return this;
    }

    @Step("Clears existing combobox values from its fields")
    public ProjectsPage clearCmbbxValues(){
        for (int i = 0; i < cmbbxClearButtons.size(); i++) {
            WebElement element = cmbbxClearButtons.get(i);
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
            driver.findElement(By.xpath("//*[contains(text(), '" + cmbbx.getString() + "')]/..//input/../div[1][@role='button']"));
            actualResult = true;
        } catch (NoSuchElementException e) {
            actualResult = false;
        }
        return actualResult;
    }

    @Step("Verifies discards of clear operation")
    public void verifiesDiscardingFields(Comboboxes cmbbx, boolean expectedResult){
        assertThat(checksElementInCmbbxFld(cmbbx))
                .as("Filtered element from list shouldn't be present on page")
                .isEqualTo(expectedResult);
    }

    @Step("Return button to navigate on page by its number")
    public WebElement getPageByNumber(int numberOfPage){
        return driver.findElement(By.xpath("//ul[contains(@class, 'Pagination')]/li/button[text()='" + numberOfPage + "']"));
    }

    @Step("Move to ordered page using pagination element")
    public ProjectsPage navigateToOrderedPage(int numberOfPage){
        this.getPageByNumber(numberOfPage).click();
        return this;
    }

    @Step("Verifies if navigate button clickable in pagination")
    public void verifyIfNavigateButtonClickable(int numberOfPage, boolean expectedResult){
        assertThat(getPageByNumber(numberOfPage).isEnabled())
                .as("Button should be clickable if weren't selected")
                .isEqualTo(expectedResult);
    }

    @Step("Verifies if 'Next Page' button is disabled")
    public void verifyIfNextPageButtonDisabled(){
        assertThat(paginationNextPage.isEnabled())
                .as("Button shouldn't be enabled")
                .isEqualTo(false);
    }

    @Step("Verifies if 'First Page' button is disabled")
    public void verifyIfFirstPageButtonSelected(){
        assertThat(paginationNextPage.isEnabled())
                .as("Button shouldn't be enabled")
                .isEqualTo(false);
    }

    @Step("Moves to first page of projects list")
    public ProjectsPage clickFirstPage(){
        paginationFirstPage.click();
        return this;
    }
}
