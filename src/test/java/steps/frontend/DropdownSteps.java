package steps.frontend;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DropdownPage;
import steps.base.BaseSteps;

public class DropdownSteps extends BaseSteps {
    private DropdownPage dropdownPage = new DropdownPage();

    @When("I select option {} from the dropdown")
    public void selectDropdownOption(String option) {
        logger.info("Selecting " + option + " from dropdown");
        dropdownPage.selectDropdownOption(option);
    }

    @Then("the selected option should be {}")
    public void verifySelectedOption(String option) {
        logger.info("Verifying " + option + " from dropdown");
        assertThat(dropdownPage.getDropdownSelectedOption())
                .as("Selected option in incorrect")
                .isEqualTo(option);
    }
}
