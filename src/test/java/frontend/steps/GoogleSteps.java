package frontend.steps;

import frontend.pages.GooglePages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.SharedStateConstants.FRONTEND.WEBDRIVER;

public class GoogleSteps extends BaseSteps{

    GooglePages googlePages;

    @Given("I open a browser")
    public void openingABrowser() {
        googlePages.open();
    }

    @Then("The page is loaded")
    public void assertingThatPageIsLoaded() {
        WebDriver driver = Serenity.sessionVariableCalled(WEBDRIVER);
        assertThat(driver.getTitle())
                .isEqualTo("Google")
                .as("The title of the page was : " + driver.getTitle());
    }

}
