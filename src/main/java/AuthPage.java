import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'auth-toggle')]")
    private WebElement signInLink;
    @FindBy(xpath = "//input[@id='login']")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passInput;
    @FindBy(xpath = "//button[contains(@class, 'ingrid-button')]")
    private WebElement signNowButton;

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public AuthPage clickSignIn(){
        signInLink.click();
        return this;
    }

    public AuthPage inputLogin(String login){
        loginInput.sendKeys(login);
        return this;
    }

    public AuthPage inputPass(String pass){
        passInput.sendKeys(pass);
        return this;
    }

    public AuthPage clickSignNowButton(){
        signNowButton.click();
        return this;
    }

    public void auth(String login, String pass){
        this.clickSignIn()
                .inputLogin(login)
                .inputPass(pass)
                .clickSignNowButton();
    }
}
