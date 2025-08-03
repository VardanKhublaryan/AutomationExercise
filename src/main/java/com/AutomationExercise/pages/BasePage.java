package com.AutomationExercise.pages;

import com.AutomationExercise.pages.components.Header;
import com.AutomationExercise.utils.CustomWebDriver;
import com.AutomationExercise.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {

    @Autowired
    private CustomWebDriver customWebDriver;

    @Autowired
    private  WaitHelper waitHelper;

    private Header header;

    BasePage() {
    }

    protected abstract String getPageUrl();

    protected abstract T open();

    protected abstract T init();

    /**
     * open page and return instance of page
     **/
    protected T openPage(Class<T> _class) {
        load();
        return initPage(_class);
    }

    protected T initPage(Class<T> _class) {
        PageFactory.initElements(customWebDriver.getDriver(), _class);
        return this.get();
    }

    @Override
    protected void load() {
        customWebDriver.getDriver().get(getPageUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        waitHelper.pageToBeLoaded();
    }
}
