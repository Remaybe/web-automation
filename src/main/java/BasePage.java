import io.qameta.allure.Story;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

@Slf4j
public class BasePage {
    WebDriver driver;
    protected SoftAssertions softAssertions = new SoftAssertions();
    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @SneakyThrows
    @Story("Closes pop-up window, returning focus on previous window in browser")
    public BasePage returnFocusOnWindow(){
        Robot bot = new Robot();
        bot.mouseMove(120, 50);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(1000);
        bot.keyPress(KeyEvent.VK_ESCAPE);
        bot.keyRelease(KeyEvent.VK_ESCAPE);
        log.info("Creates bot, which will return focus on window in browser by closing excess pop-up window");
        return this;
    }

    public BasePage setCliboard(String text){
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        log.info("Input clipboard with ordered text");
        return this;
    }

    @SneakyThrows
    public String getTextFromCliboard(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        log.info("Returns String value of text, which currently in clipboard");
        return (String)contents.getTransferData(DataFlavor.stringFlavor);
    }

    @SneakyThrows
    public BasePage pasteTextFromClipboard(){
        Robot bot = new Robot();
        bot.keyPress(KeyEvent.VK_META);
        bot.keyPress(KeyEvent.VK_V);
        bot.keyRelease(KeyEvent.VK_META);
        bot.keyRelease(KeyEvent.VK_V);
        log.info("Creates robot to make an operation of pasting the text");
        return this;
    }

    @SneakyThrows
    public BasePage copyText(){
        Robot bot = new Robot();
        bot.keyPress(KeyEvent.VK_META);
        bot.keyPress(KeyEvent.VK_C);
        bot.keyRelease(KeyEvent.VK_META);
        bot.keyRelease(KeyEvent.VK_C);
        log.info("Creates robot to make an operation of copying the text");
        return this;
    }

    @SneakyThrows
    public BasePage deleteText(){
        Robot bot = new Robot();
        bot.keyPress(KeyEvent.VK_DELETE);
        bot.keyRelease(KeyEvent.VK_DELETE);
        log.info("Creates robot to make an operation of deleting the text");
        return this;
    }

    @SneakyThrows
    public BasePage selectAllText(){
        Robot bot = new Robot();
        bot.keyPress(KeyEvent.VK_META);
        bot.keyPress(KeyEvent.VK_A);
        bot.keyRelease(KeyEvent.VK_META);
        bot.keyRelease(KeyEvent.VK_A);
        log.info("Creates robot to make an operation of selecting all text");
        return this;
    }
}
