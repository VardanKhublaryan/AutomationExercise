package com.AutomationExercise.config;

import com.AutomationExercise.utils.CustomWebDriver;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class DriverInitializer extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) {
        testContext.getApplicationContext().getBean(CustomWebDriver.class);
        CustomWebDriver.setUp();
    }

    @Override
    public void afterTestClass(TestContext testContext) {
        testContext.getApplicationContext().getBean(CustomWebDriver.class);
        CustomWebDriver.getDriver().quit();
        new CustomWebDriver().removeDriverThreadLocal();
    }
}