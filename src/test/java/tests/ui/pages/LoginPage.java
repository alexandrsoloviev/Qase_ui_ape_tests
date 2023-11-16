package tests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
public class LoginPage extends BasePage {

    final SelenideElement SIGN_IN_BUTTON = $x("//button/span");
    final SelenideElement ALERT_WITH_ERROR_MESSAGE = $x("//div[@role='alert']/span");
    final SelenideElement ERROR_MESSAGE = $x("//small[text() = 'This field is required']");




    public LoginPage setValueInEmailAndPassword(String email, String password) {
        input.setValueInInput("Work email", email);
        input.setValueInInput("Password", password);
        return this;
    }

    public LoginPage clickSingInButton(){
        SIGN_IN_BUTTON.click();
        return this;
    }

    public  LoginPage userAuthorized(){
        pageTitle.pageTitle("Projects").shouldBe(visible);
        return this;
    }

    public LoginPage alertWithErrorMessage(){
        ALERT_WITH_ERROR_MESSAGE.shouldBe(visible);
        return this;
    }

    public LoginPage errorMessageFieldIsRequired(){
        ERROR_MESSAGE.shouldBe(visible);
        return this;
    }





}
