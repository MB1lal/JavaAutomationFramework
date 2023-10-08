package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class HoversPage extends PageObject {
    @FindBy(css = ".figure:nth-child(3)")
    private WebElementFacade firstUser;

    @FindBy(css = ".figure:nth-child(4)")
    private WebElementFacade secondUser;

    @FindBy(css = ".figure:nth-child(5)")
    private WebElementFacade thirdUser;

    private final By profileName = By.cssSelector("h5");


    public void hoverOverAvatar(int userId) {
        Actions actions = new Actions(this.getDriver());
        switch(userId) {
            case 1:
                actions.moveToElement(firstUser).perform();
                break;
            case 2:
                actions.moveToElement(secondUser).perform();
                break;
            case 3:
                actions.moveToElement(thirdUser).perform();
                break;
        }
    }

    public String getProfileName(int userId) {
        return this.getDriver().findElements(profileName).get(userId).getText();
    }

}
