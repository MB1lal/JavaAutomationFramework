package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MultiWindowPage;
import steps.base.BaseSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiWindowSteps extends BaseSteps {
    private MultiWindowPage multiWindowPage = new MultiWindowPage();

    @When("I click the \"Click Here\" link to open a new window")
    public void clickButtonToOpenNewWindow() {
        logger.info("Clicking button to open link in new window");
        multiWindowPage.openClickHereLink();
    }

    @And("I switch to the {} tab")
    public void switchWindow(String tabId) {
        logger.info("Switching to the $tabId tab");
        multiWindowPage.switchToTab(tabId);
    }

    @Then("Header of the page should have {} text")
    public void performActionsOnNewWindow(String expectedText) {
        logger.info("Asserting header text of page");
        assertThat(multiWindowPage.getHeaderText()).as("Incorrect text on new tab").isEqualTo(expectedText);
    }
}
