package tests.ui.pages.pageElements;


import static com.codeborne.selenide.Selenide.$x;

public class Input {
    public void setValueInInput(String placeholder, String value) {
        $x("//input[@placeholder = '" + placeholder + "']").setValue(value);
    }

    public void clearInput(String placeholder) {
        $x("//input[@placeholder = '" + placeholder + "']").clear();
    }
}
