package core;

import frontend.ui.models.BrowserType;
import lombok.Data;

@Data
public class Env {
//    private String driverPath;
    private BrowserType driver;
//    private String driverLocation;


    public String browserStackUsername;
    public String browserStackKey;
    public String browserStackBrowserApi;
}
