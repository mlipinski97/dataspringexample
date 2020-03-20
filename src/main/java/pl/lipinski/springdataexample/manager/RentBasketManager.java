package pl.lipinski.springdataexample.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lipinski.springdataexample.dao.RentBasketRepo;
import pl.lipinski.springdataexample.dao.entity.Cassette;
import pl.lipinski.springdataexample.dao.entity.RentBasket;

import java.util.Optional;

@Service
public class RentBasketManager {

    RentBasketRepo rentBasketRepo;

    @Autowired
    public RentBasketManager(RentBasketRepo rentBasketRepo){
        this.rentBasketRepo = rentBasketRepo;
    }

    public Optional<RentBasket> findById(Long id){
        return rentBasketRepo.findById(id);
    }

    public Iterable<RentBasket> findAll(){
        return rentBasketRepo.findAll();
    }

    public RentBasket save(RentBasket RentBasket){
        return rentBasketRepo.save(RentBasket);
    }

    public void deleteById(Long id){
        rentBasketRepo.deleteById(id);
    }

}
