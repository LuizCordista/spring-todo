package todolist.todolist.controller;

import org.springframework.web.bind.annotation.*;
import todolist.todolist.entitites.Task;
import todolist.todolist.entitites.User;
import todolist.todolist.repositories.UsersRepository;
import todolist.todolist.services.TasksService;

import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TasksController {

    private final TasksService tasksService;
    private final UsersRepository usersRepository;

    public TasksController(TasksService tasksService, UsersRepository usersRepository) {
        this.tasksService = tasksService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/tarefas")
    public Iterable<Task> getAll(Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        return tasksService.findAll(user);
    }

    @PostMapping("/tarefas")
    public void newTask(@RequestBody Task task, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        tasksService.newTask(task.getName(), task.getDescription(), user);
    }

    @PutMapping("/tarefas/{id}/concluir")
    public void toogleCompleteTask(@PathVariable(value = "id") String id, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        tasksService.toogleCompleteTask(id, user);
    }

    @DeleteMapping("/tarefas/{id}")
    public void deleteTask(@PathVariable(value = "id") String id, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        tasksService.deleteTask(id, user);
    }
}
