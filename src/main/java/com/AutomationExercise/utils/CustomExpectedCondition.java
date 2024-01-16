package com.AutomationExercise.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.AutomationExercise.utils.CustomWebDriver.getDriver;


public class CustomExpectedCondition {

    public static ExpectedCondition<Boolean> waitForJQueryToLoad() {
        return driver -> {
            try {
                return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };
    }


    public static ExpectedCondition<Boolean> waitForJStoLoad() {
        return driver -> ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").toString()
                .equals("complete");
    }

    public static ExpectedCondition<Boolean> textToBeVisibility(WebElement element) {
        return driver -> {
            try {
                return !element.getText().isEmpty();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    public static ExpectedCondition<Boolean> URLToBe(String url) {
        return driver -> {
            try {
                return getDriver().getCurrentUrl().equals(url);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    public static ExpectedCondition<Cookie> cookieIsDisplayed(String cookiesName) {
        return driver -> {
            try {
                return getDriver().manage().getCookieNamed(cookiesName);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        };
    }
}