package tests.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import tests.api.pojos.request.project.CreateProjectRequest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;



public class ProjectsPage extends BasePage{

    static final SelenideElement PROJECT_TITLE_LOCATOR = $x("//div[@id='application-content']//h1");
    static final SelenideElement REMOVE_BUTTON = $x("//button[text()='Remove']");
     String projectInProjectsList = "//tr/ancestor::tbody//div/div/a[text()='%s']";
     String burgerMenuButton = "//a[text()='%s']/ancestor::tr//td//span/..";


    public ProjectsPage openProjectPage(){
        open("/projects");
        return this;
    }

    public ProjectsPage checkThatTheProjectHasBeenCreated(String projectCode){
        PROJECT_TITLE_LOCATOR.shouldBe(Condition.visible).shouldBe(text(projectCode));
        return this;
    }

    public ProjectsPage checkThatTheProjectHasBeenFound(String projectTitle){
        $x(format(projectInProjectsList,projectTitle)).shouldBe(visible);
        return this;
    }

    public ProjectsPage checkThatTheProjectHasBeenNotFound(String projectTitle){
        $x(format(projectInProjectsList,projectTitle)).shouldNotBe(visible);
        return this;
    }

    public ProjectsPage clickCreateNewProjectButton(){
        button.clickButton("Create new project");
        return this;
    }

    public ProjectsPage inputValueIntoSearchInput(String value){
        input.setValueInInput("Search for projects",value);
        return this;
    }

    public ProjectsPage clickCreateProjectButton(){
        button.clickButton("Create project");
        return this;
    }

    public ProjectsPage create(CreateProjectRequest project){
        input.setValueInInput("For example: Web Application", project.getTitle());
        input.setValueInInput("For example: WA", project.getCode());
        textArea.setValueIntoTextArea("Write a few sentences about your project", project.getDescription());

        return clickCreateProjectButton();
    }

    public ProjectsPage deleteProject(String projectTitle) {
        $x(format(burgerMenuButton, projectTitle)).click();
        REMOVE_BUTTON.shouldBe(visible).click();
        button.clickButton("Delete project");
        return this;
    }


}
