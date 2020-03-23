package pl.lipinski.springdataexample.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.lipinski.springdataexample.dao.UserRepo;
import pl.lipinski.springdataexample.dao.entity.Cassette;
import pl.lipinski.springdataexample.dao.entity.User;
import pl.lipinski.springdataexample.manager.UserManager;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserManager userManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserManager userManager, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userManager = userManager;
    }

    @GetMapping("/getall")
    public Iterable<User> getAll(){
        return userManager.findAll();
    }

    @GetMapping("/getbyid")
    public Optional<User> getById(@RequestParam Long id){
        return userManager.findById(id);
    }

    @GetMapping("/getbyusername")
    public Optional<User> findByUsername(@RequestParam String username){
        return userManager.findByUsername(username);
    }

    @PostMapping("/add")
    public User save(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userManager.save(user);
    }

}
