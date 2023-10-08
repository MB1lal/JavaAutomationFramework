package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;

public class DropdownPage extends PageObject {

    @FindBy(id = "dropdown")
    private WebElementFacade dropdownElement;

    private final Select dropdown = new Select(dropdownElement);

    public void selectDropdownOption(String option) {
        dropdown.selectByVisibleText(option);
    }

    public String getDropdownSelectedOption() { return dropdown.getFirstSelectedOption().toString(); }

}
