import com.tenpin.ApplicationStart;
import com.tenpin.TenPinScoreboardConfig;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        plugin = {"pretty", "html:target/surefire-reports/cucumber-html-report.html"},
        monochrome = true)
public class CucumberTest {
}