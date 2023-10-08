package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class NotificationsPage extends PageObject {
    @FindBy(css = "p a")
    private WebElementFacade linkClickHere;

    @FindBy(id = "flash")
    private WebElementFacade flashNotification;

    public void clickToShuffleNotification() {
        linkClickHere.click();
    }

    public String getNotificationText() {
        return flashNotification.waitUntilVisible().getText().replace("×", "").trim();
    }


}
