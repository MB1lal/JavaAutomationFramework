package frontend.steps;

import core.EnvSerenity;
import frontend.ui.models.BrowserType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.core.Serenity;

import static utils.SharedStateConstants.FRONTEND.EXCEL_DATA;
import static utils.SharedStateConstants.FRONTEND.WEBDRIVER;

public class Hooks extends BaseSteps{
    @Before(value = "@Browser", order = 0)
    public void setupBrowser() {
        if(EnvSerenity.webDriver.equalsIgnoreCase("chrome"))
        {
            env.setDriver(BrowserType.CHROME);
        }
    }

    @Before(value = "@Browser", order = 1)
    public void startBrowser() throws Exception {
        sharedState.setWebDriver(browser.getChromeDriver(env.getDriver()));
        Serenity.setSessionVariable(WEBDRIVER).to(sharedState.getWebDriver());
    }

    @After(value = "@Browser", order = 0)
    public void closeBrowser() {
        sharedState.getWebDriver().quit();
    }

    @Before("@excelData")
    public void readExcelData() {
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
