package tests.ui.pages.pageElements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class PageTitle {

    public SelenideElement pageTitle(String title){
        return  $(byTagAndText("h1",title));
    }

}
