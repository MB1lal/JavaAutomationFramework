package steps.backend;

import models.store.PetStoreModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import steps.base.BaseSteps;


import static org.assertj.core.api.Assertions.assertThat;

public class PetStoreSteps extends BaseSteps {


    @Given("I place an order on the pet store")
    public void placingOrderOnPetStore() {
        PetStoreModel petStoreModel = createPetStorePayload();
        petStoreModel.setId(uniqueOrderId());
        placePetStoreOrder(petStoreModel);
    }

    @When("I fetch the order")
    public void fetchTheOrder() {
        fetchPetStoreOrderDetails(context().getOrderId());
    }

    @Then("The order is successfully placed")
    public void assertingOrderIsSuccessfullyPlaced() {
        Response response = context().getOrderResponse();
        PetStoreModel petStoreModel = response.as(PetStoreModel.class);

        assertThat(petStoreModel.getId())
                .withFailMessage("The order Id is not found.")
                .isEqualTo(context().getOrderId());
    }

    @When("I delete the order")
    public void deleteTheOrder() {
        deleteOrderById(context().getOrderId());
    }

    @And("The order shouldn't exist")
    public void assertOrderDoesNotExist() {
        fetchDeletedOrder(context().getOrderId());
    }


}
