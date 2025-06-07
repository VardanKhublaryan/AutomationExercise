package com.AutomationExercise.pages;

import com.AutomationExercise.utils.CustomWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.AutomationExercise.utils.Configuration.CART_PAGE_URL;
import static com.AutomationExercise.utils.CustomWebElement.*;

public class CartPage extends BasePage<CartPage> {
    @FindBy(css = "[class='btn btn-default check_out']")
    private WebElement proceedToCheckout;
    @FindBy(css = "td.cart_description>h4")
    private List<WebElement> itemNameInCartPage;
    @FindBy(className = "cart_price")
    private List<WebElement> itemPriceInCartPage;
    @FindBy(css = "[class='address_firstname address_lastname']")
    private WebElement userLastName;
    @FindBy(xpath = "//*[@id='address_delivery']/li[3]")
    private WebElement userCompany;
    @FindBy(className = "address_country_name")
    private WebElement country;
    @FindBy(className = "address_phone")
    private WebElement phoneNumber;
    @FindBy(className = "form-control")
    private WebElement commentField;
    @FindBy(css = "[href='/payment']")
    private WebElement placeOrderButton;
    @FindBy(css = "[data-qa='name-on-card']")
    private WebElement nameOnCartField;
    @FindBy(css = "[data-qa='card-number']")
    private WebElement cartNumber;
    @FindBy(css = "[data-qa='cvc']")
    private WebElement cvc;
    @FindBy(css = "[data-qa='expiry-month']")
    private WebElement expirationMonth;
    @FindBy(css = "[data-qa='expiry-year']")
    private WebElement expirationYear;
    @FindBy(id = "submit")
    private WebElement payButton;
    @FindBy(id = "success_message")
    private WebElement messageSuccesses;
    @FindBy(css = "[href='/view_cart']")
    private List<WebElement> viewCart;

    public CartPage() {
        super(CustomWebDriver.getDriver());
        PageFactory.initElements(CustomWebDriver.getDriver(), this);
    }

    @Override
    protected String getPageUrl() {
        return CART_PAGE_URL;
    }

    // For cart button test see Home
    @Override
    public CartPage open() {
        return super.openPage(CartPage.class);
    }

    @Override
    protected CartPage init() {
        return super.initPage(CartPage.class);
    }

    @Override
    protected void isLoaded() throws Error {
        super.isLoaded();
        try {
            isDisplayed(proceedToCheckout);
        } catch (Exception e) {
            throw new Error("CartPage is not loaded");
        }
    }

    public void clickViewCartButton() {
        click(viewCart.get(1));
    }

    public void clickProceedToCheckout() {
        click(proceedToCheckout);
    }

    public String getItemNameInCartPage() {
        return getText(itemNameInCartPage.get(itemNameInCartPage.size() - 1));
    }

    public String getItemPriceInCartPage() {
        return getText(itemPriceInCartPage.get(itemPriceInCartPage.size() - 1));
    }

    public String getUserLastName() {
        return getText(userLastName).substring(2);
    }

    public String getUserCompany() {
        return getText(userCompany);
    }

    public String getUserCountry() {
        return getText(country);
    }

    public String getUserPhoneNumber() {
        return getText(phoneNumber);
    }

    public void fillCommentField(String comment) {
        fill(commentField, comment);
    }

    public void clickPlaceOrderBtn() {
        click(placeOrderButton);
    }

    public void payAndConfirm(String nameOnCart, String numberOfCart, String userCvc, String expirationM, String expirationY) {
        fill(nameOnCartField, numberOfCart);
        fill(cartNumber, nameOnCart);
        fill(expirationMonth, expirationM);
        fill(cvc, userCvc);
        fill(expirationYear, expirationY);
        click(payButton);
    }

    public boolean messageSuccessIsDisplayed() {
        return isDisplayed(messageSuccesses);
    }

}
