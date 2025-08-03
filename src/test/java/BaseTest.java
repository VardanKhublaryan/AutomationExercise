import com.AutomationExercise.SpringApp;
import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.utils.CustomWebDriver;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import static com.AutomationExercise.utils.CustomWebDriver.removeDriverThreadLocal;

@SpringBootTest(classes = SpringApp.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HomePage homePage;

    @Autowired
    private CustomWebDriver customWebDriver;

    @BeforeMethod()
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        homePage.open();
    }

    @BeforeClass
    @SneakyThrows
    public void setUp() {
        customWebDriver.setUp();
    }

    @AfterMethod()
    public void treeUp() {
        if (customWebDriver.getDriver() != null) {
            customWebDriver.getDriver().quit();
            removeDriverThreadLocal();
        }
    }
}

