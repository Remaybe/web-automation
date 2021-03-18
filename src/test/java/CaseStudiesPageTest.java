import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class CaseStudiesPageTest extends BaseTest {
    private CaseStudiesPage caseStudies;

    @Test
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

    @Test
    public void createStudyWithoutInput(){
        caseStudies = ProjectType.AEO_MOBILE.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .saveStudy();
    }

    @Test
    public void createStudyOnlyWithName(){
        caseStudies = ProjectType.AEO_MOBILE.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Some name")
                .saveStudy();
    }

    @Test
    public void createStudyImmediatelyAfterPrevious(){
        caseStudies = ProjectType.AEO_MOBILE.getStudiesPage(projectsPage);
        caseStudies
                .openStudyCreator()
                .setStudiesName("Some name")
                .saveStudy()
                .openStudyCreator();
    }

}
