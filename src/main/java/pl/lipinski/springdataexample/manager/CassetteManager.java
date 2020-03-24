package pl.lipinski.springdataexample.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.lipinski.springdataexample.dao.CassetteRepo;
import pl.lipinski.springdataexample.dao.entity.Cassette;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CassetteManager {

    private CassetteRepo cassetteRepo;

    @Autowired
    public CassetteManager(CassetteRepo cassetteRepo) {
        this.cassetteRepo = cassetteRepo;
    }

    public Optional<Cassette> findById(Long id){
        return cassetteRepo.findById(id);
    }

    public Optional<Cassette> findByName(String name){
        return cassetteRepo.findByName(name);
    }

    public Optional<Cassette> findByProductionYear(LocalDate productionYear){
        return cassetteRepo.findByProductionYear(productionYear);
    }


    public Iterable<Cassette> findAll(){
       return cassetteRepo.findAll();
    }

    public Cassette save(Cassette cassette){
        cassette.setCurrentBasket(null);
        return cassetteRepo.save(cassette);
    }

    public void deleteById(Long id){
        cassetteRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void dbFiller(){
        save(new Cassette(1L, "Titanic", LocalDate.of(1999, 9, 20), false));
        save(new Cassette(2L, "Titanic 2: Eletric Boogaloo", LocalDate.of(2001, 1, 21), false));
        save(new Cassette(3L, "Titanic 3: Eletric Boogaloo - return of la chupakabra",
                LocalDate.of(2003, 7, 4), false));
    }
}
