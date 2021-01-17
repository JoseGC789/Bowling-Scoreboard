package features;

import com.tenpin.ApplicationStart;
import com.tenpin.TenPinScoreboardConfig;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootTest(classes = { ApplicationStart.class, TenPinScoreboardConfig.class})
@CucumberContextConfiguration
public class ScoreboardSteps {
    private static final String EXPECTED_SCOREBOARD = "src/test/resources/scoreboard.txt";
    private static final ByteArrayOutputStream SOUT_CAPTOR = new ByteArrayOutputStream();
    private final ApplicationStart app;
    private String scoreboard;

    public ScoreboardSteps(ApplicationStart app) {
        this.app = app;
    }

    @Before
    public void init() throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        Stream<String> stream = Files.lines( Paths.get(EXPECTED_SCOREBOARD), StandardCharsets.UTF_8);
        stream.forEach(s -> contentBuilder.append(s).append("\n"));
        scoreboard = contentBuilder.toString();
        System.setOut(new PrintStream(SOUT_CAPTOR));
    }

    @When("I input my games")
    public void iShowMyScoreboard() {
        app.run();
    }

    @Then("I'm shown my scoreboard")
    public void iMShownMyScoreboard() {
        Assert.assertEquals(scoreboard, SOUT_CAPTOR.toString().trim());
    }
}
