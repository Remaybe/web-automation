package com.csma.api.steps;

import com.csma.api.CaseStudiesData;
import com.csma.api.utils.CompareUtils;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StudiesSteps {

    private static final String CASE_STUDIES_QUERY = "{\"query\":\"{\\n  caseStudies {\\n    progress\\n    status\\n    id\\n    name\\n  }\\n}\",\"variables\":{}}";
    private static final int SUCCESS_STATUS = 200;

    @Step("Returns specification by chosen cookies")
    public static RequestSpecification getSpecification(String currentCookie) {
        return new RequestSpecBuilder()
                .setBaseUri("https://csma-staging.griddynamics.net/api/graphql")
                .addHeader("Cookie", currentCookie)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    @Step("Returns list of 'Case Studies' data from request")
    public static List<CaseStudiesData> getCaseStudies(String currentCookie){
        return given()
                .spec(getSpecification(currentCookie))
                .body(CASE_STUDIES_QUERY)
                .expect()
                .statusCode(SUCCESS_STATUS)
                .when().post()
                .then()
                .extract().jsonPath().getList("data.caseStudies", CaseStudiesData.class);
    }

    @Step("Gets list of values from request by chosen path")
    public static List<String> getListOfValuesByPath(String path, String currentCookie) {
        return given()
                .spec(getSpecification(currentCookie))
                .body(CASE_STUDIES_QUERY)
                .expect()
                .statusCode(SUCCESS_STATUS)
                .when().post()
                .then()
                .extract().jsonPath().getList(path);
    }

    @Step("Verifies if any of 'Case Studies' hasn't got a name")
    public static void verifyEmptyNames(List<CaseStudiesData> studies){
        assertThat(CompareUtils.hasEmptyNames(studies))
                .as("'Case Study' without a name couldn't exist")
                .isFalse();
    }

    @Step("Verifies if 'ID' fields have unique values")
    public static void verifyUniqueIdentifiers(List<CaseStudiesData> studies){
        assertThat(CompareUtils.hasDuplicateIdentifiers(studies))
                .as("Identifiers should be unique")
                .isFalse();
    }

    @Step("Verifies if list of elements contains only chosen value")
    public static void verifyMappingElementsToOrderedValue(String value, List<String> elements){
        assertThat(CompareUtils.containsOnlyValue(value, elements))
                .as("Projects with no progress should contain 'DRAFT' status")
                .isTrue();
    }
}
