package emtlab.mk.finki.ukim.service;

import emtlab.mk.finki.ukim.model.Book;
import emtlab.mk.finki.ukim.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> listAll();
    void addCountry(String name, String countryContinent);
    void removeCountry(Long countryId);

}
