package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DynamicLoadingExample1Page;
import pages.DynamicLoadingExample2Page;
import pages.DynamicLoadingPage;
import steps.base.BaseSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicLoadingSteps extends BaseSteps {

    private DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage();
    private DynamicLoadingExample1Page dynamicLoadingExample1 = new DynamicLoadingExample1Page();
    private DynamicLoadingExample2Page dynamicLoadingExample2 = new DynamicLoadingExample2Page();

    private int exampleNumber = 0;


    @When("I click the {} link")
    public void navigateToExample(String example) {
        if(!example.contains("Example 1") && !example.contains("Example 2")) {
            logger.error("Invalid example specified");
            throw new IllegalArgumentException();
        }
        logger.info("Navigating to $example");
        exampleNumber = example.contains("1") ? 1 : 2;
        dynamicLoadingPage.navigateToExample(example);
    }

    @And("I click the \"Start\" button")
    public void clickStart() {
        logger.info("Clicking start button");
        switch(exampleNumber) {
            case 1:
                dynamicLoadingExample1.clickStart();
                break;
            case 2:
                dynamicLoadingExample2.clickStart();
                break;
        }
    }

    @Then("I should see the loaded element on the page")
    public void verifyElementIsLoaded() {
        String validationText = "Hello World!";
        switch(exampleNumber) {
            case 1:
                assertThat(dynamicLoadingExample1.getLoadedElementText()).as("Incorrect text").isEqualTo(validationText);
                break;
            case 2:
                assertThat(dynamicLoadingExample2.getLoadedElementText()).as("Incorrect text").isEqualTo(validationText);
                break;
        }
    }
}
