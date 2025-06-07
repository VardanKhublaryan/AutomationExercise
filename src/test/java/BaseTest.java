import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.utils.CustomWebDriver;
import com.AutomationExercise.utils.CustomWebElement;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.AutomationExercise.utils.Configuration.REMOTE_RUN;
import static com.AutomationExercise.utils.CustomWebDriver.getDriver;
import static com.AutomationExercise.utils.CustomWebDriver.removeDriverThreadLocal;

public class BaseTest {

    /**
     * When the suit runs from testng file browser is defined in the testng.xml file (@Parameters({"browser"}))
     * else working @Optional("chrome")
     */
    @BeforeMethod()
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome" -> {
                WebDriver driver;
                ChromeOptions options = getChromeOptions();
                if (REMOTE_RUN) {
                    driver = new RemoteWebDriver(new URL("http://192.168.1.137:4444"), options);
                    CustomWebDriver.threadLocal.set(driver);
                } else driver = new ChromeDriver(options);
                CustomWebDriver.threadLocal.set(driver);
            }
            case "safari" -> {
                SafariOptions safariOptions = new SafariOptions();
                CustomWebDriver.threadLocal.set(new SafariDriver(safariOptions));
            }
            case "firefox" -> {
                final DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setPlatform(Platform.WIN11);
                capabilities.setBrowserName("firefox");

                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.137:4444"), capabilities);
                CustomWebDriver.threadLocal.set(driver);
            }
            default -> CustomWebElement.printInfo("WRONG BROWSER NAME");
        }
        HomePage homePage = new HomePage();
        homePage.open();
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-proxy-server");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    @AfterMethod()
    public void treeUp() {
        if (getDriver() != null) {
            getDriver().quit();
            removeDriverThreadLocal();
        }
    }
}

