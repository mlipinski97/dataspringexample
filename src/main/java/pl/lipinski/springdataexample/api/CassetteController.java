package pl.lipinski.springdataexample.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lipinski.springdataexample.dao.dto.RentBasketDto;
import pl.lipinski.springdataexample.dao.entity.Cassette;
import pl.lipinski.springdataexample.dao.entity.RentBasket;
import pl.lipinski.springdataexample.util.exceptions.WrongCassetteStatusException;
import pl.lipinski.springdataexample.manager.CassetteManager;
import pl.lipinski.springdataexample.manager.RentBasketManager;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/cassettes")
public class CassetteController {

    private CassetteManager cassetteManager;
    private RentBasketManager rentBasketManager;
    protected final ModelMapper modelMapper;

    @Autowired
    public CassetteController(CassetteManager cassetteManager, RentBasketManager rentBasketManager) {
        this.rentBasketManager = rentBasketManager;
        this.cassetteManager = cassetteManager;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/getall/cassette")
    public Iterable<Cassette> getAll(){
        return cassetteManager.findAll();
    }

    @GetMapping("/getbyid/cassette")
    public Optional<Cassette> getById(@RequestParam Long id){
        return cassetteManager.findById(id);
    }

    @GetMapping("/getbyname/cassette")
    public Optional<Cassette> getById(@RequestParam String name){
        return cassetteManager.findByName(name);
    }

    @GetMapping("/getbyproductionyear/cassette")
    public Optional<Cassette> getById(@RequestParam LocalDate producetionDate){
        return cassetteManager.findByProductionYear(producetionDate);
    }

    @PostMapping("/add/cassette")
    public Cassette save(@RequestBody Cassette cassette){
        return cassetteManager.save(cassette);
    }

    @PutMapping("/put/cassette")
    public Cassette update(@RequestBody Cassette cassette){
        return cassetteManager.save(cassette);
    }

    @PatchMapping("/rentbyid")
    public ResponseEntity<Cassette> rentCassetteById(@RequestParam Long id, @RequestParam Long basketid){
        Optional<Cassette> cassette = cassetteManager.findById(id);
        cassette.orElseThrow(EntityNotFoundException::new);
        Optional<RentBasket> rentBasket = rentBasketManager.findById(basketid);
        rentBasket.orElseThrow(EntityNotFoundException::new);
        if(cassette.get().getRented()){
            throw new WrongCassetteStatusException("Cassette with id: " + id + " already rented!");
        } else{
            cassette.get().setRented(true);
            cassette.get().setCurrentBasket(rentBasket.get());
        }
        final Cassette updatedCassette = cassetteManager.save(cassette.get());
        return ResponseEntity.ok(updatedCassette);
    }

    @PatchMapping("/returnbyid")
    public ResponseEntity<Cassette> returnCassetteById(@RequestParam Long id){
        Optional<Cassette> cassette = cassetteManager.findById(id);
        cassette.orElseThrow(EntityNotFoundException::new);
        if(!cassette.get().getRented()){
            throw new WrongCassetteStatusException("Cassette with id: " + id + " is not rented!");
        } else{
            cassette.get().setRented(false);
            cassette.get().setCurrentBasket(null);
        }
        final Cassette updatedCassette = cassetteManager.save(cassette.get());
        return ResponseEntity.ok(updatedCassette);
    }

    @DeleteMapping("/delete/cassette")
    public void deletecassetteById(@RequestParam Long id){
        cassetteManager.deleteById(id);
    }

    @PostMapping("/add/basket")
    public ResponseEntity<RentBasketDto> saveBasket(@ModelAttribute("newbasketform") RentBasketDto rentBasketDto){
        rentBasketManager.save(modelMapper.map(rentBasketDto, RentBasket.class));
        rentBasketDto.setRentDate(LocalDate.now());
        return ResponseEntity.ok(rentBasketDto);
    }

    @DeleteMapping("/delete/basket")
    public void deleteBasketById(@RequestParam Long id){
        rentBasketManager.deleteById(id);
    }

    @GetMapping("/getbyid/basket")
    public RentBasketDto getBasketById(@RequestParam Long id){
        Optional<RentBasket> rentBasket = rentBasketManager.findById(id);
        rentBasket.orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(rentBasket.get(), RentBasketDto.class);
    }

}
