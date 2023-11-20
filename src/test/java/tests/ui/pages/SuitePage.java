package tests.ui.pages;


import com.codeborne.selenide.SelenideElement;
import tests.api.pojos.request.suite.CreateSuiteRequest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class SuitePage extends BasePage {

    public String createSuiteInputs = "//label[text()='%s']/../..//p";
    public String suiteTitle = "//h3/span[text()='%s']";
    public static final SelenideElement REMOVE_SUITE_BUTTON = $x("//i[@class = 'far fa-trash']");
    public static final SelenideElement ALL_SUITES_ARE_DELETED = $x("//i[@class = 'far fa-trash']");


    public SuitePage openSuite(String projectCode, Integer suiteID) {
       open("/project/"  + projectCode +"?suite="+suiteID);
       return this;
    }

    public SuitePage deleteSuite(){
        REMOVE_SUITE_BUTTON.shouldBe(visible).click();
        button.clickButton("Delete");
        return this;
    }

    public SuitePage create(CreateSuiteRequest suite){

        input.setValueInInput("For example: Web Application", suite.getTitle());
        $x(format(createSuiteInputs, "Description")).setValue(suite.getDescription());
        $x(format(createSuiteInputs, "Preconditions")).setValue(suite.getPreconditions());
        return clickCreateButton();
    }

    public SuitePage checkTheSuiteIsCreated(String suiteName){
        $x(format(suiteTitle, suiteName)).shouldBe(visible);
        return this;
    }

    public SuitePage clickCreateButton(){
        button.clickButton("Create");
        return this;
    }

    public SuitePage checkThatTheSuitesHasBeenDeleted(){
        ALL_SUITES_ARE_DELETED.shouldBe(visible);
        return this;
    }

    public SuitePage clickCreateNewSuiteButton(){
        button.clickButton("Create new suite");
        return this;
    }
}
