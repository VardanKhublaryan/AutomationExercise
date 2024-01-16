package com.AutomationExercise.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.AutomationExercise.utils.WaitHelper.*;


public class CustomWebElement {


    public static boolean isDisplayed(WebElement element) {
        try {
            waitUntilElementIsDisplayed(element);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean isDisabled(WebElement element) {
        try {
            waitUntilElementClickable(element);
            return !element.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEnabled(WebElement element) {
        try {
            waitUntilElementClickable(element);
            return element.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void click(WebElement element) {
        if (isDisplayed(element)) {
            try {
                waitUntilElementClickable(element);
                element.click();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void submit(WebElement element) {
        isDisplayed(element);
        try {
            element.submit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void select(WebElement element, String index) {
        isDisplayed(element);
        try {
            Select select = new Select(element);
            select.selectByValue(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fill(WebElement element, String field) {
        isDisplayed(element);
        element.clear();
        element.sendKeys(field);
    }

    public static String getText(WebElement element) {
        waitUntilVisibilityOfText(element);
        return element.getText();
    }

    public static String getAttribute(WebElement element, String attributeName) {
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
