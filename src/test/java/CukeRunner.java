import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Juan on 17/04/2017.
 */


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = false,
        format={"pretty","html:target/cucumber-html-report","json:target/cucumber.json"},
        snippets = SnippetType.CAMELCASE,
        features = "src/test/resources",
        tags={/*"@TestFramework, @TestThePuzzle"*/"@TestThePuzzle"}
)

public class CukeRunner {

}
