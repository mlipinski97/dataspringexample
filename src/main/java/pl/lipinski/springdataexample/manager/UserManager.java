package pl.lipinski.springdataexample.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lipinski.springdataexample.dao.UserRepo;
import pl.lipinski.springdataexample.dao.entity.Cassette;
import pl.lipinski.springdataexample.dao.entity.RentBasket;
import pl.lipinski.springdataexample.dao.entity.User;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserManager {

    UserRepo userRepo;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(UserRepo userRepo,  PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    public Optional<User> findById(Long id){
        return userRepo.findById(id);
    }

    public Iterable<User> findAll(){
        return userRepo.findAll();
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setEnabled(true);
        return userRepo.save(user);
    }

    public void deleteById(Long id){
        userRepo.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void dbFiller(){
        userRepo.save(new User("admin", passwordEncoder.encode("admin"), true, "ROLE_ADMIN"));
        userRepo.save(new User("user", passwordEncoder.encode("user"), true, "ROLE_USER"));
    }
}
