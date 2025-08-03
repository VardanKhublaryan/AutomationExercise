package com.AutomationExercise.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JavascriptHelper {
    @Autowired
    private CustomWebDriver customWebDriver;

    public  void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) customWebDriver.getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public  long getScrollPosition() {
        JavascriptExecutor js = (JavascriptExecutor) customWebDriver.getDriver();
        return (long) js.executeScript("return window.pageYOffset;");
    }
}
