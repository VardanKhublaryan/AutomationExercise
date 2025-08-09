package com.AutomationExercise.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JavascriptHelper {

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) CustomWebDriver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public long getScrollPosition() {
        JavascriptExecutor js = (JavascriptExecutor) CustomWebDriver.getDriver();
        return (long) js.executeScript("return window.pageYOffset;");
    }
}
