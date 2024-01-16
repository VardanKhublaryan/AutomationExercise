import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.utils.CustomWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.AutomationExercise.utils.CustomWebDriver.getDriver;
import static com.AutomationExercise.utils.CustomWebDriver.removeDriverThreadLocal;

public class BaseTest {
    @BeforeMethod()
    public void setUp() {
        new CustomWebDriver().setDriver();
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
    }

    @AfterMethod()
    public void treeUp() {
        if (getDriver() != null) {
            getDriver().quit();
            removeDriverThreadLocal();
        }
    }
}

