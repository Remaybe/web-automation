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

    public boolean chckButtonStatus(){
        return clrButton.isEnabled();
    }

    public String getProjectHeader() {
        return projectHeader.getText();
    }

    public ProjectsPage clickCmbbxButton(Comboboxes cmbbx){
        driver.findElement(By.xpath("//*[contains(text(), '" + cmbbx.getString() + "')]/..//button[2]")).click();
        return this;
    }

    public ProjectsPage inputCmbbxValue(Comboboxes cmbbx, String value){
        driver.findElement(By.xpath("//*[contains(text(), '" + cmbbx.getString() + "')]/..//input")).sendKeys(value);
        return this;
    }

    public ProjectsPage filterByCmbbxValue(Comboboxes cmbbx, String value) {
        this.inputCmbbxValue(cmbbx, value).chooseElement(value).clickCmbbxButton(cmbbx);
        return this;
    }

    public ProjectsPage chooseElement(String nameOfElement) {
        driver.findElement(getElementFromList(nameOfElement)).click();
        return this;
    }

    private By getElementFromList(String nameOfElement){
        return By.xpath("//li[contains(text(), '" + nameOfElement + "')]//input");
    }

    public CaseStudiesPage openProject(){
        searchableProjectFromList.click();
        return new CaseStudiesPage(driver);
    }
}
