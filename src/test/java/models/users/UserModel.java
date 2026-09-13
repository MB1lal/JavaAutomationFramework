package models.users;

import lombok.Data;
import utils.ToJson;

@Data
public class UserModel implements ToJson {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;
}
