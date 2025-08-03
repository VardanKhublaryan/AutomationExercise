package com.AutomationExercise.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CustomExpectedCondition {

    public  ExpectedCondition<Boolean> waitForJQueryToLoad() {
        return driver -> {
            try {
                return ((Long) ((JavascriptExecutor) CustomWebDriver.getDriver()).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };
    }

    public  ExpectedCondition<Boolean> waitForJStoLoad() {
        return driver -> ((JavascriptExecutor) CustomWebDriver.getDriver()).executeScript("return document.readyState").toString()
                .equals("complete");
    }

    public  ExpectedCondition<Boolean> textToBeVisibility(WebElement element) {
        return driver -> {
            try {
                return !element.getText().isEmpty();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    public  ExpectedCondition<Boolean> URLToBe(String url) {
        return driver -> {
            try {
                return CustomWebDriver.getDriver().getCurrentUrl().equals(url);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    public  ExpectedCondition<Cookie> cookieIsDisplayed(String cookiesName) {
        return driver -> {
            try {
                return CustomWebDriver.getDriver().manage().getCookieNamed(cookiesName);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        };
    }
}