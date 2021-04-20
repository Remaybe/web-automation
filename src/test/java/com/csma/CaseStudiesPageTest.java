package com.csma;

import io.qameta.allure.Feature;
import org.checkerframework.checker.units.qual.A;
import org.testng.annotations.Test;
import other.utils.ProjectType;
import page.objects.CaseStudiesPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@Feature("Creating of case study")
public class CaseStudiesPageTest extends BaseTest {
    private CaseStudiesPage caseStudies;

    @Test(description = "Creates valid case study with image uploading")
    public void creatingTestCaseStudy(){
        String img = System.getProperty("user.dir") + "/testscreen.png";
        String text = "Some text";
        String studyName = "remaybestudy";
        caseStudies = ProjectType.AEO.getStudiesPage(projectsPage);
        caseStudies.openStudyCreator()
                .setStudiesName(studyName)
                .verifyNameMatchExamplePattern(studyName, softAssertions)
                .moveToSecondStep()
                .fillAllFldsOnSecondStep(text)
                .verifySummaryMatchExamplePattern(text, softAssertions)
                .verifyChallengeMatchExamplePattern(text, softAssertions)
                .verifyKeyChallengeMatchExamplePattern(text, softAssertions)
                .clickContinue()
                .uploadImg(img)
                .verifyUploadedImgOnSolutionFld(softAssertions)
                .clickContinue()
                .fillAllFldsOnFourthStep(text)
                .verifyAchievedResultMatchExamplePattern(text, softAssertions)
                .verifyKeyFiguresMatchExamplePattern(text, softAssertions)
                .clickDiscard()
                .fillAllFldsOnFourthStep("some new text")
                .verifyAll(softAssertions);
    }

    @Test(description = "Creates case study with no input values")
    public void createStudyWithoutInput(){
        caseStudies = ProjectType.AEO.getStudiesPage(projectsPage);
        caseStudies.openStudyCreator()
                .verifySaveButtonStatus(false);
    }

    @Test(description = "Creates case study only by filling 'name' field")
    public void createStudyOnlyWithName(){
        caseStudies = ProjectType.AEO.getStudiesPage(projectsPage);
        caseStudies.openStudyCreator()
                .setStudiesName("Some name")
                .verifySaveButtonStatus(true);
    }

    @Test(description = "Opens creator of case study form immediately after saving the previous one")
    public void createStudyImmediatelyAfterPrevious(){
        caseStudies = ProjectType.AEO.getStudiesPage(projectsPage);
        caseStudies.openStudyCreator()
                .setStudiesName("Some name1")
                .saveStudy()
                .openStudyCreator()
                .verifyOpenedStudyCreator(true);
    }
}
