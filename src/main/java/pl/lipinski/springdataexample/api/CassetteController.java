package pl.lipinski.springdataexample.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lipinski.springdataexample.dao.entity.Cassette;
import pl.lipinski.springdataexample.dao.entity.RentBasket;
import pl.lipinski.springdataexample.manager.CassetteManager;
import pl.lipinski.springdataexample.manager.RentBasketManager;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/cassettes")
public class CassetteControler {

    private CassetteManager cassetteManager;
    private RentBasketManager rentBasketManager;

    @Autowired
    public CassetteControler(CassetteManager cassetteManager, RentBasketManager rentBasketManager) {
        this.rentBasketManager = rentBasketManager;
        this.cassetteManager = cassetteManager;
    }

    @GetMapping("/getall/cassette")
    public Iterable<Cassette> getAll(){
        return cassetteManager.findAll();
    }

    @GetMapping("/getbyid/cassette")
    public Optional<Cassette> getById(@RequestParam Long id){
        return cassetteManager.findById(id);
    }

    @PostMapping("/add/cassette")
    public Cassette save(@RequestBody Cassette cassette){
        return cassetteManager.save(cassette);
    }

    @PutMapping("/put/cassette")
    public Cassette update(@RequestBody Cassette cassette){
        return cassetteManager.save(cassette);
    }

    //TODO split update rent status to "rent" and "return"
    @PatchMapping("/update/cassette")
    public ResponseEntity<Cassette> updateById(@RequestParam Long id, @RequestParam Long basketId){
        Cassette cassette = cassetteManager.findById(id).get();
        RentBasket rentBasket = rentBasketManager.findById(basketId).get();
        if(cassette.getRented()){
            cassette.setRented(false);
            cassette.setCurrentBasket(null);
        } else{
            cassette.setCurrentBasket(rentBasket);
            cassette.setRented(true);
        }
        final Cassette updatedCassette = cassetteManager.save(cassette);
        return ResponseEntity.ok(updatedCassette);
    }

    @DeleteMapping("/delete/cassette")
    public void deletecassetteById(@RequestParam Long id){
        cassetteManager.deleteById(id);
    }

    @PostMapping("/add/basket")
    public RentBasket saveBasket(@RequestBody RentBasket rentBasket){
        return rentBasketManager.save(rentBasket);
    }

    @DeleteMapping("/delete/basket")
    public void deleteBasketById(@RequestParam Long id){
        rentBasketManager.deleteById(id);
    }

}
