package tests.api.steps;

import io.qameta.allure.Step;
import tests.api.pojos.request.test_case.CreateCaseRequest;
import tests.api.pojos.response.test_case.CreateCaseResponse;
import tests.api.pojos.response.test_case.DeleteCaseResponse;

import static io.restassured.RestAssured.given;
import static tests.api.specs.Specs.REQ_SPEC;
import static tests.api.specs.Specs.RES_SPEC;

public class CaseSteps {

    static String path = "/case";

    public static Integer getCaseId(String projectCode) {
        return given().spec(REQ_SPEC)
                .get(path + "/" + projectCode)
                .then().spec(RES_SPEC)
                .extract().jsonPath().getInt("result.entities[0].id");
    }

    @Step("Create test-case")
    public static CreateCaseResponse createCase(CreateCaseRequest createCaseRq, String projectCode) {
        return given().spec(REQ_SPEC)
                .body(createCaseRq)
                .log().body()
                .post(path + "/" + projectCode)
                .then().spec(RES_SPEC)
                .extract().as(CreateCaseResponse.class);
    }

    public static DeleteCaseResponse deleteCase(String projectCode, Integer caseId) {
        return given().spec(REQ_SPEC)
                .delete(path + "/" + projectCode + "/" + caseId)
                .then().spec(RES_SPEC)
                .extract().as(DeleteCaseResponse.class);
    }
}
