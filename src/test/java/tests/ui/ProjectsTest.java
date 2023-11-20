package tests.ui;

import helpers.generators.ProjectGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.api.pojos.request.project.CreateProjectRequest;


import static io.qameta.allure.Allure.step;


@Tag("UI")
@Epic("Project")
@Feature("Project")
@Owner("alexandrsoloviev")
public class ProjectsTest extends TestBase {

    @BeforeEach
    void openProjectsPage() {
        step("Authorize in app", this::authorizeInApp);
    }

    @Test
    @DisplayName("Create project")
    public void projectShouldBeCreated() {

        CreateProjectRequest project = ProjectGenerator.createProjectUI();

        step("Create project", () -> {
            projectsPage.clickCreateNewProjectButton()
                    .create(project);
        });

        step("Check that the project has been created", () -> {
            projectsPage.checkThatTheProjectHasBeenCreated(project.getCode());
        });

        step("Post-condition : delete project", () -> {
            projectGenerator.deleteProject(project.getCode());
        });

    }

    @Test
    @DisplayName("Search project")
    public void searchProjectInProjectsList() {

        step("Pre-condition : create project", () -> {
            projectGenerator.createProject();
        });

        step("Input project title into the search and check that it appears in the search output", () -> {
            projectsPage.inputValueIntoSearchInput(projectGenerator.getCreteProjectRq().getTitle())
                    .checkThatTheProjectHasBeenFound(projectGenerator.getCreteProjectRq().getTitle());
        });

        step("Post-condition : delete project", () -> {
            projectGenerator.deleteProject(projectGenerator.getCreteProjectRq().getCode());
        });
    }

    @Test
    @DisplayName("Delete project")
    public void projectShouldBeDeleted() {

        step("Pre-condition : create project", () -> {
            projectGenerator.createProject();
        });

        step("Delete project", () -> {
            projectsPage.openProjectPage();
            projectsPage.deleteProject(projectGenerator.getCreteProjectRq().getTitle());
        });

        step("Validate result", () -> {
            projectsPage.inputValueIntoSearchInput(projectGenerator.getCreteProjectRq().getTitle())
                    .checkThatTheProjectHasBeenNotFound(projectGenerator.getCreteProjectRq().getTitle());
        });
    }

}
