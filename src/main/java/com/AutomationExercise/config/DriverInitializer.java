package com.AutomationExercise.config;

import com.AutomationExercise.utils.CustomWebDriver;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class DriverInitializer extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        CustomWebDriver customWebDriver = testContext.getApplicationContext().getBean(CustomWebDriver.class);
        customWebDriver.setUp(); // Initialize your driver early
    }
}