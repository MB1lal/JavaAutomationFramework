package steps.base;

import static utils.SharedStateConstants.FRONTEND.EXCEL_DATA;

import core.ScenarioContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.cucumber.suiteslicing.SerenityTags;

public class Hooks extends BaseSteps {

    @Before
    public void bootstrap(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@excelData")) {
            utils.ExcelReader excelReader = utils.ExcelReader.getInstance();
            try {
                Serenity.setSessionVariable(EXCEL_DATA).to(excelReader.readExcel("Input"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Before(order = 0)
    public void openBrowser() {
        // Serenity binds page-object elements against the current driver.
        // Warm it up before any step runs so the first scenario of the fork
        // doesn't construct pages against a driver that doesn't exist yet.
        Serenity.getDriver().get("about:blank");
    }

    @Before(order = 1)
    public void resetScenarioState() {
        ScenarioContext.reset();
    }

    @Before
    public void before() {
        SerenityTags.create().tagScenarioWithBatchingInfo();
    }
}
