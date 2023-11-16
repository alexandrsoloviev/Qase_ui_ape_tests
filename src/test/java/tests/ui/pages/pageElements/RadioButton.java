package tests.ui.pages.pageElements;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class RadioButton {

    private static final String radioLocator = "//label[text() = '%s']/../..//span[text() = '%s']";

    public void clickIntoRadioButtonByName(String label, String buttonName) {
        $x(format(radioLocator,label,buttonName)).click();
    }
}
