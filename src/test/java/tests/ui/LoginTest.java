package tests.ui;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("UI")
@Epic("Login")
@Feature("Login")
@Owner("alexandrsoloviev")
public class LoginTest extends TestBase {


    @BeforeEach
    void openLoginPage() {
        open("");
    }

    @Test
    @DisplayName("Log in with valid data")
    void userShouldBeAuthWithValidData() {

        step("Input login, password and click Sign In button", () -> {
            loginPage.setValueInEmailAndPassword(App.config.login(), App.config.password())
                    .clickSingInButton();
        });
        step("Expected result: projects page is open", () -> {
            loginPage.userAuthorized();
        });
    }


    @ParameterizedTest
    @DisplayName("Log in with invalid data")
    @CsvSource({
            "some@mail.com, somePassword",
            "wrongemail, password2",
            "some@mail.com, 1"
    })
    void alertWithErrorMessageShouldBeVisibleIfUserInputWrongDataInAuthForm(String email, String password) {
        step("Input email and password}into auth form and click Log In button", () -> {
            loginPage.setValueInEmailAndPassword(email, password).
                    clickSingInButton();
        });
        step("Expected result: alert with error message is visible", () -> {
            loginPage.alertWithErrorMessage();
        });
    }

    @ParameterizedTest
    @DisplayName("Email and Password input is required")
    @CsvSource({
            "some@mail.com, ",
            ", password2"
    })
    void emailAndPasswordIsRequired(String email, String password){
        step("Leave the email/password field empty.", ()->{
            loginPage.setValueInEmailAndPassword(email,password).
                    clickSingInButton();
        });
        step("The email/password field must be required",()->{
            loginPage.errorMessageFieldIsRequired();
        });
    }




}
