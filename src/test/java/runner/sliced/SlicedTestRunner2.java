package runner.sliced;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {""},
        tags = "@test and not @ignore",
        plugin = {
                "json:target/cucumber-report/cucumber2.json"
        }
)
public class SlicedTestRunner2 {
}
