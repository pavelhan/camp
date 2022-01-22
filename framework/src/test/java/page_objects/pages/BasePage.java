package page_objects.pages;

import com.codeborne.selenide.SelenideElement;

public class BasePage {
    public void click(SelenideElement element){
        element.click();
    }
}
