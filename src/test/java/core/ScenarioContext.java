package core;

import io.restassured.response.Response;
import models.users.UserModel;

/**
 * Typed, thread-local state for the running scenario. Replaces the old
 * stringly-typed Serenity session variables (no casts, no key typos), and
 * because it is thread-local, parallel scenarios cannot see each other's
 * data. Reset before every scenario (see {@code Hooks}).
 */
public final class ScenarioContext {

    private static final ThreadLocal<ScenarioContext> CURRENT =
            ThreadLocal.withInitial(ScenarioContext::new);

    private long petId;
    private String petStatus;
    private Response petResponse;

    private int orderId;
    private Response orderResponse;

    private UserModel currentUser;
    private Response userResponse;

    private ScenarioContext() {
    }

    public static ScenarioContext current() {
        return CURRENT.get();
    }

    public static void reset() {
        CURRENT.remove();
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public String getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(String petStatus) {
        this.petStatus = petStatus;
    }

    public Response getPetResponse() {
        return petResponse;
    }

    public void setPetResponse(Response petResponse) {
        this.petResponse = petResponse;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Response getOrderResponse() {
        return orderResponse;
    }

    public void setOrderResponse(Response orderResponse) {
        this.orderResponse = orderResponse;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public Response getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(Response userResponse) {
        this.userResponse = userResponse;
    }
}
