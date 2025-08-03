package com.AutomationExercise.pages.components;

import com.AutomationExercise.utils.CustomWebDriver;
import com.AutomationExercise.utils.CustomWebElement;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Header {

    @FindBy(css = "[href='/products']")
    private WebElement productsButton;
    @FindBy(css = "[href='/view_cart']")
    private WebElement cartButton;
    @FindBy(css = "[href='/login']")
    private WebElement signupLoginBtn;

    @Autowired
    private CustomWebElement customWebElement;

    @PostConstruct
    public void initPage() {
        PageFactory.initElements(CustomWebDriver.getDriver(), this);
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
