package helpers.generators;

import com.github.javafaker.Faker;

import tests.api.pojos.request.suite.CreateSuiteRequest;
import tests.api.steps.ProjectSteps;
import tests.api.steps.SuiteSteps;

public class SuiteGenerator {
    static Faker faker = new Faker();
    CreateSuiteRequest createSuiteRq = SuiteGenerator.createSuiteApi();

    public static CreateSuiteRequest createSuiteApi() {
        return CreateSuiteRequest.builder()
                .title(faker.beer().name())
                .description(faker.chuckNorris().fact())
                .preconditions(faker.chuckNorris().fact())
                .build();
    }

    public static CreateSuiteRequest createSuiteUI() {
        return CreateSuiteRequest.builder()
                .title(faker.funnyName().name())
                .description(faker.chuckNorris().fact())
                .preconditions(faker.chuckNorris().fact())
                .build();
    }

    public  void createSuite(String projectCode){
        SuiteSteps.createSuite(createSuiteRq, projectCode);
    }

    public Integer getSuiteID(String projectCode){
        return SuiteSteps.getSuiteId(projectCode);
    }
}
