package todolist.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todolist.todolist.dtos.LoginResponseDTO;
import todolist.todolist.dtos.UserDTO;
import todolist.todolist.entitites.User;
import todolist.todolist.infra.TokenService;
import todolist.todolist.repositories.UsersRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    private final UsersRepository usersRepository;

    public AuthenticationController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());


        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDTO data) {
        if(this.usersRepository.findByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();
        if(this.usersRepository.findByEmail(data.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword= new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getUsername(), data.getEmail(), encryptedPassword);

        this.usersRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
