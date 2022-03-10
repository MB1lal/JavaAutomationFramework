package backend.steps;

import backend.models.PetModel;
import core.Env;
import frontend.ui.Browser;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import utils.SharedState;

import java.util.Random;

public class BaseSteps {

    public SharedState sharedState = new SharedState();
    public Env env = new Env();
    public Browser browser = new Browser();

    public static EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(new Random().nextLong())
            //sensible string length
                    .stringLengthRange(5,50)
    );

    public PetModel createNewPetPayload() {
        return random.nextObject(PetModel.class);
    }



}
