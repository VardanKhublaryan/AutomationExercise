import com.AutomationExercise.constants.UserDetails;
import com.AutomationExercise.pages.CartPage;
import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.pages.RegisterLoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

import static com.AutomationExercise.utils.CustomWebDriver.getDriver;

public class Cart extends BaseTest {

    @BeforeMethod
    public void goToCartPage() {
        RegisterLoginPage loginPage = new RegisterLoginPage(getDriver());
        loginPage.open();
        loginPage.login(UserDetails.EMAIL.getValue(), UserDetails.PASSWORD.getValue());
    }

    @Test
    public void createOrder() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(getDriver());
        String itemName = homePage.getTextItemInHomePage(0);
        String itemPrice = homePage.getItemPrice(0);
        homePage.clickAddToCartButton();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickViewCartButton();
        cartPage.clickProceedToCheckout();
        softAssert.assertEquals(itemName, cartPage.getItemNameInCartPage());
        softAssert.assertEquals(itemPrice, cartPage.getItemPriceInCartPage());
        softAssert.assertEquals(cartPage.getUserLastName(), UserDetails.USER_LAST_NAME.getValue());
        softAssert.assertEquals(cartPage.getUserCompany(), UserDetails.COMPANY.getValue());
        softAssert.assertEquals(cartPage.getUserCountry(), UserDetails.COUNTRY.getValue());
        softAssert.assertEquals(cartPage.getUserPhoneNumber(), UserDetails.MOBILE_NUMBER.getValue());
        cartPage.fillCommentField("good site!");
        cartPage.clickPlaceOrderBtn();
        cartPage.payAndConfirm(UserDetails.CART_NAME.getValue(), UserDetails.CART_NUMBER.getValue(), UserDetails.CVC.getValue()
                , UserDetails.CART_EXPIRATION_MONTH.getValue(), UserDetails.CART_EXPIRATION_YEAR.getValue());
        getDriver().navigate().back();
        softAssert.assertTrue(new CartPage(getDriver()).messageSuccessIsDisplayed());
        softAssert.assertAll();
    }
}
