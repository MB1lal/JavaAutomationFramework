package backend.steps;


import io.cucumber.java.Before;
import net.serenitybdd.cucumber.suiteslicing.SerenityTags;


public class Hooks extends BaseSteps {

    @Before
    public void before() {
        SerenityTags.create().tagScenarioWithBatchingInfo();
    }
}
