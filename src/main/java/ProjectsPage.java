import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends BasePage {

    @FindBy(xpath = "//h6[contains(@class, 'colorTextPrimary')]")
    private WebElement projectHeader;

    @FindBy(xpath = "//div[contains(@class, '3mHQO')]/button")
    private WebElement clrButton;

    @FindBy(xpath = "//tbody//a[contains(@class, 'underline')]/h6")
    private WebElement searchableProjectFromList;

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Checks 'clear button' status")
    public boolean chckButtonStatus(){
        return clrButton.isEnabled();
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
}
