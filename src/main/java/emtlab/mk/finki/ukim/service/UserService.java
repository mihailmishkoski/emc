package emtlab.mk.finki.ukim.service;

public interface UserService {
    boolean LogIn(String username, String password);

    void Register(String name, String surname, String username, String password);

    boolean checkPasswordStrength(String password);

    boolean checkPasswordAndRepeatedPassword(String password, String repeatedPassword);

}
