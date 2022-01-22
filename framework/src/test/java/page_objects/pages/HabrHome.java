package page_objects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static config.Config.config;

import static com.codeborne.selenide.Selenide.$;

public class HabrHome extends BasePage{
    private static Logger logger = LoggerFactory.getLogger(BasePage.class);

    private SelenideElement header = $(".tm-header");
    private SelenideElement allTracksBtn = header.find(By.linkText("Все потоки"));
    private SelenideElement devTrackBtn = $(By.partialLinkText("Разработка"));
    private SelenideElement trackSectionNameText = $(".tm-section-name__text");

    public SelenideElement getHeader() {
        return header;
    }

    public SelenideElement getAllTracksBtn() {
        return allTracksBtn;
    }

    public SelenideElement getDevTrackBtn() {
        return devTrackBtn;
    }

    public SelenideElement getTrackSectionNameText() {
        return trackSectionNameText;
    }

    public HabrHome open() throws Exception {
        Selenide.open(config().get("UI_TESTS_BASE_URL"));
        return new HabrHome();
    }

    @Step("Click on All tracks link")
    public HabrHome openAllTracks(){
        click(allTracksBtn);
        return this;
    }

    @Step("Assert that Header element is displayed")
    public void checkHomePageOpened(){
        Assert.assertTrue(header.isDisplayed());
    }

    @Step("Assert that Development label is visible")
    public void checkDevTrackPageOpened(){
        Assert.assertTrue(trackSectionNameText.getText().contains("Разработка"));
    }
}
