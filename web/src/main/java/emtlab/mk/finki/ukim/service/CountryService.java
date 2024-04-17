package emtlab.mk.finki.ukim.service;

import emtlab.mk.finki.ukim.model.Book;
import emtlab.mk.finki.ukim.model.Country;
import emtlab.mk.finki.ukim.model.dto.BookDto;
import emtlab.mk.finki.ukim.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> listAll();

    Optional<Country> findById(Long id);

    Optional<Country> addBook(CountryDto countryDto);

    Optional<Country> edit(Long id, CountryDto countryDto);

    void removeCountry(Long countryId);
}
