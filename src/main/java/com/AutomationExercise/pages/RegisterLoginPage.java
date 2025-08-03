package com.AutomationExercise.pages;

//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.RandomStringUtils;

import com.AutomationExercise.utils.CustomWebDriver;
import com.AutomationExercise.utils.CustomWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private CustomWebDriver customWebDriver;

    @Autowired
    private CustomWebElement customWebElement;


    public RegisterLoginPage() {
        super();
        PageFactory.initElements(customWebDriver.getDriver(), this);
    }

    @Override
    public String getPageUrl() {
        return REGISTER_LOGIN_URL;
    }

    // For signUpLogin button test see Home
    @Override
    public RegisterLoginPage open() {
        return super.openPage(RegisterLoginPage.class);
    }

    @Override
    protected RegisterLoginPage init() {
        return super.initPage(RegisterLoginPage.class);
    }

    @Override
    public void isLoaded() throws Error {
        super.isLoaded();
        try {
            customWebElement.isDisplayed(signupText);
        } catch (Exception e) {
            throw new Error("LoginPage is not loaded");
        }
    }

    public void clickOnSignupLoginBtn() {
        customWebElement.click(signupLoginButton);
    }

    public String getSignUpText() {
        return customWebElement.getText(signupText);
    }

    public String randomEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    public void sinUp(String name, String email) {
        customWebElement.fill(nameField, name);
        customWebElement.fill(emailField, email);
        customWebElement.click(submitBtn);
    }

    public void fillAddressInfo(String firstName, String lastName, String company, String address, String address2,
                                String state, String city, String zipcode, String mobileNumber) {
        List<String> countries = new ArrayList<>(Arrays.asList("India", "United States", "Canada", "Australia", "Israel", "New Zealand", "Singapore"));
        Random randomCountry = new Random();
        customWebElement.fill(this.firstName, firstName);
        customWebElement.fill(this.lastName, lastName);
        customWebElement.fill(this.company, company);
        customWebElement.fill(address1, address);
        customWebElement.fill(this.address2, address2);
        customWebElement.select(country, countries.get(randomCountry.nextInt(0, countries.size() - 1)));
        customWebElement.fill(this.state, state);
        customWebElement.fill(this.city, city);
        customWebElement.fill(this.zipcode, zipcode);
        customWebElement.fill(this.mobile_number, mobileNumber);
        customWebElement.click(createAccountBtn);
    }

    public  List<WebElement> filterList(List<WebElement> list) {
        return list.stream()
                .filter(element -> customWebElement.getText(element).matches(".*\\d$"))
                .collect(Collectors.toList());
    }

    public void clickOnContinueBtn() {
        customWebElement.click(continueButton);
    }

    public void fillAccountInfo(String name, String password) {
        customWebElement.click(titleBtn);
        customWebElement.fill(signupNameField, name);
        customWebElement.fill(signupPassField, password);

        Random random = new Random();
        int day = random.nextInt(1, 10);
        String days = Integer.toString(day);

        String months = Integer.toString(random.nextInt(1, 12));
        String years = Integer.toString(random.nextInt(1997, 2002));
        customWebElement.select(uniformDays, days);
        customWebElement.select(uniformMonths, months);
        customWebElement.select(uniformYears, years);
        customWebElement.click(newsLetterRadioBtn);
        customWebElement.click(specialOffers);
    }

    public String getAccountInfoText() {
        return customWebElement.getText(accountInfoText);
    }

    public String getAccountCreatedText() {
        return customWebElement.getText(accountCreatedText);
    }

    public String getLoggedUserText() {
        return customWebElement.getText(loggedInUserText);
    }

    public void deleteAccount() {
        customWebElement.click(deleteAccountBtn);
    }

    public String getDeleteAccountText() {
        return customWebElement.getText(deleteAccountText);
    }

    public void login(String email, String pass) {
        customWebElement.fill(loginEmailField, email);
        customWebElement.fill(passwordField, pass);
        customWebElement.click(loginButton);
    }
}
