package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("page:HerokuURL")
public class HerokuMainPage extends PageObject {
    @FindBy(linkText = "Form Authentication")
    private WebElementFacade formAuthentication;

    @FindBy(linkText = "Checkboxes")
    private WebElementFacade checkBoxesPage;

    @FindBy(linkText = "Dropdown")
    private WebElementFacade dropdownPage;

    @FindBy(linkText = "Dynamic Loading")
    private WebElementFacade dynamicLoading;

    @FindBy(xpath = "//*[text() = 'File Download']")
    private WebElementFacade fileDownloadPage;

    @FindBy(xpath = "//*[text() = 'File Upload']")
    private WebElementFacade fileUploadPage;

    @FindBy(xpath = "//*[text() = 'Frames']")
    private WebElementFacade framesPage;

    @FindBy(xpath = "//*[text() = 'Hovers']")
    private WebElementFacade hoversPage;

    @FindBy(xpath = "//*[text() = 'JavaScript Alerts']")
    private WebElementFacade jsAlertsPage;

    @FindBy(xpath = "//*[text() = 'Multiple Windows']")
    private WebElementFacade multiWindowPage;

    @FindBy(xpath = "//*[text() = 'Notification Messages']")
    private WebElementFacade notificationsPage;

    public void navigateToPage(String pageName) {
        this.open();
        switch(pageName.toLowerCase()) {
            case "form authentication":
                formAuthentication.click();
                break;
            case "checkboxes":
                checkBoxesPage.click();
                break;
            case "dropdown":
                dropdownPage.click();
                break;
            case "dynamic loading":
                dynamicLoading.click();
                break;
            case "file download":
                fileDownloadPage.click();
                break;
            case "file upload":
                fileUploadPage.click();
                break;
            case "frames":
                framesPage.click();
                break;
            case "hovers":
                hoversPage.click();
                break;
            case "javascript alerts":
                jsAlertsPage.click();
                break;
            case "multiple windows":
                multiWindowPage.click();
                break;
            case "notification messages":
                notificationsPage.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid page specified");
        }
    }
}
