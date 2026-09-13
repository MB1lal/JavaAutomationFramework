package steps.backend;

import models.pet.PetModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import steps.base.BaseSteps;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.SharedStateConstants.BACKEND.PET.PET_RESPONSE;
import static utils.SharedStateConstants.BACKEND.PET.PET_STATUS;
import static utils.SharedStateConstants.BACKEND.PET_ID;

public class PetsSteps extends BaseSteps {

    @Given("I add the pet with {} = {}")
    public void addAPet(String param, String paramValue) throws IOException {
        PetModel petModel = createPetPayloadUsingFile();
        switch(param.toLowerCase()) {
            case "id" -> {
                petModel.setId(Long.parseLong(paramValue));
            }
            case "status" -> {
                petModel.setStatus(paramValue);
            }
        }
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
            case "status" -> getPetStatus(java.util.Collections.singletonList(Serenity.sessionVariableCalled(PET_STATUS)));
        }
    }

    @Then("The pet has status = {}")
    public void assertingPetWithStatus(String status) throws JsonProcessingException {
        Response response = Serenity.sessionVariableCalled(PET_RESPONSE);
        long petId = Serenity.sessionVariableCalled(PET_ID);
        // Read status via tree model: the shared demo API contains records
        // with values outside Java int range, which breaks full POJO mapping.
        String body = response.getBody().asString().trim();
        String actualStatus = null;
        if (body.startsWith("{")) {
            actualStatus = objectMapper.readTree(body).path("status").asText(null);
        } else {
            for (JsonNode pet : objectMapper.readTree(body)) {
                if (pet.path("id").asLong() == petId) {
                    actualStatus = pet.path("status").asText(null);
                    break;
                }
            }
        }
        assertThat(actualStatus)
                .withFailMessage("No pet with id = " + petId + " exists.")
                .isNotNull();
        assertThat(actualStatus)
                .withFailMessage("No pet with status = " + status + " exists.")
                .isEqualTo(status);
    }

    @When("I call the pet deletion api with id = {int}")
    public void deletingThePetWithId(long id) {
        deletePetWithId(id);
    }

    @And("I update the pet {} to {}")
    public void updatingPetDetails(String attribute,String attributeValue) {
        updatePetDetails(attribute, attributeValue);
    }

}
