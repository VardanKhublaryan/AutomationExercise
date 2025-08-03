package com.AutomationExercise.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Scope("prototype")
public class WaitHelper {
    public static final int TIME_OUT = 7;
    private WebDriverWait wait;

    @Autowired
    private CustomWebDriver customWebDriver;

    @Autowired
    private CustomExpectedCondition expectedCondition;

     void wait(ExpectedCondition<Boolean> expectedCondition) throws WebDriverException {
        new WebDriverWait(customWebDriver.getDriver(), Duration.ofSeconds(TIME_OUT)).until(expectedCondition);
    }

    public  void waitUntilJQueryIsLoaded() {
        wait(expectedCondition.waitForJQueryToLoad());
    }

    public void waitUntilJSisLoaded() {
        wait(expectedCondition.waitForJStoLoad());
    }

    public void pageToBeLoaded() {
        try {
            waitUntilJSisLoaded();
        } catch (WebDriverException e) {
            CustomListener.log(e.toString());
            throw new WebDriverException("Page is not loaded");
        }
    }

    public void waitUntilVisibilityOfText(WebElement element) {
        try {
            wait(expectedCondition.textToBeVisibility(element));
        } catch (Exception e) {
            CustomListener.log(e.toString());
        }
    }

    public void waitUntilVisibilityTextOfElement(WebElement element, String text) {
        try {
            wait(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            CustomListener.log(e.toString());
        }
    }

    public void elementToBeVisible(WebElement element) {
        try {
            new WebDriverWait(customWebDriver.getDriver(), Duration.ofSeconds(TIME_OUT)).until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException e) {
            CustomListener.log(e.toString());
            throw new WebDriverException("Element by " + element + " selector should not be visible");
        }
    }

    public void waitUntilElementClickable(WebElement element) {
        wait = new WebDriverWait(customWebDriver.getDriver(), Duration.ofSeconds(TIME_OUT));
        elementToBeVisible(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementIsDisplayed(WebElement element) {
        new WebDriverWait(customWebDriver.getDriver(), Duration.ofSeconds(TIME_OUT)).until(ExpectedConditions.visibilityOf(element));
    }

}
