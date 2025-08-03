import com.AutomationExercise.SpringApp;
import com.AutomationExercise.constants.UserDetails;
import com.AutomationExercise.pages.RegisterLoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.AutomationExercise.constants.UserDetails.*;

@SpringBootTest(classes = SpringApp.class)
public class RegistrationLogin extends BaseTest {

    private SoftAssert softAssert;

    @Autowired
    private RegisterLoginPage registerPage;

    @BeforeClass
    public void goToLoginPage() {
        registerPage.open();
    }

    @Test
    public void validSignUp() {
        softAssert = new SoftAssert();
        softAssert.assertEquals(registerPage.getSignUpText(), "New User Signup!");
        registerPage.sinUp(NAME.getValue(), registerPage.randomEmail());
        softAssert.assertEquals(registerPage.getAccountInfoText(), "ENTER ACCOUNT INFORMATION");
        registerPage.fillAccountInfo(NAME.getValue(), PASSWORD.getValue());
        registerPage.fillAddressInfo(NAME.getValue(), USER_LAST_NAME.getValue(), COMPANY.getValue(),
                ADDRESS_1.getValue(), ADDRESS_2.getValue(), STATE.getValue(), CITY.getValue(), ZIP_CODE.getValue(), MOBILE_NUMBER.getValue());
        softAssert.assertEquals(registerPage.getAccountCreatedText(), "ACCOUNT CREATED!");
        registerPage.clickOnContinueBtn();
        softAssert.assertTrue(registerPage.getLoggedUserText().contains("Logged in as " + NAME.getValue()));
        registerPage.deleteAccount();
        softAssert.assertEquals(registerPage.getDeleteAccountText(), "ACCOUNT DELETED!");
        registerPage.clickOnSignupLoginBtn();
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "validSignUp")
    public void validLogin() {
        registerPage.login(UserDetails.EMAIL.getValue(), UserDetails.PASSWORD.getValue());
        Assert.assertTrue(registerPage.getLoggedUserText().contains("Logged in as " + NAME.getValue()));
        registerPage.deleteCookies();
    }
}
