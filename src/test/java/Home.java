import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.utils.JavascriptHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.AutomationExercise.utils.Configuration.*;
import static com.AutomationExercise.utils.CustomWebDriver.getDriver;


public class Home extends BaseTest {
    @Test
    public void pageScroll() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(getDriver());
        JavascriptHelper.scrollDown();
        long scrollDownPosition = JavascriptHelper.getScrollPosition();
        homePage.clickScrollUpBtn();
        softAssert.assertTrue(homePage.subscriptionIsDisplayed());
        long scrollUpPosition = JavascriptHelper.getScrollPosition();
        softAssert.assertTrue(scrollUpPosition < scrollDownPosition, "Page Is Not scrolled");
        softAssert.assertTrue(homePage.verifySlide());
        softAssert.assertAll();
    }

    @Test
    public void goToProductsFromHeader() {
        new HomePage(getDriver()).getHeader().clickProductsBtn();
        Assert.assertEquals(getDriver().getCurrentUrl(), PRODUCTS_PAGE_URL);
    }

    @Test
    public void goToCartFromHeader() {
        new HomePage(getDriver()).getHeader().clickCartButton();
        Assert.assertEquals(getDriver().getCurrentUrl(), CART_PAGE_URL);
    }

    @Test
    public void goToCSignupLoginFromHeader() {
        new HomePage(getDriver()).getHeader().clickSignupLoginBtn();
        Assert.assertEquals(getDriver().getCurrentUrl(), REGISTER_LOGIN_URL);
    }
}
