package com.csma.api.steps;

import com.csma.api.CaseStudiesData;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudiesSteps {

    private static final String CASE_STUDIES_QUERY = "{\"query\":\"{\\n  caseStudies {\\n    progress\\n    status\\n    id\\n    name\\n  }\\n}\",\"variables\":{}}";
    private static final int SUCCESS_STATUS = 200;
    private static final long NO_VALUES_IN_LIST = 0L;
    private static final int MIN_PROGRESS_VALUE = 0;
    private static final int MAX_PROGRESS_VALUE = 3;
    private static RequestSpecification reqSpec;
    private static final String BASE_URI = "https://csma-staging.griddynamics.net/api/graphql";
    private static final String COOKIES_HEADER = "Cookie";

    public StudiesSteps(String currentCookie) {
        this.reqSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader(COOKIES_HEADER, currentCookie)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Step("Returns list of 'Case Studies' data from request")
    public static List<CaseStudiesData> getCaseStudies(){
        return given()
                .spec(reqSpec)
                .body(CASE_STUDIES_QUERY)
                .expect()
                .statusCode(SUCCESS_STATUS)
                .when().post()
                .then()
                .extract().jsonPath().getList("data.caseStudies", CaseStudiesData.class);
    }

    @Step("Verifies if any of 'Case Studies' hasn't got a name")
    public static void verifyEmptyNames(List<CaseStudiesData> studies){
        assertThat(studies.stream()
                .filter(val -> val.getName().equals("")).count())
                .as("'Case Study' without a name couldn't exist")
                .isEqualTo(NO_VALUES_IN_LIST);
    }

    @Step("Verifies if 'ID' fields have unique values")
    public static void verifyUniqueIdentifiers(List<CaseStudiesData> studies){
        assertThat(studies.stream().filter(value -> Collections.frequency(studies, value) > 1).count())
                .as("Identifiers should be unique")
                .isEqualTo(NO_VALUES_IN_LIST);
    }

    @Step("Verifies if progress field has valid details in it")
    public static void verifyValidProgressValues(List<CaseStudiesData> studies){
        assertThat(studies.stream()
                .filter(val -> val.getProgress() < MIN_PROGRESS_VALUE || val.getProgress() > MAX_PROGRESS_VALUE).count())
                .as("Progress shouldn't be less than '0' or bigger than '3'")
                .isEqualTo(NO_VALUES_IN_LIST);
    }

    @Step("Verifies if list of elements contains only chosen value")
    public static void verifyMappingElementsToOrderedValue(List<CaseStudiesData> studies){
        assertThat(studies.stream()
                .filter(value -> value.getProgress() == 0).filter(value -> !value.getStatus().equals("DRAFT")).count())
                .as("Projects with no progress should contain 'DRAFT' status")
                .isEqualTo(NO_VALUES_IN_LIST);
    }
}
