package todolist.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import todolist.todolist.entitites.User;

public interface UsersRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
    User findByEmail (String email);
}
