package pl.lipinski.springdataexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lipinski.springdataexample.dao.entity.Cassette;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CassetteRepo extends JpaRepository<Cassette, Long> {
    Optional<Cassette> findByName(String cassetteName);
    Optional<Cassette> findByProductionYear(LocalDate productionYear);
}
