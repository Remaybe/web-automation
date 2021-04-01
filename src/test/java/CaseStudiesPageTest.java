import io.qameta.allure.Feature;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

@Feature("Creating of case study")
public class CaseStudiesPageTest extends BaseTest {
    private CaseStudiesPage caseStudies;
    private GraphQueryPage graphQueryPage;

    @SneakyThrows
    @Test(description = "Creates valid case study with image uploading")
    public void creatingTestCaseStudy(){
        String img = System.getProperty("user.dir") + "/testscreen.png";
        String text = "Some text";
        String studyName = "remaybestudy";
        caseStudies = ProjectType.AEO.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName(studyName)
                .verifyNameMatchExamplePattern(studyName)
                .fillAllFldsOnFirstStep(text)
                .verifySummaryMatchExamplePattern(text)
                .verifyChallengeMatchExamplePattern(text)
                .verifyKeyChallengeMatchExamplePattern(text)
                .clickContinue()
                .uploadImg(img)
                .verifyUploadedImgOnSolutionFld()
                .clickContinue()
                .fillAllFldsOnThirdStep(text)
                .verifyAchievedResultMatchExamplePattern(text)
                .verifyKeyFiguresMatchExamplePattern(text)
                .clickDiscard()
                .fillAllFldsOnThirdStep("some new text")
                .saveStudy()
                .verifyCreatedCaseStudy(studyName)
                .verifyAll();
        graphQueryPage.deleteCaseStudy(studyName);
    }

    @Test(description = "Creates case study with no input values")
    public void createStudyWithoutInput(){
        caseStudies = ProjectType.AEO.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .saveStudy()
                .verifySaveButtonStatus(false);
    }

    @Test(description = "Creates case study only by filling 'name' field")
    public void createStudyOnlyWithName(){
        caseStudies = ProjectType.AEO.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Some name")
                .saveStudy()
                .verifySaveButtonStatus(true);
    }

    @Test(description = "Opens creator of case study form immediately after saving the previous one")
    public void createStudyImmediatelyAfterPrevious(){
        caseStudies = ProjectType.AEO.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Some name")
                .saveStudy()
                .openStudyCreator()
                .verifyOpenedStudyCreator(true);
    }

}
