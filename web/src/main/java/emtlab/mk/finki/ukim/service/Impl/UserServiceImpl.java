package emtlab.mk.finki.ukim.service.Impl;

import emtlab.mk.finki.ukim.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import emtlab.mk.finki.ukim.config.PasswordEncryption;
import emtlab.mk.finki.ukim.exceptions.MyCustomException;
import emtlab.mk.finki.ukim.model.AppUser;

import emtlab.mk.finki.ukim.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncryption passwordEncryption;

    public UserServiceImpl(AppUserRepository appUserRepository, PasswordEncryption passwordEncryption) {
        this.appUserRepository = appUserRepository;
        this.passwordEncryption = passwordEncryption;
    }

    @Override
    public boolean LogIn(String username, String password) {
        AppUser user = appUserRepository.findAppUserByUsername(username);
        return user != null && passwordEncryption.passwordEncoder().matches(password, user.getPassword());
    }
    @Transactional
    @Override
    public void Register(String name, String surname, String username, String password) {
        try{
            appUserRepository.save(new AppUser(name,surname,username,passwordEncryption.passwordEncoder().encode(password)));
        }catch (DataAccessException e){
            throw new MyCustomException("Cannot create user account", e);
        }
    }

    @Override
    public boolean checkPasswordStrength(String password) {
        boolean hasMinimumLength = password.length() >= 8;
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasSpecialCharacter = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        return hasMinimumLength && hasUppercase && hasSpecialCharacter;
    }

    @Override
    public boolean checkPasswordAndRepeatedPassword(String password, String repeatedPassword) {
        return password.matches(repeatedPassword);
    }

    @Override
    public User loadUserByUsername(String username) {
        return (User) appUserRepository.findAppUserByUsername(username);
    }
}
