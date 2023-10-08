package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.Objects;

public class NestedFramesPage extends PageObject {
    @FindBy(name = "frame-top")
    private WebElementFacade frameTop;

    @FindBy(name = "frame-left")
    private WebElementFacade topLeftFrame;

    @FindBy(name = "frame-right")
    private WebElementFacade topRightFrame;

    @FindBy(name = "frame-middle")
    private WebElementFacade topMiddleFrame;

    @FindBy(name = "frame-bottom")
    private WebElementFacade childBottomFrame;

    @FindBy(css = "body")
    private WebElementFacade frameText;

    private void switchToFrame(String frameId) {
        switch (frameId) {
            case "Top Left":
                this.getDriver().switchTo().frame(topLeftFrame);
                break;
            case "Top Middle":
                this.getDriver().switchTo().frame(topMiddleFrame);
                break;
            case "Top Right":
                this.getDriver().switchTo().frame(topRightFrame);
                break;
            case "Bottom":
                this.getDriver().switchTo().frame(childBottomFrame);
                break;
        }
    }

    private void navigateToExpectedFrameLayer(String frameId) {
        if(!Objects.equals(frameId, "Bottom")) {
            this.getDriver().switchTo().frame(frameTop);
        }
        switchToFrame(frameId);
    }

    public String getFrameText(String frameId) {
        navigateToExpectedFrameLayer(frameId);
        String frameText = this.frameText.getText();
        if(!Objects.equals(frameId, "Bottom")){
            this.getDriver().switchTo().parentFrame().switchTo().parentFrame();
        } else {
            this.getDriver().switchTo().parentFrame();
        }
        return frameText;
    }
}
