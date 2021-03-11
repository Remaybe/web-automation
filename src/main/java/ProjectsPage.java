import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectsPage {
    WebDriver driver;
    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By projectHeader = By.xpath("//h6[contains(@class, 'colorTextPrimary')]");

    public String getProjectHeader() {
        return driver.findElement(projectHeader).getText();
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
}
