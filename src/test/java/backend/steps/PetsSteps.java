package backend.steps;

import backend.connectors.PetConnector;
import backend.models.PetModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SharedStateConstants.BACKEND.PET_RESPONSE;

public class PetsSteps extends BaseSteps{

    PetConnector petConnector = new PetConnector();

    @Given("I add the pet")
    public void addAPet() {
        PetModel petModel = createNewPetPayload();
        Serenity.setSessionVariable(PET_RESPONSE).to(petConnector.addNewPet(petModel.toJson()));
    }

    @Then("Verify status code is {int}")
    public void assertingStatusCode(int statusCode) {
        Response response = Serenity.sessionVariableCalled(PET_RESPONSE);
        assertThat(response.statusCode()).isEqualTo(statusCode);
    }

}
