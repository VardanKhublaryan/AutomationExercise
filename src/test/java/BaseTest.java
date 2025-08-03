import com.AutomationExercise.SpringApp;
import com.AutomationExercise.config.DriverInitializer;
import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.utils.CustomWebDriver;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest(classes = SpringApp.class)
@TestExecutionListeners(
        listeners = DriverInitializer.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HomePage homePage;

    @Autowired
    private CustomWebDriver customWebDriver;

    @SneakyThrows
    @BeforeClass()
    public void setUp() {
        CustomWebDriver.setUp();
        homePage.open();
    }

    @AfterClass()
    public void treeUp() {
        if (CustomWebDriver.getDriver() != null) {
            CustomWebDriver.getDriver().quit();
            customWebDriver.removeDriverThreadLocal();
        }
    }
}

