package pl.lipinski.springdataexample.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.lipinski.springdataexample.dao.dto.UserDetailsDto;
import pl.lipinski.springdataexample.dao.dto.UserRegistrationDto;
import pl.lipinski.springdataexample.dao.entity.User;
import pl.lipinski.springdataexample.manager.UserManager;
import pl.lipinski.springdataexample.util.errors.ControllerError;
import pl.lipinski.springdataexample.util.validators.UserRegistrationValidator;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRegistrationValidator userRegistrationValidator;
    protected final ModelMapper modelMapper;

    UserManager userManager;

    @Autowired
    public UserController(UserManager userManager, UserRegistrationValidator userRegistrationValidator) {
        this.userManager = userManager;
        this.userRegistrationValidator = userRegistrationValidator;
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
    public ResponseEntity saveDto(@ModelAttribute("userform") UserRegistrationDto userRegistrationDto,
                                                       BindingResult bindingResult){
        userRegistrationValidator.validate(userRegistrationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            ControllerError apiError = new ControllerError(HttpStatus.BAD_REQUEST,
                    userRegistrationValidator.getErrorCode(),
                    userRegistrationValidator.getErrorMessages(bindingResult));
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }
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
