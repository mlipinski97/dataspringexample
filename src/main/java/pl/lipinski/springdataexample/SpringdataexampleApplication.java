package pl.lipinski.springdataexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.lipinski.springdataexample.dao.UserRepo;

@SpringBootApplication
@EnableJpaRepositories
public class SpringdataexampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataexampleApplication.class, args);
    }

}
