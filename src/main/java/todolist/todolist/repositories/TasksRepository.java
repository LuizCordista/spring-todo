package todolist.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todolist.todolist.entitites.Task;
import todolist.todolist.entitites.User;

@Repository
public interface TasksRepository extends CrudRepository<Task, String> {
    Iterable<Task> findAllByUser(User user);
}
