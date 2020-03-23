package pl.lipinski.springdataexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lipinski.springdataexample.dao.entity.Cassette;

@Repository
public interface CassetteRepo extends JpaRepository<Cassette, Long> {

}
