import org.testng.annotations.Test;

public class CaseStudiesPageTest extends BaseTest {
    private CaseStudiesPage caseStudies;

    @Test
    public void creatingTestCaseStudy(){
        String img = System.getProperty("user.dir") + "/testscreen.png";
        projectsPage
                .filterByCmbbxValue(Comboboxes.ACCOUNTS, "AEO")
                .filterByCmbbxValue(Comboboxes.TECHNOLOGIES, "Typescript")
                .openProject();
        caseStudies = projectsPage.openProject();
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
}
