import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage {
    WebDriver driver;
    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    private By signInLink = By.xpath("//div[contains(@class, 'auth-toggle')]");
    private By loginInput = By.xpath("//input[@id='login']");
    private By passInput = By.xpath("//input[@id='password']");
    private By signNowButton = By.xpath("//button[contains(@class, 'ingrid-button')]");

    public AuthPage clickSignIn(){
        this.driver.findElement(signInLink).click();
        return this;
    }

    public AuthPage inputLogin(String login){
        this.driver.findElement(loginInput).sendKeys(login);
        return this;
    }

    public AuthPage inputPass(String pass){
        this.driver.findElement(passInput).sendKeys(pass);
        return this;
    }

    public ProjectsPage auth(String login, String pass){
        this.clickSignIn()
                .inputLogin(login)
                .inputPass(pass)
                .driver.findElement(signNowButton).click();
        return new ProjectsPage(driver);
    }
}
