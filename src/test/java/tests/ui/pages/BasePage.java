package tests.ui.pages;

import com.codeborne.selenide.Selenide;
import config.App;
import config.AppConfig;
import helpers.generators.ProjectGenerator;
import tests.api.pojos.request.project.CreateProjectRequest;
import tests.api.steps.ProjectSteps;
import tests.ui.pages.pageElements.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    Input input = new Input();
    PageTitle pageTitle = new PageTitle();
    Button button = new Button();
    TextArea textArea = new TextArea();
    RadioButton radioButton = new RadioButton();





}
