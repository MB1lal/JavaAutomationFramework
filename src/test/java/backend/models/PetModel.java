package backend.models;

import lombok.Data;
import utils.ToJson;

@Data
public class PetModel implements ToJson {

    private Category category;
    private Tag tag;
    private Root root;
}
