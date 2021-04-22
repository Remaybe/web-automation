package com.csma.api;

import java.io.*;
import com.squareup.okhttp.*;
import com.vimalselvam.graphql.GraphqlTemplate;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GraphqlUtils {

    private static final OkHttpClient client = new OkHttpClient();
    private static final String graphqlUrl = "https://csma-staging.griddynamics.net/api/graphql";
    File file = new File(System.getProperty("user.dir") + "/get-studies-list.graphql");

    private static Response prepareResponse(String graphqlPayload) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), graphqlPayload);
        Request request = new Request.Builder().url(graphqlUrl).post(body).build();
        log.info("Sets up default configs for client's http-session, forms response according to request of chosen url");
        return client.newCall(request).execute();
    }

    public static String getQueryAnswer(File file) {
        String queryAnswer = "";
        try {
            String graphqlPayload = GraphqlTemplate.parseGraphql(file, null);
            Response response = prepareResponse(graphqlPayload);
            queryAnswer = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Inserts query from file in GraphQL, executes it and returns output answer");
        return queryAnswer;
    }
}
