package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import pages.AuthenticationPage;
import steps.base.BaseSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationSteps extends BaseSteps {
    AuthenticationPage authenticationPage = new AuthenticationPage();

    @Steps
    HerokuMainPageSteps herokuMainPageSteps;

    @When("I enter username {} and password {}")
    public void enterUserPass(String username,String password) {
        logger.info("Entering username and password");
        authenticationPage.enterUsernameAndPassword(username, password);
    }

    @And("I click the login button")
    public void loginIsClicked() {
        logger.info("Logging in");
        authenticationPage.clickLogin();
    }

    @Then("I should be logged in successfully")
    public void userIsLoggedIn() {
        assertThat(authenticationPage.userIsLoggedIn())
                .as("User is not logged in.")
                .isTrue();
                logger.info("Logged in successfully");
    }

    @Then("I should see an error message {}")
    public void userIsNotLoggedIn(String error) {
        assertThat(authenticationPage.getDriver().getPageSource()).contains(error);
        logger.info("Not logged in");
    }

    @Given("I am logged in on the form authentication page")
    public void userIsAlreadyLoggedIn() {
        herokuMainPageSteps.navigateToXPage("form authentication");
        enterUserPass("tomsmith","SuperSecretPassword!");
        loginIsClicked();
    }

    @When("I click the logout button")
    public void logoutIsClicked() {
        logger.info("User is logging out");
        authenticationPage.clickLogout();
    }

    @Then("I should be logged out and redirected to the login page")
    public void userIsLoggedOut() {
        assertThat(authenticationPage.userIsLoggedOut())
                .as("User is not logged out.")
                .isTrue();
        logger.info("User is logged out");
        assertThat(authenticationPage.getDriver().getCurrentUrl())
                .as("User is not logged out")
                .contains("/login");
        logger.info("User is at login page");
    }
}
