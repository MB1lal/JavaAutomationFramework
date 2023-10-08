package steps.frontend;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.FileUploadPage;
import steps.base.BaseSteps;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUploadSteps extends BaseSteps {
    FileUploadPage fileUploadPage = new FileUploadPage();

    @Given("I select a file to upload")
    public void selectingAFileToUpload() {
        logger.info("Uploading file");
        fileUploadPage.selectFileToUpload();
        fileUploadPage.uploadFile();
    }

    @Then("the file should be uploaded successfully")
    public void verifyFileIsDownloaded() {
        logger.info("Verifying file is successfully uploaded");
        assertThat(fileUploadPage.getHeaderText()).as("File not uploaded").isEqualTo("File Uploaded!");
        assertThat(fileUploadPage.getUploadedFileName()).as("Incorrect file uploaded").contains("UploadFile.txt");
    }
}
