package helpers.generators;

import com.github.javafaker.Faker;
import lombok.Data;
import tests.api.pojos.request.project.CreateProjectRequest;
import tests.api.steps.ProjectSteps;

@Data
public class ProjectGenerator {

    static Faker faker = new Faker();
    public  CreateProjectRequest creteProjectRq = ProjectGenerator.createProjectApi();


    public static CreateProjectRequest createProjectApi() {
        return CreateProjectRequest.builder()
                .title(faker.name().firstName())
                .code(faker.name().firstName().toUpperCase())
                .description(faker.chuckNorris().fact())
                .access("all")
                .build();
    }

    public static CreateProjectRequest createProjectUI() {
        return CreateProjectRequest.builder()
                .title(faker.name().firstName())
                .code(faker.name().firstName().toUpperCase())
                .description(faker.chuckNorris().fact())
                .group("Public")
                .access("Add all members to this project")
                .build();
    }
    public  void createProject(){
        ProjectSteps.createProject(creteProjectRq);
    }

    public  void deleteProject(String projectCode){
        ProjectSteps.deleteProject(projectCode);
    }
}

