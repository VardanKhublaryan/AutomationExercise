package com.AutomationExercise.utils;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.AutomationExercise.utils.CustomExpectedCondition.waitForJQueryToLoad;
import static com.AutomationExercise.utils.CustomExpectedCondition.waitForJStoLoad;
import static com.AutomationExercise.utils.CustomWebDriver.getDriver;

public class WaitHelper {
    public static final int TIME_OUT = 7;
    static WebDriverWait wait;

    static void wait(ExpectedCondition<Boolean> expectedCondition) throws WebDriverException {
        new WebDriverWait(getDriver(), Duration.ofSeconds(TIME_OUT)).until(expectedCondition);
    }

    public static void waitUntilJQueryIsLoaded() {
        wait(waitForJQueryToLoad());
    }

    public static void waitUntilJSisLoaded() {
        wait(waitForJStoLoad());
    }

    public static void pageToBeLoaded() {
        try {
            waitUntilJSisLoaded();
        } catch (WebDriverException e) {
            CustomListener.log(e.toString());
            throw new WebDriverException("Page is not loaded");
        }
    }

    public static void waitUntilVisibilityOfText(WebElement element) {
        try {
            wait(CustomExpectedCondition.textToBeVisibility(element));
        } catch (Exception e) {
            CustomListener.log(e.toString());
        }
    }

    public static void waitUntilVisibilityTextOfElement(WebElement element, String text) {
        try {
            wait(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            CustomListener.log(e.toString());
        }
    }

    public static void elementToBeVisible(WebElement element) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(TIME_OUT)).until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException e) {
            CustomListener.log(e.toString());
            throw new WebDriverException("Element by " + element + " selector should not be visible");
        }
    }

    public static void waitUntilElementClickable(WebElement element) {
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIME_OUT));
        elementToBeVisible(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementIsDisplayed(WebElement element) {
        new WebDriverWait(getDriver(), Duration.ofSeconds(TIME_OUT)).until(ExpectedConditions.visibilityOf(element));
    }

}
