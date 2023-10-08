package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindBy;

public class DropdownPage extends PageObject {

    @FindBy(id = "dropdown")
    private WebElement dropdownElement;

    private final Select dropdown;

    public DropdownPage() {
        dropdown = new Select(dropdownElement);
    }

    public void selectDropdownOption(String option) {
        dropdown.selectByVisibleText(option);
    }

    public String getDropdownSelectedOption() { return dropdown.getFirstSelectedOption().toString(); }

}
