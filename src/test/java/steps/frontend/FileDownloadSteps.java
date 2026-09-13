package steps.frontend;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FileDownloadPage;
import steps.base.BaseSteps;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileDownloadSteps extends BaseSteps {

    private FileDownloadPage fileDownloadPage = new FileDownloadPage();
    private String fileName;

    @When("I download the first listed file")
    public void downloadFirstFile() {
        logger.info("Downloading the first listed file");
        fileName = fileDownloadPage.downloadFirstAvailableFile(downloadPath);
    }

    @Then("the file should be downloaded successfully")
    public void verifyFileIsDownloaded() throws Exception {
        logger.info("Verifying the file is downloaded");
        Path path = Paths.get(downloadPath, fileName);
        assertThat(Files.exists(path)).as("File " + fileName + " doesn't exist in the folder").isTrue();
        assertThat(Files.size(path)).as("Downloaded file is empty").isGreaterThan(0);
        logger.info("The file exists and has content");
    }
}
