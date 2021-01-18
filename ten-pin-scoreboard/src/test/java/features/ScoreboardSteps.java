package features;

import com.tenpin.ApplicationStart;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ScoreboardSteps {
    private static final String EXPECTED_AVERAGE_SCOREBOARD = "src/test/resources/scoreboard-average.txt";
    private static final String EXPECTED_PERFECT_SCOREBOARD = "src/test/resources/scoreboard-perfect.txt";
    private static final String EXPECTED_AWFUL_SCOREBOARD = "src/test/resources/scoreboard-bust.txt";
    private static final String EXPECTED_FRAMES = "src/test/resources/scoreboard-frames.txt";
    private static final ByteArrayOutputStream SOUT_CAPTOR = new ByteArrayOutputStream();
    private final ApplicationStart app;
    private String scoreboard;
    private String frames;

    public ScoreboardSteps(ApplicationStart app) {
        this.app = app;
    }

    public String loadFile(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        Stream<String> stream = Files.lines( Paths.get(path), StandardCharsets.UTF_8);
        stream.forEach(s -> builder.append(s).append("\n"));
        System.setOut(new PrintStream(SOUT_CAPTOR));
        return builder.toString();
    }

    @When("I input my throws")
    public void iShowMyScoreboard() {
        app.run();
    }

    @Then("I'm shown my scoreboard")
    public void iMShownMyScoreboard() {
        Assert.assertTrue(SOUT_CAPTOR.toString().trim().contains(scoreboard.trim()));
        Assert.assertTrue(SOUT_CAPTOR.toString().trim().contains(frames));
    }

    @Given("I have an average game")
    public void iHaveAnAverageGame() throws IOException {
        scoreboard = loadFile(EXPECTED_AVERAGE_SCOREBOARD);
        frames = loadFile(EXPECTED_FRAMES);
    }

    @Given("I have a perfect game")
    public void iHaveAPerfectGame() throws IOException {
        scoreboard = loadFile(EXPECTED_PERFECT_SCOREBOARD);
        frames = loadFile(EXPECTED_FRAMES);

    }

    @Given("I have an awful game")
    public void iHaveAnAwfulGame() throws IOException {
        scoreboard = loadFile(EXPECTED_AWFUL_SCOREBOARD);
        frames = loadFile(EXPECTED_FRAMES);

    }
}
