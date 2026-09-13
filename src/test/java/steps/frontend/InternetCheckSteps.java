package steps.frontend;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.cucumber.java.en.Given;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import steps.base.BaseSteps;

public class InternetCheckSteps extends BaseSteps {

    @Given("User has internet connectivity")
    public void checkInternetConnectivity() throws IOException {
        URL url = new URL("https://www.google.com/");
        URLConnection connection = url.openConnection();
        boolean isConnected;
        try {
            connection.connect();
            isConnected = true;
        } catch (Exception e) {
            isConnected = false;
        }

        assertThat(isConnected).as("Internet not connected").isTrue();
    }
}
