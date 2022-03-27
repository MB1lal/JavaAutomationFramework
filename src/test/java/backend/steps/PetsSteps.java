package backend.steps;

import backend.models.PetModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SharedStateConstants.BACKEND.PET_ID;
import static utils.SharedStateConstants.BACKEND.PET_RESPONSE;
import static utils.SharedStateConstants.BACKEND.PET_STATUS;

public class PetsSteps extends BaseSteps {

    @Given("I add the pet with {} = {}")
    public void addAPet(String param, String paramValue) throws IOException {
        PetModel petModel = createPetPayloadUsingFile();
        switch(param.toLowerCase()) {
            case "id" -> {
                logger.info("Setting petId into the payload.");
                petModel.setId(Long.parseLong(paramValue));
            }
            case "status" -> {
                logger.info("Setting status into the payload.");
                petModel.setStatus(paramValue);
            }
        }
        addANewPet(petModel);
    }

    @And("The pet with id = {int} {}")
    public void assertingPetWithId(int petId, String result) {
        Response response = Serenity.sessionVariableCalled(PET_RESPONSE);
        logger.info("Asserting the petId response.");
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
        logger.info("Calling the pet api with " + callingParameter + ".");
        switch (callingParameter.toLowerCase()) {
            case "id" -> getPetById(Serenity.sessionVariableCalled(PET_ID));
            case "status" -> getPetStatus(Collections.singletonList(Serenity.sessionVariableCalled(PET_STATUS)));
        }
    }

    @Then("The pet has status = {}")
    public void assertingPetWithStatus(String status) {
        Response response = Serenity.sessionVariableCalled(PET_RESPONSE);
        PetModel petModel;
        if (response.getBody().asString().startsWith("{")) {
            petModel = response.as(PetModel.class);
        }
        else {
            PetModel[] petResponse = response.as(PetModel[].class);
            logger.info("Asserting the pet status in response.");
            assertThat(Arrays.stream(petResponse).anyMatch(pets -> pets.getId() == (long) Serenity.sessionVariableCalled(PET_ID)))
                    .withFailMessage("No pet with id = " + Serenity.sessionVariableCalled(PET_ID) + " exists.")
                    .isTrue();

            petModel = Arrays.stream(petResponse).filter(pets -> pets.getId() == (long) Serenity.sessionVariableCalled(PET_ID)).findFirst().get();
        }
        assertThat(petModel.getStatus())
                .withFailMessage("No pet with status = " + status + " exists.")
                .isEqualTo(status);
    }

    @When("I call the pet deletion api with id = {int}")
    public void deletingThePetWithId(long id) {
        logger.info("Deletion api is called for id = " + id);
        deletePetWithId(id);
    }

    @And("I update the pet {} to {}")
    public void updatingPetDetails(String attribute,String attributeValue) {
        updatePetDetails(attribute, attributeValue);
    }

}
