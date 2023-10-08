package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AuthenticationPage extends PageObject {
    @FindBy(id ="username")
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

    public void clickLogin(){
        btnLogin.click();
    }

    public Boolean userIsLoggedIn() {
        return this.getDriver().getPageSource().contains(loggedInText);
    }
    public void clickLogout() {btnLogout.click();}

    public Boolean userIsLoggedOut() {
        return this.getDriver().getPageSource().contains(loggedOutText);
    }
}
