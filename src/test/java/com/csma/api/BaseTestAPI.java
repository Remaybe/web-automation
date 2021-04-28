package com.csma.api;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static io.github.jsonSnapshot.SnapshotMatcher.*;

public class BaseTestAPI {

    protected static final Map<String, String> COOKIES = new HashMap<String, String>(){
        {
            put("accessToken", "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJlbWF6bmV2IiwiaWF0IjoxNjE5NjE1NDk2LCJleHAiOjE2MTk2MTU1NTZ9.MpyZOYUZ4fAxDaCwHKGShiOIx8qUizguvlGr3dG1Gzng8yc1H4uJ5gNT-3eYyNUNwU3BAT6hMNxlvOzAQkcOHw2ObyiMyaYfQ6tKRILo5J5tsCPDi6XG52K_WWv2oLHxvvIVNcpXKLAh4apsB4alatbXCvN_kKc647WMgv0UX7SzLItC7qPvpw09VtvYE7YSDuuEuStHlEyifI1i_B-XFC24JDnrZFuHPs2ZvCwLYvuqDQrgMAhE0EibMtAAq4TInOwJcla4isTwcfDuVSfylgAhDCH_213SfwHj2kt6JTHdyWhK9pUMyfRh27lRK9H678vPrt-XI8ux64D4OCoSYA");
            put("refreshToken", "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJlbWF6bmV2IiwiaWF0IjoxNjE5NjE1NDk2LCJleHAiOjE2MTk2NDQyOTZ9.hxvhA5GzsBtJOr1PZAdNh3qI374lSYX2deE5ak2_uR5pSANOmkjzJyNIwKKLCBm5l8ej3Lza2XAktbai6JZGuzrooggOuNM0eee41ZwTwyEp1jvweIiuUTUu6HE7rKNOwku_yYxO7hCW7dYNBKPkpo8MBIicN6o5JlfA7jbX86Jjtg8lqBDOvJGR65xE_ckJ3DgSpD49kGzLBZTjD10uRwYUbkRjPVoiT4zpVjX_5Orxf4VAVvxj4jwe2TpA7-PAPHW1zxeTgUZrJLrjbyikHwl70Y529ZDCqFUbCoxF9klaTjRPl8pLQ2644ebfqsSR2RzyR7cHllQ_EcfNcrzBYw");
        }
    };

    protected static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://csma-staging.griddynamics.net/api/graphql")
                    .setContentType("application/json; charset=UTF-8")
                    .addCookies(COOKIES).build();

    @BeforeClass
    public static void beforeAll() {
        start();
    }

    @AfterClass
    public static void afterAll() {
        validateSnapshots();
    }
}
