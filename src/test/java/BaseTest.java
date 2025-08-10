import com.AutomationExercise.SpringApp;
import com.AutomationExercise.pages.HomePage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

@SpringBootTest(classes = SpringApp.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HomePage homePage;

    @SneakyThrows
    @BeforeClass()
    public void setUp() {
        homePage.open();
    }
}

