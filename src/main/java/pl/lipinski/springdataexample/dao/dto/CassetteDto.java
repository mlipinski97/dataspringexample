package pl.lipinski.springdataexample.dao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CassetteDto {
    private Long id;

    private String name;

    private LocalDate productionYear;

    private Boolean isRented;
}
