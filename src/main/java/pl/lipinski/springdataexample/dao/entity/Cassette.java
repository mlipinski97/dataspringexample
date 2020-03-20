package pl.lipinski.springdataexample.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Cassette {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private LocalDate productionYear;

    private Boolean isRented;


    @ManyToOne
    @JoinColumn(name = "current_basket_id")
    private RentBasket currentBasket;

    public RentBasket getCurrentBasket() {
        return currentBasket;
    }

    public void setCurrentBasket(RentBasket currentBasket) {
        this.currentBasket = currentBasket;
    }

    public Boolean getRented() {
        return isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(LocalDate productionYear) {
        this.productionYear = productionYear;
    }

    public Cassette() {
    }

    public Cassette(Long id, String name, LocalDate productionYear, Boolean isRented) {
        this.id = id;
        this.name = name;
        this.productionYear = productionYear;
        this.isRented = isRented;
    }

    public Cassette(Long id, String name, LocalDate productionYear, Boolean isRented, RentBasket rentBasket) {
        this.id = id;
        this.name = name;
        this.productionYear = productionYear;
        this.isRented = isRented;
        this.currentBasket = rentBasket;
    }
}
