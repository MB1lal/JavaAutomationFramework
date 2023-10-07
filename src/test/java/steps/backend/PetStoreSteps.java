package steps.backend;

import models.store.PetStoreModel;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import steps.base.BaseSteps;


import static org.assertj.core.api.Assertions.assertThat;
import static utils.SharedStateConstants.BACKEND.PET_STORE.PET_ORDER_ID;
import static utils.SharedStateConstants.BACKEND.PET_STORE.PET_STORE_RESPONSE;

public class PetStoreSteps extends BaseSteps {


    @Given("I place an order on pet store with id = {int}")
    public void placingOrderOnPetStore(int orderId) {
        PetStoreModel petStoreModel = createPetStorePayload();
        petStoreModel.setId(orderId);
        placePetStoreOrder(petStoreModel);
    }

    @When("I fetch the order using id = {int}")
    public void petStoreOrderStatusIsCalled(int orderId) {
        fetchPetStoreOrderDetails(orderId);
    }

    @Then("The order is successfully placed")
    public void assertingOrderIsSuccessfullyPlaced() {
        Response response = Serenity.sessionVariableCalled(PET_STORE_RESPONSE);
        PetStoreModel petStoreModel = response.as(PetStoreModel.class);

        assertThat(petStoreModel.getId())
                .withFailMessage("The order Id is not found.")
                .isEqualTo(Serenity.sessionVariableCalled(PET_ORDER_ID));
    }

    @When("I delete the order by id = {int}")
    public void deleteByOrderId(int orderId) {
        deleteOrderById(orderId);
    }

    @And("The order with id = {int} shouldn't exist")
    public void assertOrderDoesNotExist(int orderId) {
        fetchDeletedOrder(orderId);
    }


}
