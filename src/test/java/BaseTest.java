import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.utils.CustomWebDriver;
import com.AutomationExercise.utils.CustomWebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;

import static com.AutomationExercise.utils.CustomWebDriver.getDriver;
import static com.AutomationExercise.utils.CustomWebDriver.removeDriverThreadLocal;

public class BaseTest {

/**  When the suit runs from testng file browser is defined in the testng.xml file (@Parameters({"browser"}))
 else working @Optional("chrome") */
    @BeforeMethod()
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                CustomWebDriver.threadLocal.set(new ChromeDriver(chromeOptions));
            }
            case "safari" -> {
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                CustomWebDriver.threadLocal.set(new SafariDriver(safariOptions));
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                CustomWebDriver.threadLocal.set(new FirefoxDriver());
            }
            default -> CustomWebElement.printInfo("WRONG BROWSER NAME");
        }
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

