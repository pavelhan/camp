package tests.ui;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static config.Config.config;

import static com.codeborne.selenide.Configuration.baseUrl;

public class BaseTest {
    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        try {
            baseUrl = config().get("BASE_URL");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("This is a test");
        //setCapabilitiesByArguments();
    }

    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
