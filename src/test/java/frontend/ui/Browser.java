package frontend.ui;

import frontend.ui.models.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {


    private WebDriver buildDriver(String... switches) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }


    public WebDriver getChromeDriver(BrowserType type) throws Exception {
        return switch (type) {
            case CHROME -> buildDriver("--no-sandbox");
            case CHROME_HEADLESS -> buildDriver("--no-sandbox", "--headless", "window-size=1280,780");
            default -> throw new Exception("Browser type is not supported " + type);
        };
    }
}
