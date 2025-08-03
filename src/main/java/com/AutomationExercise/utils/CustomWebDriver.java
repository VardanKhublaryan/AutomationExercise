package com.AutomationExercise.utils;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.AutomationExercise.utils.Configuration.REMOTE_RUN;

@Configuration
@Scope("prototype")
public class CustomWebDriver {
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static void removeDriverThreadLocal() {
        threadLocal.remove();
    }

    public void setUp() throws MalformedURLException {
//            case "chrome" -> {
        WebDriver driver;
        ChromeOptions options = getChromeOptions();
        if (REMOTE_RUN) {
            driver = new RemoteWebDriver(new URL("http://192.168.1.137:4444"), options);
            threadLocal.set(driver);
        } else driver = new ChromeDriver(options);
        threadLocal.set(driver);
//
////            case "safari" -> {
////                SafariOptions safariOptions = new SafariOptions();
////                CustomWebDriver.threadLocal.set(new SafariDriver(safariOptions));
////            }
////            case "firefox" -> {
////                final DesiredCapabilities capabilities = new DesiredCapabilities();
////                capabilities.setPlatform(Platform.WIN11);
////                capabilities.setBrowserName("firefox");
////
////                RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.137:4444"), capabilities);
////                CustomWebDriver.threadLocal.set(driver);
////            }
////            default -> CustomWebElement.printInfo("WRONG BROWSER NAME");
//        }
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

    @SneakyThrows
    @Bean
    @Scope("prototype")
    public WebDriver getDriver() {
        return threadLocal.get();
    }

}
