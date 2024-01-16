import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.AutomationExercise.utils.CustomWebDriver.getDriver;

public class Products extends BaseTest {
    String itemNameInHomePage;
    int index;

    @BeforeMethod
    public void goToProductsPage() {
        Random random = new Random();
        index = random.nextInt(0, new HomePage(getDriver()).getItemNamesSize());
        itemNameInHomePage = new HomePage(getDriver()).getTextItemInHomePage(index);
        new ProductsPage(getDriver()).open();
    }

    @Test
    public void searchProduct() {
        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.search(itemNameInHomePage);
        Assert.assertEquals(itemNameInHomePage, productsPage.getItemNameInProducts());
    }
}
