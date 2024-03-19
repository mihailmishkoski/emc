package emtlab.mk.finki.ukim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emtlab.mk.finki.ukim.model.Country;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
