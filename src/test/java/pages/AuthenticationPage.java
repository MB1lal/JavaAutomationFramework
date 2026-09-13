package pages;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationPage extends PageObject {
    @FindBy(id = "username")
    private WebElementFacade txtUsername;

    @FindBy(id = "password")
    private WebElementFacade txtPassword;

    @FindBy(css = ".fa.fa-2x.fa-sign-in")
    private WebElementFacade btnLogin;

    @FindBy(css = ".icon-2x.icon-signout")
    private WebElementFacade btnLogout;

    private String loggedInText = "You logged into a secure area!";
    private String loggedOutText = "You logged out of the secure area!";

    public void enterUsernameAndPassword(String username, String password) {
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        btnLogin.click();
        // Both outcomes render a flash message, so wait for it instead of
        // asserting against a page that may still be navigating.
        new WebDriverWait(this.getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
    }

    public Boolean userIsLoggedIn() {
        return this.getDriver().getPageSource().contains(loggedInText);
    }

    public void clickLogout() {
        btnLogout.click();
        new WebDriverWait(this.getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.urlContains("/login"));
    }

    public Boolean userIsLoggedOut() {
        return this.getDriver().getPageSource().contains(loggedOutText);
    }
}
