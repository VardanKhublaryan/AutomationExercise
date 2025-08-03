import com.AutomationExercise.SpringApp;
import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.pages.components.Header;
import com.AutomationExercise.utils.CustomWebDriver;
import com.AutomationExercise.utils.JavascriptHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.AutomationExercise.utils.Configuration.*;

@SpringBootTest(classes = SpringApp.class)
public class Home extends BaseTest {

    @Autowired
    private HomePage homePage;

    @Autowired
    private CustomWebDriver custgetDriver;

    @Autowired
    private JavascriptHelper jsHelper;

    @Autowired
    private Header header;

    @Test
    public void pageScroll() {
        SoftAssert softAssert = new SoftAssert();
        jsHelper.scrollDown();
        long scrollDownPosition = jsHelper.getScrollPosition();
        homePage.clickScrollUpBtn();
        softAssert.assertTrue(homePage.subscriptionIsDisplayed());
        long scrollUpPosition = jsHelper.getScrollPosition();
        softAssert.assertTrue(scrollUpPosition < scrollDownPosition, "Page Is Not scrolled");
        softAssert.assertTrue(homePage.verifySlide());
        softAssert.assertAll();
    }

    @Test
    public void goToProductsFromHeader() {
        header.clickProductsBtn();
        Assert.assertEquals(custgetDriver.getDriver().getCurrentUrl(), PRODUCTS_PAGE_URL);
    }

    @Test
    public void goToCartFromHeader() {
        header.clickCartButton();
        Assert.assertEquals(custgetDriver.getDriver().getCurrentUrl(), CART_PAGE_URL);
    }

    @Test
    public void goToCSignupLoginFromHeader() {
        header.clickSignupLoginBtn();
        Assert.assertEquals(custgetDriver.getDriver().getCurrentUrl(), REGISTER_LOGIN_URL);
    }
}
