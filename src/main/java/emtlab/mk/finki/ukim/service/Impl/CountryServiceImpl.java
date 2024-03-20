package emtlab.mk.finki.ukim.service.Impl;

import emtlab.mk.finki.ukim.exceptions.MyCustomException;
import emtlab.mk.finki.ukim.model.Author;
import emtlab.mk.finki.ukim.model.Book;
import emtlab.mk.finki.ukim.model.BookCategory;
import emtlab.mk.finki.ukim.model.Country;
import emtlab.mk.finki.ukim.model.dto.CountryDto;
import emtlab.mk.finki.ukim.repository.CountryRepository;
import emtlab.mk.finki.ukim.service.CountryService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> addBook(CountryDto countryDto) {
        try{
            String countryName = countryDto.getName();
            String continent = countryDto.getContinentName();
            Country country = new Country(countryName,continent);
            countryRepository.save(country);
            return Optional.of(country);
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot add a new country", e);
        }

    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        try{
            Country country = countryRepository.findById(id).orElse(null);
            if(country!=null)
            {
                country.setCountryName(countryDto.getName());
                country.setContinent(countryDto.getContinentName());
                countryRepository.save(country);
                return Optional.of(country);
            }
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot add a country", e);
        }
        return Optional.empty();
    }

    @Override
    public void removeCountry(Long countryId) {
        countryRepository.delete(countryRepository.findById(countryId).orElseThrow());
    }


}
