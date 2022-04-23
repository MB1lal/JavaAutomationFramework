package backend.steps;

import backend.connectors.UserConnector;
import backend.models.users.UserModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SharedStateConstants.BACKEND.USERS.USERNAME;
import static utils.SharedStateConstants.BACKEND.USERS.USER_RESPONSE;

public class UsersSteps extends BaseSteps{

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
        Serenity.setSessionVariable(USER_RESPONSE).to(userConnector.deleteUser(Serenity.sessionVariableCalled(USERNAME)));
    }

    @And("User is successfully deleted")
    public void user_is_successfully_deleted() {
        Response response = Serenity.sessionVariableCalled(USER_RESPONSE);
        assertThat(response.statusCode()).isEqualTo(200);
    }

}
