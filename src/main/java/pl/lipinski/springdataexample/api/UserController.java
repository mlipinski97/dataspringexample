package pl.lipinski.springdataexample.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.lipinski.springdataexample.dao.dto.UserDetailsDto;
import pl.lipinski.springdataexample.dao.dto.UserRegistrationDto;
import pl.lipinski.springdataexample.dao.entity.User;
import pl.lipinski.springdataexample.manager.UserManager;


import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    protected final ModelMapper modelMapper;

    UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
        this.modelMapper = new ModelMapper();
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
        return userManager.save(user);
    }

    @PostMapping("/adddto")
    public ResponseEntity<UserRegistrationDto> saveDto(@ModelAttribute("userform") UserRegistrationDto userRegistrationDto){
        userManager.save(modelMapper.map(userRegistrationDto, User.class));
        return ResponseEntity.ok(userRegistrationDto);
    }

    @GetMapping("/account")
    public UserDetailsDto account() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userManager.findByUsername(name);
        user.orElseThrow(NoSuchElementException::new);
        return modelMapper.map(user.get(), UserDetailsDto.class);
    }



}
