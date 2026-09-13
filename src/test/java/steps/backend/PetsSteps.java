package steps.backend;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.Collections;
import models.pet.PetModel;
import steps.base.BaseSteps;

public class PetsSteps extends BaseSteps {

    @Given("I add a new pet with status = {}")
    public void addANewPet(String status) throws IOException {
        PetModel petModel = createPetPayloadUsingFile();
        petModel.setId(uniquePetId());
        petModel.setStatus(status);
        addANewPet(petModel);
    }

    @When("I fetch the pet by id")
    public void fetchPetById() {
        getPetById(context().getPetId());
    }

    @When("I fetch pets by status")
    public void fetchPetsByStatus() {
        getPetStatus(Collections.singletonList(context().getPetStatus()));
    }

    @When("I delete the pet")
    public void deleteThePet() {
        deletePetWithId(context().getPetId());
    }

    @Then("the pet exists")
    public void petExists() {
        Response response = context().getPetResponse();
        assertThat(response.statusCode())
                .withFailMessage("The pet doesn't exist")
                .isEqualTo(200);
        assertThat(response.as(PetModel.class).getId())
                .withFailMessage("No pet with the expected id exists.")
                .isEqualTo(context().getPetId());
    }

    @Then("the pet does not exist")
    public void petDoesNotExist() {
        assertThat(context().getPetResponse().statusCode())
                .withFailMessage("The pet still exists")
                .isEqualTo(404);
    }

    @Then("The pet has status = {}")
    public void assertingPetWithStatus(String status) throws JsonProcessingException {
        Response response = context().getPetResponse();
        long petId = context().getPetId();
        // Tree model, not POJO mapping: the shared demo API contains records
        // with values outside Java int range.
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

    @And("I update the pet {} to {}")
    public void updatingPetDetails(String attribute, String attributeValue) {
        updatePetDetails(attribute, attributeValue);
    }
}
