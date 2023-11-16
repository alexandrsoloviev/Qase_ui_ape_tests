package tests.api.specs;

import config.App;
import helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class Specs {
    public static final RequestSpecification REQ_SPEC = with()

            .baseUri(App.config.baseUri())
            .filter(CustomAllureListener.withCustomTemplates())
            .log().uri()
            .contentType(JSON)
            .header("Token", App.config.token());

    public static final ResponseSpecification RES_SPEC = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(200)
            .build();
}
