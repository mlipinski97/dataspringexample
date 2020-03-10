package pl.lipinski.springdataexample.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lipinski.springdataexample.dao.entity.Cassette;
import pl.lipinski.springdataexample.manager.CassetteManager;

import java.util.Optional;

@RestController
@RequestMapping("/api/cassettes")
public class CassetteControler {

    private CassetteManager cassetteManager;

    @Autowired
    public CassetteControler(CassetteManager cassetteManager) {
        this.cassetteManager = cassetteManager;
    }

    @GetMapping("/getall")
    public Iterable<Cassette> getAll(){
        return cassetteManager.findAll();
    }

    @GetMapping("/getbyid")
    public Optional<Cassette> getById(@RequestParam Long id){
        return cassetteManager.findById(id);
    }

    @PostMapping
    public Cassette save(@RequestBody Cassette cassette){
        return cassetteManager.save(cassette);
    }

    @PutMapping
    public Cassette update(@RequestBody Cassette cassette){
        return cassetteManager.save(cassette);
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long id){
        cassetteManager.deleteById(id);
    }
}
