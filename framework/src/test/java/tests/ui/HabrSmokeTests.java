package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page_objects.pages.*;

public class HabrSmokeTests extends BaseTest{
    private static Logger logger = LoggerFactory.getLogger(HabrSmokeTests.class);

    @Test
    @Link("https://google.com")
    @Issue("X2-48293")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Smoke test")
    @Feature("Landing")
    public void checkLandingPageOpenedSuccessfully(){
        try {
            //Default Logging
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logger.info("--------- \"{}\" test Start -----------", methodName);

            //Test steps
            new HabrHome().open().checkHomePageOpened();
            logger.info("LANDING PAGE OPENED");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    @Link("https://google.com")
    @Issue("X2-48292")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Smoke test")
    @Feature("Navigation bar")
    public void checkDevTrackPageOpenedAfterClickOnNavBar(){
        try {
            //Default Logging
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logger.info("--------- \"{}\" test Start -----------", methodName);

            //Test steps
            HabrHome habrHome = new HabrHome().open();
            habrHome.click(habrHome.getDevTrackBtn());
            habrHome.checkDevTrackPageOpened();
            logger.info("DEV TRACK PAGE OPENED");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
