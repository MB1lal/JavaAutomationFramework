package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

public class MultiWindowPage extends PageObject {
    @FindBy(css = "div[class='example'] a")
    private WebElementFacade linkClickHere;

    @FindBy(css = "h3")
    private WebElementFacade lblH3;


    public void openClickHereLink()  {
        linkClickHere.click();
    }

    public void switchToTab(String tabId) {
        List<String> windowHandle = this.getDriver().getWindowHandles().stream().toList();
        switch (tabId.toLowerCase()) {
            case "newly opened":
                this.getDriver().switchTo().window(windowHandle.get(windowHandle.size()-1));
                break;
            case "previous":
                this.getDriver().switchTo().window(windowHandle.get(0));
                break;
            default:
                throw new IllegalArgumentException("Invalid argument specified");
        }
    }

    public String getHeaderText() {
        return lblH3.getText();
    }
}
