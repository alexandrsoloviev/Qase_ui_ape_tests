package tests;

import com.codeborne.selenide.Configuration;
import config.App;
import config.Project;

import drvers.UIDriver;
import helpers.generators.ProjectGenerator;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import tests.api.pojos.request.project.CreateProjectRequest;
import tests.api.steps.ProjectSteps;
import tests.ui.pages.LoginPage;
import tests.ui.pages.ProjectsPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attachments.*;

public class TestBase {
   protected LoginPage loginPage = new LoginPage();
   protected ProjectsPage projectsPage = new ProjectsPage();
   protected   ProjectGenerator projectGenerator = new ProjectGenerator();


    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        selectDriver();
    }

    private static void selectDriver() {
        switch (Project.config.runIn()) {
            case "browser_selenoid":
            case "browser_local":
                UIDriver.configure();
                break;

        }
    }


    public  void authorizeInApp(){
        open("");
        $("[name=\"email\"]").setValue(App.config.login());
        $("[name=\"password\"]").setValue(App.config.password()).submit();
    }

    @AfterEach
    @Step("Save artifacts and close webdriver")
    public void afterEach() {
        screenshotAs("Last screenshot");
        pageSource();
        attachEnvDependingTestArtifacts();
        closeWebDriver();
    }


    private void attachEnvDependingTestArtifacts() {
        String sessionId = getSessionId();
        switch (Project.config.runIn()) {
            case "browser_selenoid":
                videoSelenoid(sessionId);
            case "browser_local":
                if (!Project.config.browser().equals("firefox")) {
                    browserConsoleLogs();
                }
                break;
        }
    }
}