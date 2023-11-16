package tests.api.steps;

import io.qameta.allure.Step;
import tests.api.pojos.request.suite.CreateSuiteRequest;
import tests.api.pojos.response.suite.CreateSuiteResponse;
import tests.api.pojos.response.suite.DeleteSuiteResponse;

import static io.restassured.RestAssured.given;
import static tests.api.specs.Specs.REQ_SPEC;
import static tests.api.specs.Specs.RES_SPEC;

public class SuiteSteps {
    static String path = "/suite";

    @Step("Create suite with random data")
    public static CreateSuiteResponse createSuite(CreateSuiteRequest createSuiteRq, String projectCode) {
        return given().spec(REQ_SPEC)
                .body(createSuiteRq)
                .post(path + "/" + projectCode)
                .then().spec(RES_SPEC)
                .extract().as(CreateSuiteResponse.class);
    }

    @Step("Get suite id")
    public static Integer getSuiteId(String projectCode) {
        return given().spec(REQ_SPEC)
                .get(path + "/" + projectCode)
                .then().spec(RES_SPEC)
                .extract().jsonPath().getInt("result.entities[0].id");
    }

    @Step("Delete suite")
    public static DeleteSuiteResponse deleteSuite(String projectCode, Integer suiteId) {
        return given().spec(REQ_SPEC)
                .delete(path + "/" + projectCode + "/" + suiteId)
                .then().spec(RES_SPEC)
                .extract().as(DeleteSuiteResponse.class);
    }

}
