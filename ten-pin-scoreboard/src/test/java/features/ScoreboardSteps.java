package features;

import com.tenpin.ApplicationStart;
import com.tenpin.TenPinScoreboardConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@SpringBootTest(classes = { ApplicationStart.class, TenPinScoreboardConfig.class})
@EnableConfigurationProperties
@CucumberContextConfiguration
public class ScoreboardSteps {
    private final ByteArrayOutputStream soutCaptor = new ByteArrayOutputStream();
    private final ApplicationStart app;

    public ScoreboardSteps(ApplicationStart app) {
        this.app = app;
    }

    @When("I input my scoreboard")
    public void iShowMyScoreboard() {
        System.setOut(new PrintStream(soutCaptor));
        app.run();
    }

    @Then("I'm shown my scoreboard")
    public void iMShownMyScoreboard() {
        Assert.assertEquals("Jeff's throw scored a 10\n" +
                "John's throw scored a 3\n" +
                "John's throw scored a 7\n" +
                "Jeff's throw scored a 10\n" +
                "John's throw scored a 3\n" +
                "John's throw scored a 7\n" +
                "Jeff's throw scored a x", soutCaptor.toString()
                .trim());
    }
}
