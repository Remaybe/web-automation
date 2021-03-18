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
        caseStudies = ProjectType.AEO_MOBILE.getStudiesPage(projectsPage);
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
    public void createStudyWithoutInput(){
        caseStudies = ProjectType.AEO_MOBILE.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .saveStudy();
    }

    @Test(description = "Creates case study only by filling 'name' field") 
    public void createStudyOnlyWithName(){
        caseStudies = ProjectType.AEO_MOBILE.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Some name")
                .saveStudy();
    }

    @Test(description = "Opens creator of case study form immediately after saving the previous one")
    public void createStudyImmediatelyAfterPrevious(){
        caseStudies = ProjectType.AEO_MOBILE.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Some name")
                .saveStudy()
                .openStudyCreator();
    }

}
