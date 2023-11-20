package tests.api.steps;

import io.qameta.allure.Step;
import tests.api.pojos.request.project.CreateProjectRequest;
import tests.api.pojos.response.project.CreateProjectResponse;
import tests.api.pojos.response.project.DeleteProjectResponse;
import tests.api.pojos.response.project.EntitiesItem;

import java.util.List;

import static io.restassured.RestAssured.given;
import static tests.api.specs.Specs.REQ_SPEC;
import static tests.api.specs.Specs.RES_SPEC;


public class ProjectSteps {
    static String path = "/project";

    @Step("Create project with random data")
    public static CreateProjectResponse createProject(CreateProjectRequest projectRq) {
        return given().spec(REQ_SPEC)
                .body(projectRq)
                .post(path)
                .then().spec(RES_SPEC)
                .extract().as(CreateProjectResponse.class);
    }

    @Step("Delete project")
    public static DeleteProjectResponse deleteProject(String projectCode) {
        return given().spec(REQ_SPEC)
                .delete(path + "/" + projectCode)
                .then().spec(RES_SPEC)
                .extract().as(DeleteProjectResponse.class);
    }

    @Step("Get list projects entities")
    public static List<EntitiesItem> getListProjectEntities() {
        return given()
                .spec(REQ_SPEC)
                .get(path)
                .then().spec(RES_SPEC)
                .extract().jsonPath().getList("result.entities", EntitiesItem.class);
    }

}
