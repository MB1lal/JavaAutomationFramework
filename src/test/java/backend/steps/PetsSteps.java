package backend.steps;

import backend.connectors.PetConnector;
import backend.models.PetModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.EnvSerenity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SharedStateConstants.BACKEND.PET_ID;
import static utils.SharedStateConstants.BACKEND.PET_RESPONSE;
import static utils.SharedStateConstants.BACKEND.PET_STATUS;

public class PetsSteps extends BaseSteps{


    @Given("I add the pet with id = {int}")
    public void addAPet(int petId) throws IOException {
        PetModel petModel = createPetPayloadUsingFile();
        logger.info("setting petId into the payload.");
        petModel.setId(petId);
        logger.info("Adding the pet using api.");
        addANewPet(petModel);
    }

    @And("The pet with id = {int} {}")
    public void assertingPetWithId(int petId, String result) {
        Response response = Serenity.sessionVariableCalled(PET_RESPONSE);
        switch (result) {
            case "exists" -> {
                assertThat(response.statusCode())
                        .withFailMessage("The pet with id = " + petId + " doesn't exists")
                        .isEqualTo(200);
                PetModel petResponse = response.as(PetModel.class);
                assertThat(petResponse.getId())
                        .withFailMessage("No pet with petId = " + petId + " exists.")
                        .isEqualTo(petId);
            }
            case "doesn't exists" -> assertThat(response.statusCode())
                    .withFailMessage("The pet with id = " + petId + " still exists")
                    .isEqualTo(404);
        }

    }

    @When("I call the pet api with {}")
    public void callingApiWithId(String callingParameter) {
        switch (callingParameter.toLowerCase()) {
            case "id" -> getPetById(Serenity.sessionVariableCalled(PET_ID));
            case "status" -> getPetStatus(Serenity.sessionVariableCalled(PET_STATUS));
        }
    }

    @Given("I add the pet with status = {}")
    public void addAPet(String status) throws IOException {
        PetModel petModel = createPetPayloadUsingFile();
        petModel.setStatus(status);
        addANewPet(petModel);
    }

    @Then("The pet has status = {}")
    public void assertingPetWithStatus(String status) {
        Response response = Serenity.sessionVariableCalled(PET_RESPONSE);
        PetModel[] petResponse = response.as(PetModel[].class);

        assertThat(Arrays.stream(petResponse).anyMatch(pets -> pets.getId() == (long) Serenity.sessionVariableCalled(PET_ID)))
                .withFailMessage("No pet with id = " + Serenity.sessionVariableCalled(PET_ID) + " exists.")
                .isTrue();

        PetModel petModel = Arrays.stream(petResponse).filter(pets -> pets.getId() == (long) Serenity.sessionVariableCalled(PET_ID)).findFirst().get();

        assertThat(petModel.getStatus())
                .withFailMessage("No pet with status = " + status + " exists.")
                .isEqualTo(status);
    }

    @When("I call the pet deletion api with id = {int}")
    public void deletingThePetWithId(long id) {
        deletePetWithId(id);
    }

}
