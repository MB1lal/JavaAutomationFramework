package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;

public class DropdownPage extends PageObject {

    @FindBy(id = "dropdown")
    private WebElementFacade dropdownElement;

    private Select dropdown;

    private void initialiseDropdown() {
        dropdown = new Select(dropdownElement);
    }

    public void selectDropdownOption(String option) {
        initialiseDropdown();
        dropdown.selectByVisibleText(option);
    }

    public String getDropdownSelectedOption() {
        initialiseDropdown();
        return dropdown.getFirstSelectedOption().getText();
    }

}
