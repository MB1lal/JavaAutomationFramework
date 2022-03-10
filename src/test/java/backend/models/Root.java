package backend.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Root {
    public int id;
    public Category category;
    public String name;
    public ArrayList<String> photoUrls;
    public ArrayList<Tag> tags;
    public String status;
}
