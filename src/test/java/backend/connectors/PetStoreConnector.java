package backend.connectors;

import core.EnvSerenity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class PetStoreConnector {

    private RequestSpecification baseRequest() {
        return SerenityRest
                .with()
                .contentType(ContentType.JSON)
                .baseUri(EnvSerenity.basePetStoreURI);
    }

    public void placingAnOrder(String body) {
        baseRequest()
                .body(body)
                .post("/order")
                .then()
                .statusCode(200);
    }

    public Response fetchOrder(int orderId) {
        return baseRequest()
                  .get("/order/" + orderId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
    }

    public void fetchInvalidOrder(int orderId) {
         baseRequest()
                .get("/order/" + orderId)
                .then()
                .assertThat()
                .statusCode(404);
    }

    public void deleteOrderById(int orderId) {
         baseRequest()
                .delete("/order/" + orderId)
                .then()
                .assertThat()
                 .statusCode(200);
    }

}
