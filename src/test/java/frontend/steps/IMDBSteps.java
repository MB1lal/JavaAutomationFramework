package frontend.steps;

import frontend.pages.IMDBPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.List;

public class IMDBSteps extends BaseSteps{

    IMDBPages imdbPages;

    @And("User scrolls down to find the {string} text")
    public void scrollsDownToElement(String elementText) {
        imdbPages.scrollToText(elementText);
    }

    @And("User clicks on the {string}")
    public void clickOnElementWithText(String elementText) {
        imdbPages.clickScrolledElement(elementText);
        imdbPages.pageHasLogo();
    }

    @Then("User picks the following details:")
    public void saveDetailsIntoTable(List<String> information) {

    }
}
