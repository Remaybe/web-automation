package com.csma.api;

import com.csma.api.steps.StudiesSteps;
import org.testng.annotations.Test;

import java.util.List;

public class GraphQLTest extends BaseTestAPI {

    @Test(description = "Checks if projects with '0' progress line have 'DRAFT' status")
    public void checksProjectStatusDueToProgress(){
        List<CaseStudiesData> studies = StudiesSteps.getCaseStudies();
        StudiesSteps.verifyMappingElementsToOrderedValue(studies);
    }

    @Test(description = "Checks for unique values of identifiers")
    public void checksUniqueValuesForID(){
        List<CaseStudiesData> studies = StudiesSteps.getCaseStudies();
        StudiesSteps.verifyUniqueIdentifiers(studies);
    }

    @Test(description = "Checks 'Case Studies' containing of name")
    public void checksForEmptyName(){
        List<CaseStudiesData> studies = StudiesSteps.getCaseStudies();
        StudiesSteps.verifyEmptyNames(studies);
    }

    @Test(description = "Checks 'progress' field in 'Case Studies' data")
    public void checksProgress(){
        List<CaseStudiesData> studies = StudiesSteps.getCaseStudies();
        StudiesSteps.verifyValidProgressValues(studies);
    }
}
