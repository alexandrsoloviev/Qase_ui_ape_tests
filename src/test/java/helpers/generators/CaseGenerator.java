package helpers.generators;

import com.github.javafaker.Faker;
import tests.api.pojos.request.test_case.CreateCaseRequest;

public class CaseGenerator {
    static Faker faker = new Faker();

    public static CreateCaseRequest createCaseApi(){
        return CreateCaseRequest.builder()
                .description(faker.chuckNorris().fact())
                .preconditions(faker.chuckNorris().fact())
                .postconditions(faker.chuckNorris().fact())
                .title(faker.chuckNorris().fact())
                .severity(faker.number().numberBetween(1,2))
                .priority(faker.number().numberBetween(1,2))
                .behavior(faker.number().numberBetween(1,2))
                .type(faker.number().numberBetween(1,2))
                .layer(faker.number().numberBetween(1,2))
                .automation(faker.number().numberBetween(1,2))
                .status(faker.number().numberBetween(1,2))
                .build();
    }

}
