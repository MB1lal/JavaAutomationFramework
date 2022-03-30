package backend.models.pet;

import lombok.Data;
import utils.ToJson;

import java.util.ArrayList;

@Data
public class PetModel implements ToJson {
    public long id;
    public Category category;
    public String name;
    public ArrayList<String> photoUrls;
    public ArrayList<Tag> tags;
    public String status;
}
