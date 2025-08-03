package com.AutomationExercise.pages;

import com.AutomationExercise.utils.CustomWebDriver;
import com.AutomationExercise.utils.CustomWebElement;
import com.AutomationExercise.utils.JavascriptHelper;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.AutomationExercise.utils.Configuration.HOME_URL;

@Component
@Scope("prototype")
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

    @Autowired
    private CustomWebDriver customWebDriver;

    @Autowired
    private CustomWebElement customWebElement;

    @Autowired
    private JavascriptHelper javascriptHelper;

    public HomePage() {
        super();
    }

    @PostConstruct
    public void initPage() {
        PageFactory.initElements(customWebDriver.getDriver(), this);
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
            customWebElement.isDisplayed(homePageIcon);
        } catch (Exception e) {
            throw new Error("HomePage is not loaded");
        }
    }

    public String getTextItemInHomePage(int itemIndex) {
        return customWebElement.getText(itemsNamesInHomePage.get(itemIndex));
    }

    public String getItemPrice(int index) {
        return customWebElement.getText(itemsPriceInHomePage.get(index));
    }

    public int getItemNamesSize() {
        return itemsNamesInHomePage.size();
    }

    public void clickAddToCartButton() {
        javascriptHelper.scrollDown();
        customWebElement.click(addToCartBtn);
    }

    public boolean subscriptionIsDisplayed() {
        return customWebElement.isDisplayed(subscription);
    }

    public void clickScrollUpBtn() {
        customWebElement.click(scrollUpButton);
    }

    public boolean verifySlide() {
        return customWebElement.getText(carouselLinerSlide).contains("Fledged practice website for Automation Engineers");
    }
}


