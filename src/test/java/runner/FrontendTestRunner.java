package runner;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/frontend"},
        glue = {"steps"},
        tags = "@test and not @ignore and @herokuapp and not @download",
        stepNotifications = false,
        dryRun = false,
        plugin = {
                "json:target/cucumber-report/frontend-cucumber.json"
        }
)
public class FrontendTestRunner {
}
