package pages;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUploadPage extends PageObject {
    @FindBy(id = "file-upload")
    private WebElementFacade btnFileUploader;

    @FindBy(id = "file-submit")
    private WebElementFacade btnUpload;

    @FindBy(css = "#content > div > h3")
    private WebElementFacade lblHeader;

    @FindBy(id = "uploaded-files")
    private WebElementFacade uploadedFiles;

    public void selectFileToUpload() {
        btnFileUploader.sendKeys(System.getProperty("user.dir") + "/src/test/resources/data-files/UploadFile.txt");
    }

    public void uploadFile() {
        btnUpload.click();
        // The form POSTs to a new page, so wait for the result before reading anything.
        new WebDriverWait(this.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.textToBePresentInElementLocated(
                        By.cssSelector("#content h3"), "File Uploaded!"));
    }

    public String getHeaderText() {
        return lblHeader.getText();
    }

    public String getUploadedFileName() {
        return uploadedFiles.getText();
    }
}
