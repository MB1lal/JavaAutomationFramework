package backend.connectors;

import core.EnvSerenity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class PetConnector {

    private RequestSpecification baseRequest() {
        return SerenityRest
                .with()
                .contentType(ContentType.JSON)
                .baseUri(EnvSerenity.basePetURI);
    }

    public Response addNewPet(String body) {
        return baseRequest()
                .body(body)
                .post();
    }

}
