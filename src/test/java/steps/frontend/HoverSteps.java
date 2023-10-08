package steps.frontend;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HoversPage;
import steps.base.BaseSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class HoverSteps extends BaseSteps {
    private HoversPage hoversPage = new HoversPage();
    @When("I hover over the {} user avatar")
    public void hoverOverXUserAvatar(String userIndex) {
        logger.info("Hovering over $userIndex user avatar");
        int userId = switch (userIndex) {
            case "first" -> 1;
            case "second" -> 2;
            case "third" -> 3;
            default -> {
                logger.error("Invalid userIndex specified");
                throw new IllegalArgumentException();
            }
        };
        hoversPage.hoverOverAvatar(userId);
    }

    @Then("I should see {} for the {} user")
    public void verifyInformationForXUser(String expectedUser , String userIndex) {
        logger.info("Verifying the user information for $userIndex user");
        int userId = switch (userIndex) {
            case "first" -> 1;
            case "second" -> 2;
            case "third" -> 3;
            default -> {
                logger.error("Invalid userIndex specified");
                throw new IllegalArgumentException();
            }
        };
        assertThat(hoversPage.getProfileName(userId)).as("Profile name is incorrect").isEqualTo(expectedUser);
    }
}
