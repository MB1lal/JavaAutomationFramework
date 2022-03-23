package frontend.steps;

import frontend.pages.IMDBPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import utils.ExcelWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.SharedStateConstants.FRONTEND.CAST_AND_CREW;
import static utils.SharedStateConstants.FRONTEND.EXCEL_DATA;

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
        List<List<String>> castDetails = new ArrayList<>();
        castDetails.add(new ArrayList<>());
        castDetails.get(0).addAll(information);
        castDetails.addAll(imdbPages.getCastTableData());
        Serenity.setSessionVariable(CAST_AND_CREW).to(castDetails);
    }

    @And("User exports all the data into {string} sheet")
    public void exportDataToExcel(String sheetName) throws IOException, InvalidFormatException {
        ExcelWriter excelWriter = ExcelWriter.getInstance();
        excelWriter.writeToExcel(Serenity.sessionVariableCalled(CAST_AND_CREW),
                "testData",
                sheetName);
    }
}
