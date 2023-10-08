package connectors;

import core.EnvSerenity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import java.util.List;


import static utils.SharedStateConstants.BACKEND.PET_ID;

public class PetConnector {

    private RequestSpecification baseRequest() {
        return SerenityRest
                .with()
                .contentType(ContentType.JSON)
                .baseUri(EnvSerenity.basePetURI);
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

    public void updatePetDetails(String attribute, String attributeValue) {
        baseRequest()
                .header("Content-Type", ContentType.URLENC)
                .formParam(attribute, attributeValue)
                .post("/" + Serenity.sessionVariableCalled(PET_ID))
                .then()
                .statusCode(200)
                .extract().response();
    }
}
