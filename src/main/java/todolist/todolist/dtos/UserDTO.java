package todolist.todolist.dtos;

import lombok.Getter;
import todolist.todolist.entitites.User;

@Getter
public class UserDTO {

    private String username;
    private String email;
    private String password;

    public User createUser() {
        return new User(username, email, password);
    }
}
