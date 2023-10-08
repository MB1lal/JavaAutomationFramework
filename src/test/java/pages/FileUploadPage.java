package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

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
    }

    public String getHeaderText() {
        return lblHeader.getText();
    }

    public String getUploadedFileName() {
        return uploadedFiles.getText();
    }

}
