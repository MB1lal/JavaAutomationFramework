package connectors;

import core.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import java.util.List;


public class PetConnector {

    private RequestSpecification baseRequest() {
        return SerenityRest
                .with()
                .contentType(ContentType.JSON)
                .baseUri(TestConfig.petUri());
    }

    public void addNewPet(String body) {
        baseRequest()
            .body(body)
            .post()
            .then()
            .statusCode(200)
            .extract().response();
    }

    public Response getPetById(int id) {
        return baseRequest()
                .get("/" + id);
    }

    public Response getPetStatus(List<String> status) {
        return baseRequest()
                .param("status", status)
                .get("/findByStatus")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public void deletePetWithId(int petId) {
        baseRequest()
                .delete("/" + petId)
                .then()
                .statusCode(200)
                .extract().response();
    }

    public void updatePetDetails(long petId, String attribute, String attributeValue) {
        baseRequest()
                .header("Content-Type", ContentType.URLENC)
                .formParam(attribute, attributeValue)
                .post("/" + petId)
                .then()
                .statusCode(200)
                .extract().response();
    }
}
