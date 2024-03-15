package com.AutomationExercise.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.AutomationExercise.utils.Configuration.REGISTER_LOGIN_URL;
import static com.AutomationExercise.utils.CustomWebElement.*;

public class RegisterLoginPage extends BasePage<RegisterLoginPage> {


    @FindBy(css = "[href='/login']")
    private WebElement signupLoginButton;
    @FindBy(css = "div.signup-form>h2")
    private WebElement signupText;
    @FindBy(css = "[data-qa='signup-name'")
    private WebElement nameField;
    @FindBy(css = "[data-qa='signup-email'")
    private WebElement emailField;
    @FindBy(css = "[data-qa='signup-button']")
    private WebElement submitBtn;
    @FindBy(css = "[class='title text-center']")
    private WebElement accountInfoText;
    @FindBy(id = "name")
    private WebElement signupNameField;
    @FindBy(id = "email")
    private WebElement signupEmailField;
    @FindBy(id = "password")
    private WebElement signupPassField;
    @FindBy(id = "days")
    private WebElement uniformDays;
    @FindBy(id = "months")
    private WebElement uniformMonths;
    @FindBy(id = "years")
    private WebElement uniformYears;
    @FindBy(id = "id_gender1")
    private WebElement titleBtn;
    @FindBy(id = "newsletter")
    private WebElement newsLetterRadioBtn;
    @FindBy(id = "optin")
    private WebElement specialOffers;
    @FindBy(id = "first_name")
    private WebElement firstName;
    @FindBy(id = "last_name")
    private WebElement lastName;
    @FindBy(id = "company")
    private WebElement company;
    @FindBy(id = "address1")
    private WebElement address1;
    @FindBy(id = "address2")
    private WebElement address2;
    @FindBy(id = "country")
    private WebElement country;
    @FindBy(id = "state")
    private WebElement state;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "zipcode")
    private WebElement zipcode;
    @FindBy(id = "mobile_number")
    private WebElement mobile_number;
    @FindBy(css = "[data-qa='create-account']")
    private WebElement createAccountBtn;
    @FindBy(css = "[data-qa='account-created']")
    private WebElement accountCreatedText;
    @FindBy(css = "[data-qa='continue-button']")
    private WebElement continueButton;
    @FindBy(css = "div,shop-menu>a")
    private WebElement loggedInUserText;
    @FindBy(css = "[href='/delete_account']")
    private WebElement deleteAccountBtn;
    @FindBy(css = "[data-qa='account-deleted']")
    private WebElement deleteAccountText;
    @FindBy(css = "[data-qa='login-email']")
    private WebElement loginEmailField;
    @FindBy(css = "[data-qa='login-password']")
    private WebElement passwordField;
    @FindBy(css = "[data-qa='login-button']")
    private WebElement loginButton;

    public RegisterLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getPageUrl() {
        return REGISTER_LOGIN_URL;
    }

    // For signUpLogin button test see Home
    @Override
    public RegisterLoginPage open() {
        return super.openPage();
    }

    @Override
    public void isLoaded() throws Error {
        super.isLoaded();
        try {
            isDisplayed(signupText);
        } catch (Exception e) {
            throw new Error("LoginPage is not loaded");
        }
    }

    public void clickOnSignupLoginBtn() {
        click(signupLoginButton);
    }

    public String getSignUpText() {
        return getText(signupText);
    }

    public String randomEmail() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        String email;
        String temp = RandomStringUtils.random(10, allowedChars);
        email = temp + "@testdata.com";
        return email;
    }

    public void sinUp(String name, String email) {
        fill(nameField, name);
        fill(emailField, email);
        click(submitBtn);
    }

    public void fillAddressInfo(String firstName, String lastName, String company, String address, String address2,
                                String state, String city, String zipcode, String mobileNumber) {
        List<String> countries = new ArrayList<>(Arrays.asList("India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore"));
        Random randomCountry = new Random();
        fill(this.firstName, firstName);
        fill(this.lastName, lastName);
        fill(this.company, company);
        fill(address1, address);
        fill(this.address2, address2);
        select(country, countries.get(randomCountry.nextInt(0, countries.size() - 1)));
        fill(this.state, state);
        fill(this.city, city);
        fill(this.zipcode, zipcode);
        fill(this.mobile_number, mobileNumber);
        click(createAccountBtn);
    }

    public void clickOnContinueBtn() {
        click(continueButton);
    }

    public void fillAccountInfo(String name, String password) {
        click(titleBtn);
        fill(signupNameField, name);
        fill(signupPassField, password);

        Random random = new Random();
        int day = random.nextInt(1, 10);
        String days = Integer.toString(day);

        String months = Integer.toString(random.nextInt(1, 12));
        String years = Integer.toString(random.nextInt(1997, 2002));
        select(uniformDays, days);
        select(uniformMonths, months);
        select(uniformYears, years);
        click(newsLetterRadioBtn);
        click(specialOffers);
    }

    public String getAccountInfoText() {
        return getText(accountInfoText);
    }

    public String getAccountCreatedText() {
        return getText(accountCreatedText);
    }

    public String getLoggedUserText() {
        return getText(loggedInUserText);
    }

    public void deleteAccount() {
        click(deleteAccountBtn);
    }

    public String getDeleteAccountText() {
        return getText(deleteAccountText);
    }

    public void login(String email, String pass) {
        fill(loginEmailField, email);
        fill(passwordField, pass);
        click(loginButton);
    }
}
