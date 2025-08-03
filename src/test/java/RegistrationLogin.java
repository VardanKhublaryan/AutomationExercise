import com.AutomationExercise.constants.UserDetails;
import com.AutomationExercise.pages.RegisterLoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.AutomationExercise.constants.UserDetails.*;

public class RegistrationLogin extends BaseTest {
    SoftAssert softAssert;

    @BeforeMethod
    public void goToLoginPage() {
        RegisterLoginPage registerPage = new RegisterLoginPage();
        registerPage.open();
    }

    @Test
    public void validSignUp() {
        RegisterLoginPage registerPage = new RegisterLoginPage();
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

    @Test
    public void validLogin() {
        RegisterLoginPage loginPage = new RegisterLoginPage();
        loginPage.login(UserDetails.EMAIL.getValue(), UserDetails.PASSWORD.getValue());
        Assert.assertTrue(loginPage.getLoggedUserText().contains("Logged in as " + NAME.getValue()));
    }
}
