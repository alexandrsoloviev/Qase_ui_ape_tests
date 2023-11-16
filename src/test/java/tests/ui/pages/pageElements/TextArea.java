package tests.ui.pages.pageElements;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TextArea {
    public void setValueIntoTextArea(String placeholder, String value) {
        $x("//textarea[@placeholder = '" + placeholder + "']").setValue(value);
    }

    public void clearTextArea(String placeholder) {
        $x("//textarea[@placeholder = '" + placeholder + "']").clear();
    }
}
