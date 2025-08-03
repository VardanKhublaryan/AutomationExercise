import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.pages.ProductsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class Products extends BaseTest {
    String itemNameInHomePage;
    int index;
    @Autowired
    HomePage homePage;

    @BeforeMethod
    public void goToProductsPage() {
        Random random = new Random();
        index = random.nextInt(0, homePage.getItemNamesSize());
        itemNameInHomePage = homePage.getTextItemInHomePage(index);
        new ProductsPage().open();
    }

    @Test
    public void searchProduct() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.search(itemNameInHomePage);
        Assert.assertEquals(itemNameInHomePage, productsPage.getItemNameInProducts());
    }
}
