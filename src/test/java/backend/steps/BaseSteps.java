package backend.steps;

import backend.connectors.PetConnector;
import backend.connectors.PetStoreConnector;
import backend.models.pet.PetModel;
import backend.models.store.PetStoreModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.EnvSerenity;
import net.serenitybdd.core.Serenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import static utils.SharedStateConstants.BACKEND.PET_ID;
import static utils.SharedStateConstants.BACKEND.PET_ORDER_ID;
import static utils.SharedStateConstants.BACKEND.PET_RESPONSE;
import static utils.SharedStateConstants.BACKEND.PET_STATUS;
import static utils.SharedStateConstants.BACKEND.PET_STORE_RESPONSE;

public abstract class BaseSteps {

    public static final ObjectMapper objectMapper = new ObjectMapper();
    private final PetConnector petConnector = new PetConnector();
    private final PetStoreConnector petStoreConnector = new PetStoreConnector();

    public static final Logger logger = LogManager.getLogger(BaseSteps.class);

    public EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(new Random().nextLong())
            //sensible string length
                    .stringLengthRange(5,50)
    );

    public PetModel createNewPetPayload() {
        return random.nextObject(PetModel.class);
    }
    public PetModel createPetPayloadUsingFile() throws IOException {
        logger.info("Creating a pet payload using sample json file");
        return getStaticBody(
            PetModel.class, EnvSerenity.petFileBodiesRoot + "new-pet.json");
    }

    public PetStoreModel createPetStorePayload() {
//        return getStaticBody(
//                PetStoreModel.class, EnvSerenity.petFileBodiesRoot + "new-pet-store.json");
        return random.nextObject(PetStoreModel.class);
    }

    public static <T> T getStaticBody(Class<T> tClass, String path) throws IOException {
        return objectMapper.readValue(new File(path), tClass);
    }

    public void addANewPet(PetModel petModel) {

        Serenity.setSessionVariable(PET_ID).to(petModel.getId());
        Serenity.setSessionVariable(PET_STATUS).to(petModel.getStatus());
        petConnector.addNewPet(petModel.toJson());
    }

    public void getPetById(long petId) {
        Serenity.setSessionVariable(PET_RESPONSE).to(
            petConnector.getPetById((int) petId));
    }

    public void getPetStatus(List<String> status) {
        Serenity.setSessionVariable(PET_RESPONSE).to(
                petConnector.getPetStatus(status));
    }

    public void deletePetWithId(long petId) {
        petConnector.deletePetWithId((int) petId);
    }

    public void updatePetDetails(String attribute, String attributeValue) {
        petConnector.updatePetDetails(attribute, attributeValue);

    }

    public void placePetStoreOrder(PetStoreModel petStoreModel) {
        Serenity.setSessionVariable(PET_ORDER_ID).to(petStoreModel.getId());
        petStoreConnector.placingAnOrder(petStoreModel.toJson());
    }

    public void fetchPetStoreOrderDetails(int orderId) {
        Serenity.setSessionVariable(PET_STORE_RESPONSE).
                to(petStoreConnector.fetchOrder(orderId));
    }

}
