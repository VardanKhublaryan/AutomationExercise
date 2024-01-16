package com.AutomationExercise.utils;

import org.openqa.selenium.JavascriptExecutor;

import static com.AutomationExercise.utils.CustomWebDriver.getDriver;

public class JavascriptHelper {
    public static void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static long getScrollPosition() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (long) js.executeScript("return window.pageYOffset;");
    }
}
