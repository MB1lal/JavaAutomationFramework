package models.store;

import lombok.Data;
import org.jeasy.random.annotation.Randomizer;
import utils.TimestampGenerator;
import utils.ToJson;

@Data
public class PetStoreModel implements ToJson {
    private int id;
    private int petId;
    private int quantity;

    @Randomizer(TimestampGenerator.class)
    private String shipDate;

    private String status = "placed";
    private boolean complete = true;
}
