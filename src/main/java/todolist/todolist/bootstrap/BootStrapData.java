package todolist.todolist.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import todolist.todolist.entitites.Task;
import todolist.todolist.entitites.User;
import todolist.todolist.repositories.TasksRepository;
import todolist.todolist.repositories.UsersRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    public BootStrapData(TasksRepository tasksRepository, UsersRepository usersRepository) {
        this.tasksRepository = tasksRepository;
        this.usersRepository = usersRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        User testUser = new User();
        testUser.setEmail("EmailTeste");
        testUser.setPassword("PasswordTeste");
        testUser.setUsername("TesteUser");
        User userSaved = usersRepository.save(testUser);

        Task test = new Task();
        test.setName("testeTask");
        test.setDescription("Teste Denovo");
        test.setUser(userSaved);

        Task testSaved = tasksRepository.save(test);


    }
}
