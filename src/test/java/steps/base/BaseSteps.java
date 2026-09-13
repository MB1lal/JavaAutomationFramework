package steps.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import connectors.PetConnector;
import connectors.PetStoreConnector;
import connectors.UserConnector;
import core.ScenarioContext;
import core.TestConfig;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import models.pet.PetModel;
import models.store.PetStoreModel;
import models.users.UserModel;
import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public abstract class BaseSteps {

    public static final ObjectMapper objectMapper = new ObjectMapper();
    private final PetConnector petConnector = new PetConnector();
    private final PetStoreConnector petStoreConnector = new PetStoreConnector();
    private final UserConnector userConnector = new UserConnector();

    public static final Logger logger = LogManager.getLogger(BaseSteps.class);

    public utils.ExcelReader excelReader = utils.ExcelReader.getInstance();

    protected String downloadPath = System.getProperty("user.dir") + "/src/test-output/downloads/";

    // EasyRandom instances are not shared between parallel scenarios.
    private static final ThreadLocal<EasyRandom> RANDOM =
            ThreadLocal.withInitial(() -> new EasyRandom(new EasyRandomParameters()
                    .seed(new Random().nextLong())
                    // sensible string length
                    .stringLengthRange(5, 50)));

    protected static ScenarioContext context() {
        return ScenarioContext.current();
    }

    /** Unique id per call, so parallel scenarios never share test data. */
    protected static long uniquePetId() {
        return ThreadLocalRandom.current().nextLong(100_000L, 9_999_999L);
    }

    protected static int uniqueOrderId() {
        return ThreadLocalRandom.current().nextInt(10_000, 999_999);
    }

    public PetModel createNewPetPayload() {
        return RANDOM.get().nextObject(PetModel.class);
    }

    public PetModel createPetPayloadUsingFile() throws IOException {
        return getStaticBody(PetModel.class, TestConfig.petFileBodiesRoot() + "new-pet.json");
    }

    public PetStoreModel createPetStorePayload() {
        PetStoreModel petStoreModel = new PetStoreModel();
        Faker faker = new Faker();

        petStoreModel.setId(faker.random().nextInt(0, 1000));
        petStoreModel.setPetId(faker.random().nextInt(0, Integer.MAX_VALUE));
        petStoreModel.setQuantity(4);

        return petStoreModel;
    }

    public static <T> T getStaticBody(Class<T> tClass, String path) throws IOException {
        return objectMapper.readValue(new File(path), tClass);
    }

    public void addANewPet(PetModel petModel) {

        context().setPetId(petModel.getId());
        context().setPetStatus(petModel.getStatus());
        petConnector.addNewPet(petModel.toJson());
    }

    public void getPetById(long petId) {
        context().setPetResponse(petConnector.getPetById((int) petId));
    }

    public void getPetStatus(List<String> status) {
        context().setPetResponse(petConnector.getPetStatus(status));
    }

    public void deletePetWithId(long petId) {
        petConnector.deletePetWithId((int) petId);
    }

    public void updatePetDetails(String attribute, String attributeValue) {
        petConnector.updatePetDetails(context().getPetId(), attribute, attributeValue);
    }

    public void placePetStoreOrder(PetStoreModel petStoreModel) {
        context().setOrderId(petStoreModel.getId());
        petStoreConnector.placingAnOrder(petStoreModel.toJson());
    }

    public void fetchPetStoreOrderDetails(int orderId) {
        context().setOrderResponse(petStoreConnector.fetchOrder(orderId));
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
        userModel.setId(faker.random().nextInt(0, Integer.MAX_VALUE));
        userModel.setUsername(faker.credentials().username());
        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword(faker.credentials().password(10, 20));
        userModel.setPhone(faker.phoneNumber().cellPhone());
        userModel.setUserStatus(faker.random().nextInt(3));

        context().setCurrentUser(userModel);

        return userModel;
    }

    public void verifyUserExists() {
        context()
                .setUserResponse(
                        userConnector.getUser(context().getCurrentUser().getUsername()));
    }

    public void loginUser() {
        UserModel user = context().getCurrentUser();
        context().setUserResponse(userConnector.loginExistingUser(user.getUsername(), user.getPassword()));
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
}
