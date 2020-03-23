package pl.lipinski.springdataexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lipinski.springdataexample.dao.entity.RentBasket;

@Repository
public interface RentBasketRepo extends JpaRepository<RentBasket, Long> {
}
