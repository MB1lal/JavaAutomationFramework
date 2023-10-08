package steps.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import connectors.PetConnector;
import connectors.PetStoreConnector;
import connectors.UserConnector;
import core.EnvSerenity;
import models.DownloadedJson;
import models.pet.PetModel;
import models.store.PetStoreModel;
import models.users.UserModel;
import net.serenitybdd.core.Serenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static utils.SharedStateConstants.BACKEND.PET.PET_RESPONSE;
import static utils.SharedStateConstants.BACKEND.PET.PET_STATUS;
import static utils.SharedStateConstants.BACKEND.PET_ID;
import static utils.SharedStateConstants.BACKEND.PET_STORE.PET_ORDER_ID;
import static utils.SharedStateConstants.BACKEND.PET_STORE.PET_STORE_RESPONSE;
import static utils.SharedStateConstants.BACKEND.USERS.EMAIL;
import static utils.SharedStateConstants.BACKEND.USERS.FIRST_NAME;
import static utils.SharedStateConstants.BACKEND.USERS.LAST_NAME;
import static utils.SharedStateConstants.BACKEND.USERS.PASSWORD;
import static utils.SharedStateConstants.BACKEND.USERS.PHONE;
import static utils.SharedStateConstants.BACKEND.USERS.STATUS;
import static utils.SharedStateConstants.BACKEND.USERS.USERNAME;
import static utils.SharedStateConstants.BACKEND.USERS.USER_ID;
import static utils.SharedStateConstants.BACKEND.USERS.USER_RESPONSE;


public abstract class BaseSteps {

    public static final ObjectMapper objectMapper = new ObjectMapper();
    private final PetConnector petConnector = new PetConnector();
    private final PetStoreConnector petStoreConnector = new PetStoreConnector();
    private final UserConnector userConnector = new UserConnector();

    public static final Logger logger = LogManager.getLogger(BaseSteps.class);

    public utils.ExcelReader excelReader = utils.ExcelReader.getInstance();

    protected String downloadPath = System.getProperty("user.dir") + "/src/test-output/downloads/";

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
        return getStaticBody(
            PetModel.class, EnvSerenity.petFileBodiesRoot + "new-pet.json");
    }

    public PetStoreModel createPetStorePayload() {
        PetStoreModel petStoreModel = new PetStoreModel();
        Faker faker = new Faker();

        petStoreModel.setId(faker.random().nextInt(0,1000));
        petStoreModel.setPetId(faker.hashCode());
        petStoreModel.setQuantity(4);


        return petStoreModel;
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

    public void fetchDeletedOrder(int orderId) {
        petStoreConnector.fetchInvalidOrder(orderId);
    }

    public void deleteOrderById(int orderId) {
        petStoreConnector.deleteOrderById(orderId);
    }

    public UserModel createUserPayLoad() {
        UserModel userModel = new UserModel();
        Faker faker = new Faker();
        userModel.setId(faker.hashCode());
        userModel.setUsername(faker.name().username());
        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword(faker.internet().password());
        userModel.setPhone(faker.phoneNumber().cellPhone());
        userModel.setUserStatus(faker.random().nextInt(3));


        Serenity.setSessionVariable(USERNAME).to(userModel.getUsername());
        Serenity.setSessionVariable(PASSWORD).to(userModel.getPassword());
        Serenity.setSessionVariable(EMAIL).to(userModel.getEmail());
        Serenity.setSessionVariable(PHONE).to(userModel.getPhone());
        Serenity.setSessionVariable(FIRST_NAME).to(userModel.getFirstName());
        Serenity.setSessionVariable(LAST_NAME).to(userModel.getLastName());
        Serenity.setSessionVariable(STATUS).to(userModel.getUserStatus());
        Serenity.setSessionVariable(USER_ID).to(userModel.getId());


        return userModel;
    }

    public void verifyUserExists() {
        Serenity.setSessionVariable(USER_RESPONSE).to(
            userConnector.getUser(Serenity.sessionVariableCalled(USERNAME)));
    }

    public void loginUser() {
        Serenity.setSessionVariable(USER_RESPONSE).to(
                userConnector.loginExistingUser(
                        Serenity.sessionVariableCalled(USERNAME),
                        Serenity.sessionVariableCalled(PASSWORD)
                )
        );
    }

    public void logoutUser() {
        userConnector.logoutUser();
    }

    protected void deleteDownloadsFolder(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            if (folder.isDirectory()) {
                // Delete all files and subdirectories in the folder
                Arrays.stream(folder.listFiles()).forEach(file -> {
                    if (file.isDirectory()) {
                        deleteDownloadsFolder(file.getAbsolutePath());
                    } else {
                        file.delete();
                    }
                });
            }
            // Delete the empty folder
            folder.delete();
            logger.info("Folder deleted successfully.");
        } else {
            logger.info("Folder does not exist.");
        }
    }

    protected String readJsonFile(String filePath) {
        try {
            return Arrays.toString(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            return null;
        }
    }

    protected DownloadedJson parseJson(String jsonString) {
        return new Gson().fromJson(jsonString, DownloadedJson.class);
    }


}
