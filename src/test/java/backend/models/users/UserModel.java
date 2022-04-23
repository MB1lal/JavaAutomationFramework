package backend.models.users;

import lombok.Data;
import utils.ToJson;

@Data
public class UserModel implements ToJson {

    Integer id;
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    Integer userStatus;
}
