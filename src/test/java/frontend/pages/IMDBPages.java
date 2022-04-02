package frontend.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IMDBPages extends PageObject {

    WebDriver driver = super.getDriver();

    @FindBy(id = "home_img")
    WebElementFacade pageLogo;

    @FindBy(css = "input[class='ipc-metadata-list-item__label ipc-metadata-list-item__label--link']")
    WebElementFacade allCastAndCrew;

    @FindBy(xpath = "//*[@id='fullcredits_content']/table[3]/tbody/tr")
    WebElement castTable;

    By castTableRow = By.xpath("//*[@id='fullcredits_content']/table[3]/tbody/tr");

    public void pageHasLogo() {
        pageLogo.withTimeoutOf(Duration.ofSeconds(3));
        pageLogo.isDisplayed();
    }

    public void scrollToText(String elementText) {
        WebElement targetElement = find(By.linkText(elementText));
        ((JavascriptExecutor) super.getDriver()).executeScript("arguments[0].scrollIntoView(true);", targetElement);
    }

    public void clickScrolledElement(String elementText) {
        WebElement targetElement = find(By.linkText(elementText));
        targetElement.click();
    }

    public List<List<String>> getCastTableData() {
        List<List<String>> castTableData = new ArrayList<>();
        List<WebElement> tableData = driver.findElements(castTableRow);
        List<String> rows = new ArrayList<>();

        for (WebElement tableDatum : tableData) {
            if (!tableDatum.getAttribute("outerText").equalsIgnoreCase("")) {
                rows.add(tableDatum.getAttribute("outerText"));
            }
        }
       for (int i=0; i<rows.size(); i++) {
           Scanner s = new Scanner(rows.get(i)).useDelimiter("[\\t\\n]");
           castTableData.add(new ArrayList<>());
           for(int j=0;j<4;j++) {
               String rowText = s.next();
               if(!rowText.equals("...")) {
                   castTableData.get(i).add(rowText);
               }
           }
       }
        return castTableData;
    }
}
