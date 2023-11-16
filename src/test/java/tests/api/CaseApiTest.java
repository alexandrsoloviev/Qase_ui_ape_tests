package tests.api;

import helpers.generators.CaseGenerator;
import helpers.generators.ProjectGenerator;
import helpers.generators.SuiteGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import tests.api.pojos.request.project.CreateProjectRequest;
import tests.api.pojos.request.suite.CreateSuiteRequest;
import tests.api.pojos.request.test_case.CreateCaseRequest;
import tests.api.pojos.response.test_case.CreateCaseResponse;
import tests.api.pojos.response.test_case.DeleteCaseResponse;
import tests.api.pojos.response.test_case.Result;
import tests.api.steps.CaseSteps;
import tests.api.steps.ProjectSteps;
import tests.api.steps.SuiteSteps;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("API")
@Epic("API")
@Feature("CASE")
@Owner("alexandrsoloviev")
public class CaseApiTest {
    CreateProjectRequest projectRq = ProjectGenerator.createProjectApi();
    @BeforeEach
    @DisplayName("Pre-condition : Create project")
    public void precondition(){
        ProjectSteps.createProject(projectRq);
    }

    @Test
    @DisplayName("Create case into project root")
    public void caseShouldBeCreatedIntoProjectRoot(){
        CreateCaseRequest caseRq = CaseGenerator.createCaseApi();
        CreateCaseResponse caseRs = CaseSteps.createCase(caseRq,projectRq.getCode());

        assertThat(caseRs)
                .isNotNull()
                .extracting(CreateCaseResponse::isStatus)
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Create case into suite")
    public void caseShouldBeCreatedIntoSuite(){
        CreateSuiteRequest suiteRq = SuiteGenerator.createSuiteApi();
        SuiteSteps.createSuite(suiteRq, projectRq.getCode());
        Integer suiteId = SuiteSteps.getSuiteId(projectRq.getCode());


        CreateCaseRequest caseRq = CaseGenerator.createCaseApi();
        caseRq.setSuiteId(suiteId);
        CreateCaseResponse caseRs = CaseSteps.createCase(caseRq,projectRq.getCode());


        assertThat(caseRs)
                .isNotNull()
                .extracting(CreateCaseResponse::isStatus)
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Delete case")
    public void caseShouldBeDeleted(){

        CreateCaseRequest caseRq = CaseGenerator.createCaseApi();
        CreateCaseResponse caseRs = CaseSteps.createCase(caseRq, projectRq.getCode());

        Integer caseId = CaseSteps.getCaseId(projectRq.getCode());
        DeleteCaseResponse delCaseRs = CaseSteps.deleteCase(projectRq.getCode(), caseId);

        assertThat(delCaseRs)
                .isNotNull()
                .extracting(DeleteCaseResponse::getResult)
                .extracting(Result::getId)
                .isEqualTo(caseRs.getResult().getId());
    }



    @AfterEach
    @DisplayName("Post-condition : Delete project")
    public void postondition(){
        ProjectSteps.deleteProject(projectRq.getCode());
    }
}
