package com.csma.web.page_objects;

import com.csma.web.utils.AllureHelper;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Story("Closes pop-up window, returning focus on previous window in browser")
    public BasePage returnFocusOnWindow() {
        try {
            Robot bot = new Robot();
            bot.mouseMove(120, 50);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(1000);
            bot.keyPress(KeyEvent.VK_ESCAPE);
            bot.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
        AllureHelper.addStep("Creates bot, which will return focus on window in browser by closing excess pop-up window");
        return this;
    }
}
