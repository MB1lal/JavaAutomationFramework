package frontend.steps;

import core.Env;
import frontend.ui.Browser;
import utils.SharedState;

public class BaseSteps {

    public SharedState sharedState = new SharedState();
    public Env env = new Env();
    public Browser browser = new Browser();

}
