package steps.frontend;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NotificationsPage;
import steps.base.BaseSteps;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class NotificationsSteps extends BaseSteps {
    private NotificationsPage notificationsPage = new NotificationsPage();

    @When("I generate a new notification")
    public void shuffleNotifications() {
        logger.info("Clicking the notifications button");
        notificationsPage.clickToShuffleNotification();
    }

    @Then("I should see one of the correct notification messages")
    public void verifyNotificationMessages() {
        logger.info("Verifying notification messages");
        List<String> expectedNotifications = List.of("Action successful", "Action unsuccesful, please try again");
        assertThat(notificationsPage.getNotificationText())
                .as("Incorrect notification message")
                .isIn(expectedNotifications);
    }
}
