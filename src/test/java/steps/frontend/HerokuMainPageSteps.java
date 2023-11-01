package steps.frontend;

import io.cucumber.java.en.Given;
import pages.HerokuMainPage;
import steps.base.BaseSteps;

public class HerokuMainPageSteps extends BaseSteps {
    private HerokuMainPage herokuPage = new HerokuMainPage();

    @Given("I am on the {} page")
    public void navigateToXPage(String pageName) {
        logger.debug("Browser: " + herokuPage.getDriver());
        logger.info("Navigating to " + pageName);
        herokuPage.navigateToPage(pageName);
    }
}
