import io.qameta.allure.Feature;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Feature("Creating of case study")
public class CaseStudiesPageTest extends BaseTest {
    private CaseStudiesPage caseStudies;

    @Test(description = "Creates valid case study with image uploading")
    @Ignore
    public void creatingTestCaseStudy(){
        String img = System.getProperty("user.dir") + "/testscreen.png";
        caseStudies = ProjectType.SUNFLOWER.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Name")
                .fillAllFldsOnFirstStep("some text")
                .clickContinue()
                .uploadImg(img);  // problem
//                .clickContinue()
//                .fillAllFldsOnThirdStep("some text")
//                .clickDiscard()
//                .fillAllFldsOnThirdStep("some new text")
//                .saveStudy();
    }

    @Test(description = "Creates case study with no input values")
    @Ignore
    public void createStudyWithoutInput(){
        caseStudies = ProjectType.SUNFLOWER.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .saveStudy()
                .verifySaveButtonStatus(false);
    }

    @Test(description = "Creates case study only by filling 'name' field")
    @Ignore
    public void createStudyOnlyWithName(){
        caseStudies = ProjectType.SUNFLOWER.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Some name")
                .saveStudy()
                .verifySaveButtonStatus(true);
    }

    @Test(description = "Opens creator of case study form immediately after saving the previous one")
    @Ignore
    public void createStudyImmediatelyAfterPrevious(){
        caseStudies = ProjectType.SUNFLOWER.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Some name")
                .saveStudy()
                .openStudyCreator()
                .verifyOpenedStudyCreator(true);
    }

}
