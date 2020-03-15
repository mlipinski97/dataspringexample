package pl.lipinski.springdataexample.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RentBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
