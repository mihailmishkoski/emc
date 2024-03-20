package emtlab.mk.finki.ukim.model.dto;

import lombok.Data;

@Data
public class CountryDto {

    private String name;
    private String continentName;

    public CountryDto(String name, String continentName) {
        this.name = name;
        this.continentName = continentName;
    }
}
