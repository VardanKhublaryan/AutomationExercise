package com.AutomationExercise.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

import static com.AutomationExercise.utils.Configuration.BROWSER_NAME;

public class CustomWebDriver {
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static void removeDriverThreadLocal() {
        threadLocal.remove();
    }

    public static WebDriver getDriver() {

        return Objects.requireNonNull(threadLocal.get());
    }

    public void setDriver() {
        switch (BROWSER_NAME) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                threadLocal.set(new ChromeDriver(chromeOptions));
            }
            case "edge" -> {
                System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("start-maximized");
                threadLocal.set(new EdgeDriver(edgeOptions));
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                threadLocal.set(new FirefoxDriver());
            }
            default -> CustomWebElement.printInfo("WRONG BROWSER NAME");
        }
    }
}
