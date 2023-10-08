package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class CheckboxesPage extends PageObject {

    @FindBy(css = "#checkboxes > input:first-of-type")
    private WebElementFacade checkbox1;

    @FindBy(css = "#checkboxes  > input:nth-of-type(2)")
    private WebElementFacade checkbox2;

    public void clickCheckbox(int index) {
        if(index == 1) {
            checkbox1.click();
        }
        if(index == 2) {
            checkbox2.click();
        }
    }

    public Boolean isChecked(int index) {
        if(index == 1) {
            return checkbox1.isSelected();
        }
        if(index == 2) {
            return checkbox2.isSelected();
        }
        return false;
    }
}
