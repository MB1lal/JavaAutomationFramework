package backend.connectors;

import core.EnvSerenity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import static org.assertj.core.api.Assertions.assertThat;

public class PetConnector {

    private RequestSpecification baseRequest() {
        return SerenityRest
                .with()
                .contentType(ContentType.JSON)
                .baseUri(EnvSerenity.basePetURI);
    }

    public void addNewPet(String body) {
        Response response = baseRequest()
                .body(body)
                .post();

        assertThat(response.statusCode()).isEqualTo(200);
    }

    public Response getPetById(int id) {
        return baseRequest()
                .get("/" + id);
    }

    public Response getPetStatus(String status) {
        Response response = baseRequest()
                .param("status", status)
                .get("/findByStatus");

        assertThat(response.statusCode()).isEqualTo(200);
        return response;
    }

    public void deletePetWithId(int petId) {
        Response response = baseRequest()
                .delete("/" + petId);

        assertThat(response.statusCode())
                .as("Status code for deletion api")
                .isEqualTo(200);
    }
}
