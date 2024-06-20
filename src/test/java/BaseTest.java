import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.utils.CustomWebDriver;
import com.AutomationExercise.utils.CustomWebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import static com.AutomationExercise.utils.CustomWebDriver.getDriver;
import static com.AutomationExercise.utils.CustomWebDriver.removeDriverThreadLocal;

public class BaseTest {

/**  When the suit runs from testng file browser is defined in the testng.xml file (@Parameters({"browser"}))
 else working @Optional("chrome") */
    @BeforeMethod()
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome" -> {
//                WebDriverManager.chromedriver().setup();
//                WebDriverManager.chromedriver().clearDriverCache().setup();
                final DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setPlatform(Platform.WIN11);
                capabilities.setBrowserName("chrome");
                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.137:4444"),capabilities);
                CustomWebDriver.threadLocal.set(driver);
            }
            case "safari" -> {
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                CustomWebDriver.threadLocal.set(new SafariDriver(safariOptions));
            }
            case "firefox" -> {
                final DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setPlatform(Platform.WIN11);
                capabilities.setBrowserName("firefox");

                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.137:4444"),capabilities);
                CustomWebDriver.threadLocal.set(driver);
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

