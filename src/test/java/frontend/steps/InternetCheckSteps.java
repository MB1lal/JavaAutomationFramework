package frontend.steps;

import io.cucumber.java.en.Given;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InternetCheckSteps extends BaseSteps{

    @Given("User has internet connectivity")
    public void checkInternetConnectivity() throws IOException {
        logger.info("Checking internet connectivity");
        URL url = new URL("https://www.google.com/");
        URLConnection connection = url.openConnection();
        boolean isConnected;
        try {
            connection.connect();
            isConnected = true;
        }
        catch (Exception e) {
            isConnected = false;
        }

        assertThat(isConnected).as("Internet not connected").isTrue();
    }

}
