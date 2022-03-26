package runner;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/backend"},
        glue = {"backend"},
        tags = "@test and @backend and not @ignore",
        stepNotifications = true,
        plugin = {
                "json:target/cucumber-report/cucumber.json"
        }
)
public class backendTestRunner {
}
