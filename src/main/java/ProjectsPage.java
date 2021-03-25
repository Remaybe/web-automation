import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Checks 'clear button' status")
    public void verifyChckButtonStatus(boolean expectedResult){
        Assert.assertEquals(clrButton.isEnabled(), expectedResult);
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
        Assert.assertEquals(driver.findElement(By.xpath("//*[contains(text(), '" + value + "')]/..//input")).getText(),
                driver.findElement(getElementFromList(value)).getText());
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
        Assert.assertEquals(managedByMeCheckbox.isSelected() && caseStudyCheckbox.isSelected() && activeProjectsCheckbox.isSelected(),
                expectedResult);
    }
}
