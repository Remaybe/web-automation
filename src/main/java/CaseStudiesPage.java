import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CaseStudiesPage extends BasePage {
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

    public CaseStudiesPage openStudyCreator(){
        createStudyButton.click();
        return this;
    }

    public CaseStudiesPage setStudiesName(String name){
        nameInputFld.sendKeys(name);
        return this;
    }

    public CaseStudiesPage inputSummary(String text){
        summaryInputFld.sendKeys(text);
        return this;
    }

    public CaseStudiesPage inputChallenge(String text){
        challengeInputFld.sendKeys(text);
        return this;
    }

    public CaseStudiesPage inputKeyChallenges(String text){
        keyChallengesInputFld.sendKeys(text);
        return this;
    }

    public CaseStudiesPage inputProposedSolution(String text){
        proposedSolutionFld.sendKeys(text);
        return this;
    }

    public CaseStudiesPage inputAchievedRslt(String text){
        achievedRsltFld.sendKeys(text);
        return this;
    }

    public CaseStudiesPage inputKeyFigures(String text){
        keyFiguresFld.sendKeys(text);
        return this;
    }

    public CaseStudiesPage uploadImage(String fileDirectory){
        insImgButton.click();
        new Actions(driver).sendKeys(fileDirectory);
        return this;
    }

    public CaseStudiesPage uploadImg(String file) {
        driver.switchTo().frame(insImgButton);
        inputImg.sendKeys(file);
        return this;
    }

    public CaseStudiesPage clickContinue(){
        continueButton.click();
        return this;
    }

    public CaseStudiesPage clickDiscard(){
        discardButton.click();
        return this;
    }

    public CaseStudiesPage moveToFirstStep(){
        firstStepLink.click();
        return this;
    }

    public CaseStudiesPage moveToSecondStep(){
        secondStepLink.click();
        return this;
    }

    public CaseStudiesPage moveToThirdStep(){
        thirdStepLink.click();
        return this;
    }

    public CaseStudiesPage fillAllFldsOnFirstStep(String text){
        this
                .inputChallenge(text)
                .inputSummary(text)
                .inputKeyChallenges(text);
        return this;
    }

    public CaseStudiesPage fillAllFldsOnThirdStep(String text){
        this
                .inputAchievedRslt(text)
                .inputKeyFigures(text);
        return this;
    }

    public CaseStudiesPage saveStudy(){
        saveStudyButton.click();
        return this;
    }
}
