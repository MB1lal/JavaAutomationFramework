package steps.frontend;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckboxesPage;
import steps.base.BaseSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckboxesSteps extends BaseSteps {
    private CheckboxesPage checkboxesPage = new CheckboxesPage();

    @When("I toggle the {} checkbox")
    public void toggleCheckbox(String index) {
        logger.info("Selecting $index checkbox");
        if(index.equals("First")) {
            checkboxesPage.clickCheckbox(1);
        } else if(index.equals("Second")) {
            checkboxesPage.clickCheckbox(2);
        } else {
            logger.error("Invalid checkbox is specified");
            throw new AssertionError();
        }
    }

    @Then("the {} checkbox should be {}")
    public void assertBoxIsChecked(String index, String status) {
        logger.info("Asserting that $index box is $status");
        Boolean isChecked = status.equals("selected");
        if(index.equals("First")) {
            assertThat(checkboxesPage.isChecked(1))
                    .as("Checkbox status is incorrect.")
                    .isEqualTo(isChecked);
        } else if(index.equals("Second")) {
            assertThat(checkboxesPage.isChecked(2))
                    .as("Checkbox status is incorrect.")
                    .isEqualTo(isChecked);
        } else {
            logger.error("Invalid checkbox is specified");
            throw new AssertionError();
        }
    }
}
