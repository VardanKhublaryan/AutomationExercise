package com.AutomationExercise.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CustomWebElement {

    @Autowired
    private WaitHelper waitHelper;

    public boolean isDisplayed(WebElement element) {
        try {
            waitHelper.waitUntilElementIsDisplayed(element);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDisabled(WebElement element) {
        try {
            waitHelper.waitUntilElementClickable(element);
            return !element.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEnabled(WebElement element) {
        try {
            waitHelper.waitUntilElementClickable(element);
            return element.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void click(WebElement element) {
        if (isDisplayed(element)) {
            try {
                waitHelper.waitUntilElementClickable(element);
                element.click();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void submit(WebElement element) {
        isDisplayed(element);
        try {
            element.submit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void select(WebElement element, String index) {
        isDisplayed(element);
        try {
            Select select = new Select(element);
            select.selectByValue(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fill(WebElement element, String field) {
        isDisplayed(element);
        element.clear();
        element.sendKeys(field);
    }

    public String getText(WebElement element) {
        waitHelper.waitUntilVisibilityOfText(element);
        return element.getText();
    }

    public String getAttribute(WebElement element, String attributeName) {
        if (isDisplayed(element)) {
            return element.getAttribute(attributeName);
        }
        printInfo("CAN NOT GET ATTRIBUTE BECAUSE ELEMENT IS NOT VISIBILITY");
        return "";
    }


    public static void printInfo(Object text) {
        System.out.println("\u001B[32m" + text + "\u001B[0m");
    }

    public static String printError(String text) {
        throw new Error("\u001B[31m" + text + "\u001B[0m");
    }

}
