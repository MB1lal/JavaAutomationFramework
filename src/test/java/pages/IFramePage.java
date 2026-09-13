package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IFramePage extends PageObject {
    @FindBy(id = "mce_0_ifr")
    private WebElementFacade iFrameWithText;

    @FindBy(id = "tinymce")
    private WebElementFacade txtContent;


    public void switchToIFrame() {
       this.getDriver().switchTo().frame(iFrameWithText);
    }

    public void enterTextIntoContent(String inputText) {
        // TinyMCE swallows raw Selenium keystrokes in headless runs (clear()
        // is illegal on contenteditable and clicks get intercepted), so write
        // through the editor's own API. activeEditor exists before
        // initialisation finishes and anything set too early gets wiped, so
        // wait for initialised, then verify the write.
        this.getDriver().switchTo().defaultContent();
        JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
        WebDriverWait wait = new WebDriverWait(this.getDriver(), Duration.ofSeconds(15));
        wait.until(d -> Boolean.TRUE.equals(js.executeScript(
                "return typeof tinymce !== 'undefined' && !!tinymce.activeEditor"
                        + " && tinymce.activeEditor.initialized;")));
        js.executeScript("tinymce.activeEditor.setContent(arguments[0]);", inputText);
        wait.until(d -> String.valueOf(
                js.executeScript("return tinymce.activeEditor.getContent();")).contains(inputText));
    }

    public String getIFrameText() {
        this.getDriver().switchTo().defaultContent();
        this.getDriver().switchTo().frame(iFrameWithText);
        try {
            return txtContent.getText().trim();
        } finally {
            this.getDriver().switchTo().defaultContent();
        }
    }
}
