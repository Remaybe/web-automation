import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectsPage extends BasePage {

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
        driver.findElement(By.xpath("//*[contains(text(), '" + cmbbx.getString() + "')]/..//button[2]")).click();
        return this;
    }

    @Step("Fills chosen combobox field with some text")
    public ProjectsPage inputCmbbxValue(Comboboxes cmbbx, String value){
        driver.findElement(By.xpath("//*[contains(text(), '" + cmbbx.getString() + "')]/..//input")).sendKeys(value);
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
        searchableProjectFromList.click();
        return new CaseStudiesPage(driver);
    }

    @Step("Verify if project has been found")
    public void verifySrchblProject(String header){
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
//        cmbbxClearButtons.stream().filter(WebElement::isDisplayed).forEach(WebElement::click);
        for (int i = 0; i < cmbbxClearButtons.size(); i++) {
            WebElement element = cmbbxClearButtons.get(i);
            if (element.isDisplayed()) {
                element.click();
            }
        }
        return this;
    }

    @Step("Verifies discards of clear operation")
    public void verifiesDiscardingFields(Comboboxes cmbbx, boolean expectedResult){
        boolean actualResult;
        try {
            driver.findElement(By.xpath("//*[contains(text(), '" + cmbbx.getString() + "')]/..//input/../div[1][@role='button']"));
            actualResult = true;
        } catch (NoSuchElementException e) {
            actualResult = false;
        }
        assertThat(actualResult)
                .as("Filtered element from list shouldn't be present on page")
                .isEqualTo(expectedResult);
    }
}
