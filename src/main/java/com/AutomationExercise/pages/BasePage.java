package com.AutomationExercise.pages;

import com.AutomationExercise.pages.components.Header;
import com.AutomationExercise.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {
    private WebDriver driver;
    private Header header;

    BasePage(WebDriver driver) {
        this.driver = driver;
        header = new Header(driver);
    }

    protected abstract String getPageUrl();

    protected abstract T open();

  /** open page and return instance of page **/
    protected T openPage() {
        load();
        return this.get();
    }

    @Override
    protected void load() {
        driver.get(getPageUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        WaitHelper.pageToBeLoaded();
    }

    public Header getHeader(){
        return header;
    }
}
