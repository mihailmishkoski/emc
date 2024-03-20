package emtlab.mk.finki.ukim.web.controller.rest;


import emtlab.mk.finki.ukim.model.Country;

import emtlab.mk.finki.ukim.model.dto.CountryDto;
import emtlab.mk.finki.ukim.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/countries")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping
    public List<Country> findAll(){
        return countryService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        countryService.removeCountry(id);
    }
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto countryDto) {
        return this.countryService.addBook(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> edit(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        return this.countryService.edit(id,countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
