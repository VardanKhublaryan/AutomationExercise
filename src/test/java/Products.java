import com.AutomationExercise.SpringApp;
import com.AutomationExercise.pages.HomePage;
import com.AutomationExercise.pages.ProductsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

@SpringBootTest(classes = SpringApp.class)
public class Products extends BaseTest {

    private String itemNameInHomePage;
    int index;

    @Autowired
    private HomePage homePage;

    @Autowired
    private ProductsPage productsPage;

    @BeforeClass
    public void goToProductsPage() {
        Random random = new Random();
        index = random.nextInt(0, homePage.getItemNamesSize());
        itemNameInHomePage = homePage.getTextItemInHomePage(index);
        productsPage.open();
    }

    @Test
    public void searchProduct() {
        productsPage.search(itemNameInHomePage);
        Assert.assertEquals(itemNameInHomePage, productsPage.getItemNameInProducts());
    }

}
