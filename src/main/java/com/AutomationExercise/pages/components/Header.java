package com.AutomationExercise.pages.components;

import com.AutomationExercise.utils.CustomWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class Header {
    private WebDriver driver;
    @FindBy(css = "[href='/products']")
    private WebElement productsButton;
    @FindBy(css = "[href='/view_cart']")
    private WebElement cartButton;
    @FindBy(css = "[href='/login']")
    private WebElement signupLoginBtn;

    @Autowired
    private CustomWebElement customWebElement;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProductsBtn() {
        customWebElement.click(productsButton);
    }

    public void clickCartButton() {
        customWebElement.click(cartButton);
    }

    public void clickSignupLoginBtn() {
        customWebElement.click(signupLoginBtn);
    }
}
