package backend.models.store;

import lombok.Data;
import org.jeasy.random.annotation.Randomizer;
import utils.TimestampGenerator;
import utils.ToJson;

@Data
public class PetStoreModel implements ToJson {
    public int id = 10;
    public int petId = 2008;
    public int quantity = 4;
    @Randomizer(TimestampGenerator.class)
    public String shipDate;
    public String status = "placed";
    public boolean complete = true;
}
