package com.AutomationExercise.pages;

import com.AutomationExercise.utils.CustomWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.AutomationExercise.utils.Configuration.HOME_URL;
import static com.AutomationExercise.utils.CustomWebElement.*;

public class HomePage extends BasePage<HomePage> {

    @FindBy(css = "[href='/']")
    private WebElement homePageIcon;
    @FindBy(css = "div.productinfo>p")
    private List<WebElement> itemsNamesInHomePage;
    @FindBy(css = "div.productinfo>h2")
    private List<WebElement> itemsPriceInHomePage;
    @FindBy(css = "[data-product-id='1']")
    private WebElement addToCartBtn;
    @FindBy(css = "div.single-widget>h2")
    private WebElement subscription;
    @FindBy(id = "scrollUp")
    private WebElement scrollUpButton;
    @FindBy(css = "[class='item active']")
    private WebElement carouselLinerSlide;

    public HomePage() {
        super(CustomWebDriver.getDriver());
        PageFactory.initElements(CustomWebDriver.getDriver(), this);
    }

    @Override
    protected String getPageUrl() {
        return HOME_URL;
    }

    @Override
    public HomePage open() {
        return super.openPage(HomePage.class);
    }

    @Override
    protected HomePage init() {
        return super.initPage(HomePage.class);
    }

    @Override
    public void isLoaded() throws Error {
        super.isLoaded();
        try {
            isDisplayed(homePageIcon);
        } catch (Exception e) {
            throw new Error("HomePage is not loaded");
        }
    }

    public String getTextItemInHomePage(int itemIndex) {
        return getText(itemsNamesInHomePage.get(itemIndex));
    }

    public String getItemPrice(int index) {
        return getText(itemsPriceInHomePage.get(index));
    }

    public int getItemNamesSize() {
        return itemsNamesInHomePage.size();
    }

    public void clickAddToCartButton() {
        click(addToCartBtn);
    }

    public boolean subscriptionIsDisplayed() {
        return isDisplayed(subscription);
    }

    public void clickScrollUpBtn() {
        click(scrollUpButton);
    }

    public boolean verifySlide() {
        return getText(carouselLinerSlide).contains("Fledged practice website for Automation Engineers");
    }
}


