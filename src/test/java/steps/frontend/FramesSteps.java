package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FramesPage;
import pages.IFramePage;
import pages.NestedFramesPage;
import steps.base.BaseSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesSteps extends BaseSteps {
    FramesPage framesPage = new FramesPage();
    IFramePage iFramePage = new IFramePage();
    NestedFramesPage nestedFramesPage = new NestedFramesPage();

    @When("I navigate to {} page")
    public void navigateToXFramePage(String framePage) {
        logger.info("Switching to " + framePage);
        framesPage.navigateToXFramePage(framePage);
    }

    @And("I write {}in iframe")
    public void inputTextIntoIFrame(String inputText) {
        logger.info("Switching to iFrame");
        iFramePage.switchToIFrame();
        logger.info("Entering text into iFrame");
        iFramePage.enterTextIntoContent(inputText);
    }

    @Then("iframe has the text {}")
    public void verifyMainContentIsDisplayed(String expectedText) {
        logger.info("Verifying text of iFrame");
        assertThat(iFramePage.getIFrameText()).as("Incorrect text").isEqualTo(expectedText);
    }

    @Then("{} frame has {} text")
    public void verifyFrameTexts(String frameIdentifier, String expectedText) {
        logger.info("Verifying " + frameIdentifier + " has " + expectedText + " text");
        assertThat(nestedFramesPage.getFrameText(frameIdentifier)).isEqualTo(expectedText);
    }
}
