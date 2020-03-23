package pl.lipinski.springdataexample.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lipinski.springdataexample.dao.UserRepo;
import pl.lipinski.springdataexample.dao.entity.User;

import java.util.Optional;

@Service
public class ApplicationUserService implements UserDetailsService {

    UserRepo userRepo;

    @Autowired
    public ApplicationUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
        return user.map(ApplicationUser::new).get();
    }
}
