package com.csma.api;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GraphQLTest extends BaseTestAPI {

    @Test(description = "Verifies if projects with '0' progress line have 'DRAFT' status")
    public void checksProjectStatusDueToProgress(){
        String listOfCaseStudiesQuery = "{\"query\":\"{\\n  caseStudies {\\n    progress\\n    status\\n    id\\n    name\\n  }\\n}\",\"variables\":{}}";

        given()
                .spec(specification)
                .body(listOfCaseStudiesQuery)
                .expect()
                .statusCode(200)
                .and()
                .body("data.caseStudies.find{it.progress==0}.status", equalTo("DRAFT"))
                .when().post();
    }

    @Test(description = "Verifies if 'ID' fields have unique values")
    public void checksUniqueValuesForID(){
        String listOfCaseStudiesQuery = "{\"query\":\"{\\n  caseStudies {\\n    progress\\n    status\\n    id\\n    name\\n  }\\n}\",\"variables\":{}}";

        List<CaseStudiesData> studies = given()
                .spec(specification)
                .body(listOfCaseStudiesQuery)
                .expect()
                .statusCode(200)
                .when().post()
                .then()
                .extract().jsonPath().getList("data.caseStudies", CaseStudiesData.class);

        List<Integer> identifiers = new ArrayList<>();
        studies.forEach(value -> identifiers.add(value.getId()));
        assertThat(hasDuplicate(identifiers))
                .as("Identifiers should be unique")
                .isFalse();
    }
}
