package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.JSAlertPage;
import steps.base.BaseSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class JSAlertSteps extends BaseSteps {
    private JSAlertPage jsAlertPage = new JSAlertPage();

    @When("I click the {} button of alert")
    public void clickJSAlertButton(String buttonText) {
        logger.info("Clicking the " + buttonText + " button");
        jsAlertPage.triggerJSAlert(buttonText);
    }

    @And("I interact as {} with the {}")
    public void interactWithAlerts(String interaction, String alertType) {
        logger.info("Interacting with " + alertType + " as " + interaction);
        jsAlertPage.interactWithAlert(interaction);
    }

    @And("I enter {} in the prompt")
    public void enterTextIntoPrompt(String inputText) {
        logger.info("Entering " + inputText + " into the prompt");
        jsAlertPage.enterTextIntoAlert(inputText);
    }

    @And("Result says {}")
    public void verifyResultText(String expectedText) {
        logger.info("Verifying result text");
        assertThat(jsAlertPage.getResultText())
                .as("Incorrect result text")
                .isEqualTo(expectedText);
    }
}
