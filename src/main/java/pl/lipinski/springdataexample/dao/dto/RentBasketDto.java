package pl.lipinski.springdataexample.dao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lipinski.springdataexample.dao.entity.Cassette;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class RentBasketDto {
    private LocalDate rentDate;
    private Set<Cassette> cassettes;

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }
}
