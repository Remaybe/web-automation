package com.csma.page_objects;

import io.qameta.allure.Step;
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

    @Step("Opens signIn form")
    public AuthPage clickSignIn(){
        signInLink.click();
        return this;
    }

    @Step("Fills login field")
    public AuthPage inputLogin(String login){
        loginInput.sendKeys(login);
        return this;
    }

    @Step("Fills password field")
    public AuthPage inputPass(String pass){
        passInput.sendKeys(pass);
        return this;
    }

    @Step("Clicks on 'Sign Now' Button")
    public AuthPage clickSignNowButton(){
        signNowButton.click();
        return this;
    }

    @Step("Signs in site by valid login and password")
    public void auth(String login, String pass){
        this.clickSignIn()
                .inputLogin(login)
                .inputPass(pass)
                .clickSignNowButton();
    }
}
