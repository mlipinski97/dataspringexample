package pl.lipinski.springdataexample.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lipinski.springdataexample.dao.entity.RentBasket;

@Repository
public interface RentBasketRepo extends CrudRepository<RentBasket, Long> {
}
