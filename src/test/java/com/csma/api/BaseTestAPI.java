package com.csma.api;
import com.csma.browser_factory.Browser;
import com.csma.browser_factory.BrowserFactory;
import com.csma.browser_factory.BrowserType;
import com.csma.page_objects.AuthPage;
import com.csma.page_objects.ProjectsPage;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BaseTestAPI {

    protected static RequestSpecification specification;

    @BeforeClass
    public void beforeAll() {
        WebDriver driver = new BrowserFactory().createBrowser(BrowserType.CHROME).getDriver();
        driver.get("https://csma-staging.griddynamics.net/projects");
        AuthPage authPage = new AuthPage(driver);
        authPage.auth("emaznev", "@85411321eGo885441113221");
        ProjectsPage projectsPage = new ProjectsPage(driver);
        projectsPage.waitTillLoad();

        Set<Cookie> cookies = Browser.getCurrentDriver().manage().getCookies();
        String currentCookie = cookies.iterator().next().toString();

        specification = new RequestSpecBuilder()
                .setBaseUri("https://csma-staging.griddynamics.net/api/graphql")
                .addHeader("Cookie", currentCookie)
                .addHeader("Content-Type", "application/json")
                .build();

        driver.quit();
    }

    protected boolean hasDuplicate(List<Integer> list){
        Set<Integer> updList = new HashSet();
        for(Integer value : list) {
            if(updList.contains(value)) return true;
            else updList.add(value);
        }
        return false;
    }
}
