package tests.ui;

import helpers.generators.SuiteGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.api.pojos.request.suite.CreateSuiteRequest;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
@Tag("UI")
@Epic("Suite")
@Feature("Suite")
@Owner("alexandrsoloviev")
@DisplayName("Suite UI tests")
public class SuiteTest extends TestBase {

    @BeforeEach
    void openProjectsPage() {
        step("Authorize in app", this::authorizeInApp);
    }

    @Test
    @DisplayName("Create suite")
    public void suiteShouldBeCreated() {

        CreateSuiteRequest suite = SuiteGenerator.createSuiteUI();

        step("Pre-condition : create project", () -> projectGenerator.createProject());

        step("Open project", () -> open("/project/" + projectGenerator.getCreteProjectRq().getCode()));

        step("Create suite", () -> {
            suitePage.clickCreateNewSuiteButton()
                    .create(suite);
        });

        step("Validate result", () -> {
            suitePage.checkTheSuiteIsCreated(suite.getTitle());
        });

        step("Post-condition : delete project", () -> {
            projectGenerator.deleteProject(projectGenerator.getCreteProjectRq().getCode());
        });
    }

    @Test
    @DisplayName("Delete suite")
    public void suiteShouldBeDeleted(){

        step("Pre-condition : create project and suite", () -> {
            projectGenerator.createProject();
            suiteGenerator.createSuite(projectGenerator.getCreteProjectRq().getCode());
        });

        step("Go to the created suite", () -> {
            Integer suiteID = suiteGenerator.getSuiteID(projectGenerator.getCreteProjectRq().getCode());
            suitePage.openSuite(projectGenerator.getCreteProjectRq().getCode(), suiteID);
        });

        step("Delete suite", () -> {
            suitePage.deleteSuite();
        });

        step("Validate result", () -> {
            suitePage.checkThatTheSuitesHasBeenDeleted();
        });

        step("Post-condition : delete project", () -> {
            projectGenerator.deleteProject(projectGenerator.getCreteProjectRq().getCode());
        });
    }
}
