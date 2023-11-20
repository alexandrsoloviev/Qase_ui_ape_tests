package tests.api;

import helpers.generators.ProjectGenerator;
import helpers.generators.SuiteGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import tests.api.pojos.request.project.CreateProjectRequest;
import tests.api.pojos.request.suite.CreateSuiteRequest;
import tests.api.pojos.response.suite.CreateSuiteResponse;
import tests.api.pojos.response.suite.DeleteSuiteResponse;
import tests.api.pojos.response.suite.Result;
import tests.api.steps.ProjectSteps;
import tests.api.steps.SuiteSteps;

import static org.assertj.core.api.Assertions.assertThat;
@Tag("API")
@Epic("API")
@Feature("SUITE")
@Owner("alexandrsoloviev")
@DisplayName("Suite API tests")
public class SuiteApiTest {
    CreateProjectRequest projectRq = ProjectGenerator.createProjectApi();

    @BeforeEach
    @DisplayName("Pre-condition : Create project")
    public void precondition(){
        ProjectSteps.createProject(projectRq);
    }

    @Test
    @DisplayName("Delete suite")
    public void suiteShouldBeDeleted(){

        CreateSuiteRequest suiteRq = SuiteGenerator.createSuiteApi();
        CreateSuiteResponse suiteRs = SuiteSteps.createSuite(suiteRq, projectRq.getCode());

        Integer suiteId = SuiteSteps.getSuiteId(projectRq.getCode());
        DeleteSuiteResponse delSuiteRs = SuiteSteps.deleteSuite(projectRq.getCode(),suiteId);

        assertThat(delSuiteRs)
                .isNotNull()
                .extracting(DeleteSuiteResponse::getResult)
                .extracting(Result::getId)
                .isEqualTo(suiteRs.getResult().getId());
    }

    @Test
    @DisplayName("Create suite")
    public void suiteShouldBeCreated(){

        CreateSuiteRequest suiteRq = SuiteGenerator.createSuiteApi();
        CreateSuiteResponse suiteRs = SuiteSteps.createSuite(suiteRq, projectRq.getCode());

        assertThat(suiteRs)
                .isNotNull()
                .extracting(CreateSuiteResponse::isStatus)
                .isEqualTo(true);
    }

    @AfterEach
    @DisplayName("Post-condition : Delete project")
    void postondition(){
        ProjectSteps.deleteProject(projectRq.getCode());
    }
}
