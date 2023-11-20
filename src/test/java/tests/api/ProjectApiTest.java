package tests.api;

import helpers.generators.ProjectGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.pojos.request.project.CreateProjectRequest;
import tests.api.pojos.response.project.CreateProjectResponse;
import tests.api.pojos.response.project.EntitiesItem;
import tests.api.pojos.response.project.Result;
import tests.api.steps.ProjectSteps;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("API")
@Epic("API")
@Feature("PROJECT")
@Owner("alexandrsoloviev")
public class ProjectApiTest {

    @Test
    @DisplayName("Create project")
    public void projectShouldBeCreated() {
        CreateProjectRequest creteProjectRq = ProjectGenerator.createProjectApi();
        CreateProjectResponse createProjectRs = ProjectSteps.createProject(creteProjectRq);

        assertThat(createProjectRs)
                .isNotNull()
                .extracting(CreateProjectResponse::getResult)
                .extracting(Result::getCode)
                .isEqualTo(creteProjectRq.getCode());

        ProjectSteps.deleteProject(creteProjectRq.getCode());
    }

    @Test
    @DisplayName("Delete project")
    public void projectShouldBeDeleted() {
        CreateProjectRequest creteProjectRq = ProjectGenerator.createProjectApi();
        ProjectSteps.createProject(creteProjectRq);

        ProjectSteps.deleteProject(creteProjectRq.getCode());

        List<EntitiesItem> listProjectsCode = ProjectSteps.getListProjectEntities();

        assertThat(listProjectsCode)
                .extracting(EntitiesItem::getCode)
                .doesNotContain(creteProjectRq.getCode());

    }
}
