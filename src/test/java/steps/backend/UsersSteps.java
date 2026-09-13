package steps.backend;

import static org.assertj.core.api.Assertions.assertThat;

import connectors.UserConnector;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.users.UserModel;
import steps.base.BaseSteps;

public class UsersSteps extends BaseSteps {

    private UserConnector userConnector = new UserConnector();

    @Given("I create a user")
    public void i_create_a_user() {
        UserModel user = createUserPayLoad();
        userConnector.createNewUser(user.toJson());
    }

    @When("User is successfully created")
    public void user_is_successfully_created() {
        verifyUserExists();
    }

    @Then("I login using same user")
    public void i_login_using_same_user() {
        loginUser();
    }

    @Then("I logout using same user")
    public void i_logout_using_same_user() {
        logoutUser();
    }

    @Then("I delete the user")
    public void i_delete_user() {
        context()
                .setUserResponse(
                        userConnector.deleteUser(context().getCurrentUser().getUsername()));
    }

    @And("User is successfully deleted")
    public void user_is_successfully_deleted() {
        Response response = context().getUserResponse();
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
