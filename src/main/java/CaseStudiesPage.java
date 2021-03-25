import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CaseStudiesPage extends BasePage {
    private JavascriptExecutor js;
    public CaseStudiesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(), 'new case study')]/..")
    WebElement createStudyButton;

    @FindBy(xpath = "//button[contains(@class, 'editable')]")
    WebElement editDescMarker;

    @FindBy(xpath = "//input[@name='name']")
    WebElement nameInputFld;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveStudyButton;

    @FindBy(xpath = "//div[@id='problem']//div[contains(@class, 'ql-editor')]")
    WebElement challengeInputFld;

    @FindBy(xpath = "//div[@id='summary']//div[contains(@class, 'ql-editor')]")
    WebElement summaryInputFld;

    @FindBy(xpath = "//div[@id='challenges']//div[contains(@class, 'ql-editor')]")
    WebElement keyChallengesInputFld;

    @FindBy(xpath = "//div[@id='solution']//div[contains(@class, 'ql-editor')]")
    WebElement proposedSolutionFld;

    @FindBy(xpath = "//button[contains(@class, 'image')]")
    WebElement insImgButton;

    @FindBy(xpath = "//input[@type='file']")
    WebElement inputImg;

    @FindBy(xpath = "//div[@id='result']//div[contains(@class, 'ql-editor')]")
    WebElement achievedRsltFld;

    @FindBy(xpath = "//div[@id='keyFigures']//div[contains(@class, 'ql-editor')]")
    WebElement keyFiguresFld;

    @FindBy(xpath = "//span[text()='Continue']/..")
    WebElement continueButton;

    @FindBy(xpath = "//span[text()='Discard']/..")
    WebElement discardButton;

    @FindBy(xpath = "//span[text()='Problem statement']")
    WebElement firstStepLink;

    @FindBy(xpath = "//span[text()='Proposed solution']")
    WebElement secondStepLink;

    @FindBy(xpath = "//span[text()='Achieved result']")
    WebElement thirdStepLink;

    @Step("Opens 'Case Study' creator")
    public CaseStudiesPage openStudyCreator(){
        createStudyButton.click();
        return this;
    }

    @Step("Fills case study's name field")
    public CaseStudiesPage setStudiesName(String name){
        nameInputFld.sendKeys(name);
        return this;
    }

    @Step("Fills 'summary' field")
    public CaseStudiesPage inputSummary(String text){
        summaryInputFld.sendKeys(text);
        return this;
    }

    @Step("Fills 'challenge' field")
    public CaseStudiesPage inputChallenge(String text){
        challengeInputFld.sendKeys(text);
        return this;
    }

    @Step("Fills 'key challenges' field")
    public CaseStudiesPage inputKeyChallenges(String text){
        keyChallengesInputFld.sendKeys(text);
        return this;
    }

    @Step("Fills 'proposed solution' field")
    public CaseStudiesPage inputProposedSolution(String text){
        proposedSolutionFld.sendKeys(text);
        return this;
    }

    @Step("Fills 'achieved result' field")
    public CaseStudiesPage inputAchievedRslt(String text){
        achievedRsltFld.sendKeys(text);
        return this;
    }

    @Step("Fills 'key figures' field")
    public CaseStudiesPage inputKeyFigures(String text){
        keyFiguresFld.sendKeys(text);
        return this;
    }

    @Step("Uploads an image into the field")
    public CaseStudiesPage uploadImg(String file) {
        js = (JavascriptExecutor)driver;
        js.executeScript("document.querySelector('input[type=file]').setAttribute('*', 'display: block')");
        inputImg.sendKeys(file);
        return this;
    }

    @Step("Goes to the next step of case study creator")
    public CaseStudiesPage clickContinue(){
        continueButton.click();
        return this;
    }

    @Step("Clears all field in current step of case study creator")
    public CaseStudiesPage clickDiscard(){
        discardButton.click();
        return this;
    }

    @Step("Moves to the first step of case study creator")
    public CaseStudiesPage moveToFirstStep(){
        firstStepLink.click();
        return this;
    }

    @Step("Moves to the second step of case study creator")
    public CaseStudiesPage moveToSecondStep(){
        secondStepLink.click();
        return this;
    }

    @Step("Moves to the third step of case study creator")
    public CaseStudiesPage moveToThirdStep(){
        thirdStepLink.click();
        return this;
    }

    @Step("Fills all fields on first step with some text")
    public CaseStudiesPage fillAllFldsOnFirstStep(String text) {
        this.inputChallenge(text)
                .inputSummary(text)
                .inputKeyChallenges(text);
        return this;
    }

    @Step("Fills all fields on third step with some text")
    public CaseStudiesPage fillAllFldsOnThirdStep(String text){
        this.inputAchievedRslt(text)
                .inputKeyFigures(text);
        return this;
    }

    @Step("Saves current case study")
    public CaseStudiesPage saveStudy(){
        saveStudyButton.click();
        return this;
    }

    @Step("Verify 'save' button status")
    public void verifySaveButtonStatus(boolean expectedStatus){
        Assert.assertEquals(saveStudyButton.isEnabled(), expectedStatus);
    }

    @Step("Verify if case study's creator form opened")
    public void verifyOpenedStudyCreator(boolean expectedStatus){
        Assert.assertEquals(summaryInputFld.isDisplayed(), expectedStatus);
    }

}
