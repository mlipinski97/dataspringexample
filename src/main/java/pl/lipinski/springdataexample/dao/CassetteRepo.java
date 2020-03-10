package pl.lipinski.springdataexample.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lipinski.springdataexample.dao.entity.Cassette;

@Repository
public interface CassetteRepo extends CrudRepository<Cassette, Long> {

}
