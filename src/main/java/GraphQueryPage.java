import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

public class GraphQueryPage extends BasePage {
    public GraphQueryPage(WebDriver driver) {
        super(driver);
        driver.get("https://csma-staging.griddynamics.net/api/graphql");
    }

    static private final int DISTANCE_BETWEEN_NAME_AND_ID = 20;

    @FindBy(xpath = "//div[contains(@title, 'Ctrl-Enter')]")
    private WebElement executeCodeBtn;

    @Step("Deletes Case Study by its name")
    public void deleteCaseStudy(String studyName){
        clearQueryField();
        setCliboard("{\n" + "  caseStudies {\n" + "    id\n" + "    name\n" + "  }\n" + "}");
        executeCodeFromClipboard();
        copyOutputExecution();
        clearQueryField();
        setCliboard("mutation {\n" + "  deleteCaseStudy(caseStudyId: " + getIdOfStudyByName(getTextFromCliboard(), studyName) + ")\n" + "}");
        executeCodeFromClipboard();
    }

    @SneakyThrows
    @Step("Selects and copies execute code from output field")
    private GraphQueryPage copyOutputExecution(){
        selectOutputField();
        Thread.sleep(500);
        selectAllText();
        copyText();
        Thread.sleep(500);
        return this;
    }

    @Step("Pastes text from clipboard to query field and executes it")
    private GraphQueryPage executeCodeFromClipboard(){
        pasteTextFromClipboard();
        executeCodeBtn.click();
        return this;
    }

    @Step("Clears all text in query field")
    private GraphQueryPage clearQueryField(){
        selectQueryField();
        selectAllText();
        deleteText();
        return this;
    }

    @SneakyThrows
    @Step("Selects an output field")
    private GraphQueryPage selectOutputField(){
        Robot bot = new Robot();
        bot.mouseMove(900, 500);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        return this;
    }

    @SneakyThrows
    @Step("Selects a query field")
    private GraphQueryPage selectQueryField(){
        Robot bot = new Robot();
        bot.mouseMove(100, 300);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        return this;
    }

    @Step("Gets position of first letter of Study's name by executed result of query")
    private int getFirstNameLetterPosition(String queryStudiesResult, String studyName){
        int firstNameLetterPosition = 0;
        java.util.List<String> charQueryList = getCharQueryList(queryStudiesResult);
        for (int i = 0; i < charQueryList.size(); i++) {
            StringBuilder name = new StringBuilder();
            for (int j = 0; j < studyName.toCharArray().length; j++) {
                name.append(charQueryList.get(i + j));
            }
            if (name.toString().equals(studyName)) {
                firstNameLetterPosition = i;
                break;
            }
        }
        return firstNameLetterPosition;
    }

    @Step("Gets Study's id by its name and by executed result of query")
    private int getIdOfStudyByName(String queryStudiesResult, String studyName){
        StringBuilder descId = new StringBuilder();
        int dynamicDistance = DISTANCE_BETWEEN_NAME_AND_ID;
        java.util.List<String> charQueryList = getCharQueryList(queryStudiesResult);
        int firstNameLetterPosition = getFirstNameLetterPosition(queryStudiesResult, studyName);
        while (!charQueryList.get(firstNameLetterPosition - dynamicDistance).equals(" ")) {
            descId.append(charQueryList.get(firstNameLetterPosition - dynamicDistance));
            dynamicDistance++;
        }
        return Integer.parseInt(new StringBuilder(descId.toString()).reverse().toString());
    }

    @Step("Makes array of chars from executed query in output field")
    private List<String> getCharQueryList(String queryStudiesResult) {
        List<String> charQueryList = new ArrayList<>();
        for (char c : queryStudiesResult.toCharArray()) charQueryList.add(String.valueOf(c));
        return charQueryList;
    }
}
