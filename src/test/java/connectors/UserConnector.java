package connectors;

import core.EnvSerenity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class UserConnector {

    private RequestSpecification baseRequest() {
        return SerenityRest
                .with()
                .contentType(ContentType.JSON)
                .baseUri(EnvSerenity.userURI);
    }

    public Response getUser(String username) {
        return baseRequest()
                .get("/" + username)
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response loginExistingUser(String username, String password) {
        return baseRequest()
                .formParam("username", username)
                .formParam("password", password)
                .get("/login")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public void logoutUser() {
        baseRequest()
                .get("/logout")
                .then()
                .statusCode(200);
    }

    public void createNewUser(String user) {
        baseRequest()
                .body(user)
                .post()
                .then()
                .statusCode(200);
    }

    public Response deleteUser(String username) {
        return baseRequest()
                .delete("/" + username);
    }
}
