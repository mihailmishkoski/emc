package emtlab.mk.finki.ukim.service.Impl;

import emtlab.mk.finki.ukim.exceptions.MyCustomException;
import emtlab.mk.finki.ukim.model.Author;
import emtlab.mk.finki.ukim.model.Book;
import emtlab.mk.finki.ukim.model.Country;
import emtlab.mk.finki.ukim.repository.CountryRepository;
import emtlab.mk.finki.ukim.service.CountryService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImple implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImple(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public void addCountry(String name, String countryContinent) {
        try{
            countryRepository.save(new Country(name,countryContinent));
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot add a new country", e);
        }
    }

    @Override
    public void removeCountry(Long countryId) {
        try{
            Country country = countryRepository.findById(countryId).orElse(null);
            if(country!=null)
                countryRepository.delete(country);
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot remove country", e);
        }
    }
}
