package todolist.todolist.services;

import todolist.todolist.entitites.Task;
import todolist.todolist.entitites.User;

public interface TasksService {

    Iterable<Task> findAll(User user);
    void newTask(String name, String description, User user);
    void toogleCompleteTask(String id, User user);
    void deleteTask(String id, User user);
}
