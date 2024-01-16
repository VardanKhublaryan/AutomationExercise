package com.AutomationExercise.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.AutomationExercise.utils.CustomWebElement.click;

public class Header {
    WebDriver driver;
    @FindBy(css = "[href='/products']")
    private WebElement productsButton;
    @FindBy(css = "[href='/view_cart']")
    private WebElement cartButton;
    @FindBy(css = "[href='/login']")
    private WebElement signupLoginBtn;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickProductsBtn() {
        click(productsButton);
    }

    public void clickCartButton() {
        click(cartButton);
    }

    public void clickSignupLoginBtn() {
        click(signupLoginBtn);
    }
}
