package steps.base;

import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.cucumber.suiteslicing.SerenityTags;

import static utils.SharedStateConstants.FRONTEND.EXCEL_DATA;

public class Hooks extends BaseSteps{

    @Before
    public void bootstrap(Scenario scenario) {
        if(scenario.getSourceTagNames().contains("@excelData")) {
            utils.ExcelReader excelReader = utils.ExcelReader.getInstance();
            try
            {
                Serenity.setSessionVariable(EXCEL_DATA).to(excelReader.readExcel("Input"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Before
    public void before() {
        SerenityTags.create().tagScenarioWithBatchingInfo();
    }
}
