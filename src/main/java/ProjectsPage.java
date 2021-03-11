import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ProjectsPage {
    WebDriver driver;
    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
    }

//    private By comboboxesArea = By.xpath("//div[contains(@class, '3mHQO')]/../preceding-sibling::*");
//    private By comboboxesHeadings = By.xpath("//div[contains(@class, '3mHQO')]/../preceding-sibling::*//label");
//    private By checkboxesList = By.xpath("//div[contains(@class, '3mHQO')]/..//span[contains(@class, 'jss5')]//input");
//    private By clearButton = By.xpath("//div[contains(@class, '3mHQO')]/button");
//    private By allAccountsButton = By.xpath("//input[@id='mui-588']/..//button[2]");
//    private By allAccountsField = By.xpath("//input[@id='mui-588']");
//    private By allProjectsButton = By.xpath("//input[@id='mui-87883']/..//button[2]");
//    private By allAreasButton = By.xpath("//input[@id='mui-35976']/..//button[2]");
//    private By allTechnologiesButton = By.xpath("//input[@id='mui-27227']/..//button[2]");

    private By newStudyButton = By.xpath("//button[contains(@class, 'MuiButton-root')]");



}
