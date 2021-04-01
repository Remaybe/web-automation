import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CaseStudiesPage extends BasePage {

    private JavascriptExecutor js;
    private WaitUtils waitUtils;

    public CaseStudiesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(@class, 'styles_name')]")
    private WebElement nameExamplePattern;

    @FindBy(xpath = "//header[contains(@class, 'pageHeader')]/..//div[contains(@class, 'summary__description')]/p")
    private WebElement summaryExamplePattern;

    @FindBy(xpath = "//header[contains(@class, 'pageHeader')]/..//div[contains(@class, 'problem__description')]/p")
    private WebElement challengeExamplePattern;

    @FindBy(xpath = "//header[contains(@class, 'pageHeader')]/..//div[contains(@class, 'challenges__description')]/p")
    private WebElement keyChallengeExamplePattern;

    @FindBy(xpath = "//header[contains(@class, 'pageHeader')]/..//div[contains(@class, 'solution__description')]/p")
    private WebElement proposedSolutionExamplePattern;

    @FindBy(xpath = "//header[contains(@class, 'pageHeader')]/..//div[contains(@class, 'keyFigures__description')]/p")
    private WebElement keyFiguresExamplePattern;

    @FindBy(xpath = "//header[contains(@class, 'pageHeader')]/..//div[contains(@class, 'result__description')]/p")
    private WebElement achievedResultExamplePattern;

    @FindBy(xpath = "//span[contains(text(), 'new case study')]/..")
    private WebElement createStudyButton;

    @FindBy(xpath = "//button[contains(@class, 'editable')]")
    private WebElement editDescMarker;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameInputFld;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveStudyButton;

    @FindBy(xpath = "//div[@id='problem']//div[contains(@class, 'ql-editor')]")
    private WebElement challengeInputFld;

    @FindBy(xpath = "//div[@id='summary']//div[contains(@class, 'ql-editor')]")
    private WebElement summaryInputFld;

    @FindBy(xpath = "//div[@id='challenges']//div[contains(@class, 'ql-editor')]")
    private WebElement keyChallengesInputFld;

    @FindBy(xpath = "//div[@id='solution']//div[contains(@class, 'ql-editor')]")
    private WebElement proposedSolutionFld;

    @FindBy(xpath = "//button[contains(@class, 'image')]")
    private WebElement insImgButton;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement inputImg;

    @FindBy(xpath = "//div[@id='result']//div[contains(@class, 'ql-editor')]")
    private WebElement achievedRsltFld;

    @FindBy(xpath = "//div[@id='keyFigures']//div[contains(@class, 'ql-editor')]")
    private WebElement keyFiguresFld;

    @FindBy(xpath = "//span[text()='Continue']/..")
    private WebElement continueButton;

    @FindBy(xpath = "//span[text()='Discard']/..")
    private WebElement discardButton;

    @FindBy(xpath = "//span[text()='Problem statement']")
    private WebElement firstStepLink;

    @FindBy(xpath = "//span[text()='Proposed solution']")
    private WebElement secondStepLink;

    @FindBy(xpath = "//span[text()='Achieved result']")
    private WebElement thirdStepLink;

    @Step("Verifies is study's name matches itself on example pattern")
    public CaseStudiesPage verifyNameMatchExamplePattern(String name){
        softAssertions.assertThat(nameExamplePattern.getText())
                .as("Name value doesn't match on example pattern of 'Case Study'")
                .isEqualTo(name);
        return this;
    }

    public void ver(){
        boolean s = achievedRsltFld.getAttribute("class").contains("ql-blank");  // need true
    }

    @Step("Verifies is 'Summary' matches itself on example pattern")
    public CaseStudiesPage verifySummaryMatchExamplePattern(String summary){
        softAssertions.assertThat(summaryExamplePattern.getText())
                .as("Summary value doesn't match on example pattern of 'Case Study'")
                .isEqualTo(summary);
        return this;
    }

    @Step("Verifies is 'Challenge' matches itself on example pattern")
    public CaseStudiesPage verifyChallengeMatchExamplePattern(String challenge){
        softAssertions.assertThat(challengeExamplePattern.getText())
                .as("Challenge value doesn't match on example pattern of 'Case Study'")
                .isEqualTo(challenge);
        return this;
    }

    @Step("Verifies is 'Key Challenge' matches itself on example pattern")
    public CaseStudiesPage verifyKeyChallengeMatchExamplePattern(String keyChallenge){
        softAssertions.assertThat(keyChallengeExamplePattern.getText())
                .as("Key Challenge value doesn't match on example pattern of 'Case Study'")
                .isEqualTo(keyChallenge);
        return this;
    }

    @Step("Verifies match of uploaded image on 'Solution' field of 'Case Study'")
    public CaseStudiesPage verifyUploadedImgOnSolutionFld(){
        boolean isUploaded = false;
        try {
            driver.findElement(By.xpath("//header[contains(@class, 'pageHeader')]/..//div[contains(@class, 'solution__description')]/p/img"));
            isUploaded = true;
        } catch (Exception n) {}
        softAssertions.assertThat(isUploaded)
                .as("Image doesn't match on example pattern of 'Case Study' or wasn't uploaded")
                .isEqualTo(true);
        return this;
    }

    @Step("Verifies is 'Key Figures' matches itself on example pattern")
    public CaseStudiesPage verifyKeyFiguresMatchExamplePattern(String keyFigure){
        softAssertions.assertThat(keyFiguresExamplePattern.getText())
                .as("Key Figure value doesn't match on example pattern of 'Case Study'")
                .isEqualTo(keyFigure);
        return this;
    }

    @Step("Verifies is 'Achieved Result' matches itself on example pattern")
    public CaseStudiesPage verifyAchievedResultMatchExamplePattern(String result){
        softAssertions.assertThat(achievedResultExamplePattern.getText())
                .as("Achieved Result value doesn't match on example pattern of 'Case Study'")
                .isEqualTo(result);
        return this;
    }

    @Step("Collects and asserts all soft assertions")
    public void verifyAll(){
        softAssertions.assertAll();
    }

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
        waitUtils = new WaitUtils(driver);
        waitUtils.waitForVisibilityElement(insImgButton);
        insImgButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                inputImg,
                "display",
                "inline");
        inputImg.sendKeys(file);
        returnFocusOnWindow();
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
        assertThat(saveStudyButton.isEnabled())
                .as("'Save' button status is incorrect")
                .isEqualTo(expectedStatus);
    }

    @Step("Verify if case study's creator form opened")
    public void verifyOpenedStudyCreator(boolean expectedStatus){
        assertThat(summaryInputFld.isDisplayed())
                .as("Study's creator form should be opened")
                .isEqualTo(expectedStatus);
    }

    @Step("Returns true, if Case Study by that name was created")
    public boolean checkStudyFromListByName(String caseStudyName){
        boolean isPresent;
        try {
            driver.findElement(By.xpath(" //td[contains(@class, 'caseStudyName')]/../td[text()='" + caseStudyName + "']"));
            isPresent = true;
        } catch (NoSuchElementException e) {
            isPresent = false;
        }
        return isPresent;
    }

    @Step("Verifies, if Case Study by that name was created")
    public CaseStudiesPage verifyCreatedCaseStudy(String caseStudyName){
        softAssertions.assertThat(checkStudyFromListByName(caseStudyName))
                .as("Case Study wasn't created")
                .isEqualTo(true);
        return this;
    }
}
