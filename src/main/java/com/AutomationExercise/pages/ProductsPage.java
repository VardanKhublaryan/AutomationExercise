package com.AutomationExercise.pages;

import com.AutomationExercise.utils.CustomWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.AutomationExercise.utils.Configuration.PRODUCTS_PAGE_URL;
import static com.AutomationExercise.utils.CustomWebElement.*;

public class ProductsPage extends BasePage<ProductsPage> {
    @FindBy(css = "[href='/products']")
    private WebElement productsBtn;
    @FindBy(id = "sale_image")
    private WebElement saleImg;
    @FindBy(id = "search_product")
    private WebElement searchInput;
    @FindBy(id = "submit_search")
    private WebElement submitSearchBtn;
    @FindBy(css = "div.productinfo>p")
    private WebElement itemsNamesProductsPage;


    public ProductsPage() {
        super(CustomWebDriver.getDriver());
        PageFactory.initElements(CustomWebDriver.getDriver(), this);
    }

    @Override
    protected String getPageUrl() {
        return PRODUCTS_PAGE_URL;
    }

    // For Products button test see Home
    @Override
    public ProductsPage open() {
        return super.openPage(ProductsPage.class);
    }

    @Override
    protected ProductsPage init() {
        return super.initPage(ProductsPage.class);
    }


    @Override
    public void isLoaded() throws Error {
        super.isLoaded();
        try {
            isDisplayed(saleImg);
        } catch (Exception e) {
            throw new Error("ProductsPag is not loaded");
        }
    }

    public void search(String productName) {
        fill(searchInput, productName);
        click(submitSearchBtn);
    }

    public String getItemNameInProducts() {
        System.out.println(getText(itemsNamesProductsPage));
        return getText(itemsNamesProductsPage);
    }
}
