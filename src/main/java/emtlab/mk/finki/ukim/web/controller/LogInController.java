package emtlab.mk.finki.ukim.web.controller;

import emtlab.mk.finki.ukim.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import emtlab.mk.finki.ukim.service.UserService;

@Controller
@SessionAttributes("currentUserId")
public class LogInController {

    private final UserService userService;

    private final BookService bookService;

    public LogInController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping({"/","login"})
    public String GetLogInPage()
    {
        return "logInView";
    }

    @GetMapping("/register")
    public String GetRegisterPage()
    {
        return "registerView";
    }

    @PostMapping("/register")
    public String RegisterUser(@RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String repeatedPassword,
                               Model model)
    {
        boolean valid = true;
        if(!userService.checkPasswordStrength(password)){
            model.addAttribute("passwordStrengthError", "Password is too week");
            valid = false;
        }
        if(!userService.checkPasswordAndRepeatedPassword(password, repeatedPassword))
        {
            model.addAttribute("passwordsNotSameError", "Passwords do not match");
            valid = false;
        }
        if(valid)
        {
            userService.Register(name,surname,username,password);
            return "logInView";
        }
        else {
            return "registerView";
        }

    }

    @PostMapping("/login")
    public String LogIn(@RequestParam String username, @RequestParam String password, Model model)
    {
        if(userService.LogIn(username,password))
        {
            model.addAttribute("books", bookService.listAll());
            return "redirect:/books";
        }
        else {
            model.addAttribute("logInError", "No user with those credentials");
            return "logInView";
        }
    }

}
