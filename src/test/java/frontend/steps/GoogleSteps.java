package frontend.steps;

import frontend.pages.GooglePages;
import frontend.pages.IMDBPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import java.util.List;
import static utils.SharedStateConstants.FRONTEND.EXCEL_DATA;
import static utils.SharedStateConstants.FRONTEND.LINK_TEXT;

public class GoogleSteps extends BaseSteps{

    GooglePages googlePages;
    IMDBPages iMDBPages;
    List<List<String>> excelData;

    @Given("User navigates to Google")
    public void openingABrowser() {
        googlePages.open();
    }

    @Then("The page is loaded")
    public void assertingThatPageIsLoaded() {
        googlePages.pageHasLogo();
    }

    @And("User searches for value in {string} of provided sheet")
    public void searchThePageForValue(String sheetIndex) {
        excelData = Serenity.sessionVariableCalled(EXCEL_DATA);
        int index1 = Integer.parseInt(String.valueOf(sheetIndex.charAt(1))) - 1;
        int index2 = excelReader.getColumnIndex(sheetIndex);
        String searchString = excelData.get(index1).get(index2);
        googlePages.enterDataIntoSearchBox(searchString);
        googlePages.clickOnSearchButton();
    }

    @And("User finds the link present in {string} of provided sheet")
    public void findTheLinkOnPage(String sheetIndex) {
        int index1 = Integer.parseInt(String.valueOf(sheetIndex.charAt(1))) - 1;
        int index2 = excelReader.getColumnIndex(sheetIndex);
        String linkText = excelData.get(index1).get(index2);
        googlePages.containsText(linkText);
        Serenity.setSessionVariable(LINK_TEXT).to(linkText);
    }

    @And("User right clicks on the link")
    public void rightClickOnThelink() {
        String linkText = Serenity.sessionVariableCalled(LINK_TEXT);
//        googlePages.rightClickOnThelink(linkText);
        googlePages.saveTheLinkedURL(linkText);
    }

    @And("User opens the link in a new tab")
    public void clickOpenInNewTab() {
        String linkText = Serenity.sessionVariableCalled(LINK_TEXT);
        googlePages.openLinkInANewTab(linkText);
        iMDBPages.pageHasLogo();
    }




}
