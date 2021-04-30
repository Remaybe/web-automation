package com.csma.web.utils;

import com.csma.web.browser_factory.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import com.csma.web.page_objects.AuthPage;

import java.lang.reflect.Method;

public class SessionListener implements WebDriverListener {

    private WebDriver driver;

    public SessionListener() {
        this.driver = Browser.getCurrentDriver();
    }

    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        conditionSolutionWrapper();
    }

    @Override
    public void beforeAnyCall(Object target, Method method, Object[] args) {
        conditionSolutionWrapper();
    }

    private void conditionSolutionWrapper(){
        if (driver.getCurrentUrl().contains("sso.griddynamics.net")) {
            AuthPage authPage = new AuthPage(driver);
            authPage.auth("emaznev", "@85411321eGo885441113221");
        }
    }
}
