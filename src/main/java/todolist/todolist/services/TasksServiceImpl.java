package todolist.todolist.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import todolist.todolist.entitites.Task;
import todolist.todolist.entitites.User;
import todolist.todolist.repositories.TasksRepository;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;

    public TasksServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Iterable<Task> findAll(User user) {
        return tasksRepository.findAllByUser(user) ;
    }

    @Override
    public void newTask(String name, String description, User user) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setUser(user);
        task.setCreatedDate(LocalDateTime.now());
        tasksRepository.save(task);
    }

    @Override
    public void toogleCompleteTask(String id, User user) {
        Task task = tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (Objects.equals(user.getUsername(), task.getUser().getUsername())) {
            task.setCompleted(!task.isCompleted());
            tasksRepository.save(task);
        }
    }

    @Override
    public void deleteTask(String id , User user) {
        Task task = tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (Objects.equals(user.getUsername(), task.getUser().getUsername())) {tasksRepository.delete(task);}

    }
}
