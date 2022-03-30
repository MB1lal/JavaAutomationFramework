package backend.models.store;

import lombok.Data;
import org.jeasy.random.annotation.Randomizer;
import utils.TimestampGenerator;
import utils.ToJson;

@Data
public class PetStoreModel implements ToJson {
    public int id;
    public int petId;
    public int quantity;
    @Randomizer(TimestampGenerator.class)
    public String shipDate;
    public String status = "placed";
    public boolean complete = true;
}
