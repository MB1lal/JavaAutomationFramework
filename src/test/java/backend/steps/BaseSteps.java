package backend.steps;

import backend.connectors.PetConnector;
import backend.models.PetModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.core.Serenity;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import static utils.SharedStateConstants.BACKEND.PET_ID;
import static utils.SharedStateConstants.BACKEND.PET_RESPONSE;
import static utils.SharedStateConstants.BACKEND.PET_STATUS;

public abstract class BaseSteps {

    public static final ObjectMapper objectMapper = new ObjectMapper();
    private final PetConnector petConnector = new PetConnector();

    public EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(new Random().nextLong())
            //sensible string length
                    .stringLengthRange(5,50)
    );

    public PetModel createNewPetPayload() {
        return random.nextObject(PetModel.class);
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

    public void getPetStatus(String status) {
        //ToDo - Fix the following method to accommodate multiple statuses
        Serenity.setSessionVariable(PET_RESPONSE).to(
                petConnector.getPetStatus(status));
    }

    public void deletePetWithId(long petId) {
        petConnector.deletePetWithId((int) petId);
    }



}
