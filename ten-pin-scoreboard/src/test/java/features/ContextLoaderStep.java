package features;

import com.tenpin.ApplicationStart;
import com.tenpin.TenPinScoreboardConfig;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = { ApplicationStart.class, TenPinScoreboardConfig.class})
@CucumberContextConfiguration
public class ContextLoaderStep {

    @Given("the App is running")
    public void setup() {
        // Dummy method so cucumber will configure spring's context
    }
}