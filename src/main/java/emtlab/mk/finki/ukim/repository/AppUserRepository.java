package emtlab.mk.finki.ukim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emtlab.mk.finki.ukim.model.AppUser;


public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findAppUserByUsername(String username);
}
