package com.csma.api;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.github.jsonSnapshot.SnapshotMatcher.*;
import static io.restassured.RestAssured.given;

public class GraphQLTest {
    @BeforeClass
    public static void beforeAll() {
        start();
    }

    @Test
    public void testGraphQLGetRequestWithValidDetails(){
        Map<String, String> headers = new HashMap<String, String>() {
            {
                put("Cookie", "accessToken=eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJlbWF6bmV2IiwiaWF0IjoxNjE5NjE1NDk2LCJleHAiOjE2MTk2MTU1NTZ9.MpyZOYUZ4fAxDaCwHKGShiOIx8qUizguvlGr3dG1Gzng8yc1H4uJ5gNT-3eYyNUNwU3BAT6hMNxlvOzAQkcOHw2ObyiMyaYfQ6tKRILo5J5tsCPDi6XG52K_WWv2oLHxvvIVNcpXKLAh4apsB4alatbXCvN_kKc647WMgv0UX7SzLItC7qPvpw09VtvYE7YSDuuEuStHlEyifI1i_B-XFC24JDnrZFuHPs2ZvCwLYvuqDQrgMAhE0EibMtAAq4TInOwJcla4isTwcfDuVSfylgAhDCH_213SfwHj2kt6JTHdyWhK9pUMyfRh27lRK9H678vPrt-XI8ux64D4OCoSYA; refreshToken=eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJlbWF6bmV2IiwiaWF0IjoxNjE5NjE1NDk2LCJleHAiOjE2MTk2NDQyOTZ9.hxvhA5GzsBtJOr1PZAdNh3qI374lSYX2deE5ak2_uR5pSANOmkjzJyNIwKKLCBm5l8ej3Lza2XAktbai6JZGuzrooggOuNM0eee41ZwTwyEp1jvweIiuUTUu6HE7rKNOwku_yYxO7hCW7dYNBKPkpo8MBIicN6o5JlfA7jbX86Jjtg8lqBDOvJGR65xE_ckJ3DgSpD49kGzLBZTjD10uRwYUbkRjPVoiT4zpVjX_5Orxf4VAVvxj4jwe2TpA7-PAPHW1zxeTgUZrJLrjbyikHwl70Y529ZDCqFUbCoxF9klaTjRPl8pLQ2644ebfqsSR2RzyR7cHllQ_EcfNcrzBYw");
            }
        };

        String body = "{\"query\":\"{\\n  caseStudies {\\n    id\\n    name\\n  }\\n}\",\"variables\":{}}";

        GraphQLGetResponse graphResponse = given()
                .baseUri("https://csma-staging.griddynamics.net/api/graphql")
                .contentType("application/json; charset=UTF-8")
                .headers(headers)
                .body(body)
                .expect()
                .when().get()
                .as(GraphQLGetResponse.class);

        expect(graphResponse).toMatchSnapshot();
    }

    @AfterClass
    public static void afterAll() {
        validateSnapshots();
    }
}
