package todolist.todolist.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import todolist.todolist.dtos.LoginResponseDTO;
import todolist.todolist.dtos.ResponseMessageDTO;
import todolist.todolist.dtos.UserDTO;
import todolist.todolist.entitites.User;
import todolist.todolist.infra.TokenService;
import todolist.todolist.repositories.UsersRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    private final UsersRepository usersRepository;
    @Autowired
    private HttpServletResponse httpServletResponse;

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
    public ResponseEntity<ResponseMessageDTO> register(@RequestBody UserDTO data) {
        if(this.usersRepository.findByUsername(data.getUsername()) != null)  {
            return ResponseEntity.badRequest().body(new ResponseMessageDTO("Usuario já existe"));
        }
        if(this.usersRepository.findByEmail(data.getEmail()) != null) {
            return ResponseEntity.badRequest().body(new ResponseMessageDTO("Email já existe"));
        }

        String encryptedPassword= new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getUsername(), data.getEmail(), encryptedPassword);

        this.usersRepository.save(newUser);
        return ResponseEntity.ok().body(new ResponseMessageDTO("Usuario registrado com sucesso"));
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String token) {
        if (token != null) token = token.replace("Bearer ", "");
        String user = tokenService.validateToken(token);
        if (user.isEmpty()) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
        return ResponseEntity.ok(user);
    }
}
