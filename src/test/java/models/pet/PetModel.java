package models.pet;

import java.util.ArrayList;
import lombok.Data;
import utils.ToJson;

@Data
public class PetModel implements ToJson {
    private long id;
    private Category category;
    private String name;
    private ArrayList<String> photoUrls;
    private ArrayList<Tag> tags;
    private String status;
}
