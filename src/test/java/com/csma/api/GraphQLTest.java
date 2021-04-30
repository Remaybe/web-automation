package com.csma.api;

import com.csma.api.steps.StudiesSteps;
import org.testng.annotations.Test;

import java.util.List;

public class GraphQLTest extends BaseTestAPI {

    @Test(description = "Checks if projects with '0' progress line have 'DRAFT' status")
    public void checksProjectStatusDueToProgress(){
        List<String> statusesWithNoProgress = StudiesSteps.getListOfValuesByPath("data.caseStudies.find{it.progress==0}.status", currentCookie);
        StudiesSteps.verifyMappingElementsToOrderedValue("DRAFT", statusesWithNoProgress);
    }

    @Test(description = "Checks for unique values of identifiers")
    public void checksUniqueValuesForID(){
        List<CaseStudiesData> studies = StudiesSteps.getCaseStudies(currentCookie);
        StudiesSteps.verifyUniqueIdentifiers(studies);
    }

    @Test(description = "Checks 'Case Studies' containing of name")
    public void checksForEmptyName(){
        List<CaseStudiesData> studies = StudiesSteps.getCaseStudies(currentCookie);
        StudiesSteps.verifyEmptyNames(studies);
    }
}
