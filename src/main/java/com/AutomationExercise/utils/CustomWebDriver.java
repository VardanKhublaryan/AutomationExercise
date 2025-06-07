package com.AutomationExercise.utils;

import org.openqa.selenium.WebDriver;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class CustomWebDriver {
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static void removeDriverThreadLocal() {
        threadLocal.remove();
    }
    public static WebDriver getDriver() {
        return Objects.requireNonNull(threadLocal.get());
    }
}
