package com.csma.api;
import org.junit.Test;

import static io.github.jsonSnapshot.SnapshotMatcher.*;
import static io.restassured.RestAssured.given;

public class GraphQLTest extends BaseTestAPI {

    @Test
    public void getListOfCaseStudies(){
        String body = "{\"query\":\"{\\n  caseStudies {\\n    id\\n    name\\n  }\\n}\",\"variables\":{}}";

        GraphQLGetResponse graphResponse = given()
                .spec(REQ_SPEC)
                .body(body)
                .when().post()
                .as(GraphQLGetResponse.class);

        expect(graphResponse).toMatchSnapshot();
    }
}
