package steps.frontend;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.DownloadedJson;
import pages.FileDownloadPage;
import steps.base.BaseSteps;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileDownloadSteps extends BaseSteps {

    private FileDownloadPage fileDownloadPage = new FileDownloadPage();
    private String fileName;
    @When("I download the file {}")
    public void downloadIsPressed(String fileName) {
        this.fileName = fileName;
        logger.info("Downloading the file " + fileName);
        fileDownloadPage.downloadFile(fileName);

    }

    @Then("the file should be downloaded successfully")
    public void verifyFileIsDownloaded() {
        logger.info("Verifying the file is downloaded");
        Path path = Paths.get(downloadPath, fileName);
        assertThat(Files.exists(path)).as("File " + fileName + " doesn't exist in the folder").isTrue();
        logger.info("The file exists");
    }

    @And("I should validate the content of the downloaded file")
    public void verifyContentsOfFile() {
        logger.info("Verifying the contents of file");
        String jsonString = readJsonFile(downloadPath + "/example.json");
        if (jsonString != null) {
            DownloadedJson jsonData = parseJson(jsonString);
            assertThat(jsonData.email).as("email is blank").isNotBlank();
            assertThat(jsonData.email1).as("email1 is blank").isNotBlank();
            assertThat(jsonData.password).as("password is blank").isNotBlank();
            assertThat(jsonData.password1).as("password1 is blank").isNotBlank();
        } else {
            logger.error("Error reading JSON file");
        }
    }
}
