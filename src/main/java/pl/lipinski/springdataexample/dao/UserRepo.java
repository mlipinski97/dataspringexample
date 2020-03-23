package pl.lipinski.springdataexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lipinski.springdataexample.dao.entity.User;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
