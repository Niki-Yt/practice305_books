package store.book.thymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import store.book.auth.dtos.RegistrationUserDto;
import store.book.auth.exceptions.PasswordsNotMatchingException;
import store.book.auth.exceptions.UserAlreadyExistsException;
import store.book.auth.services.AuthService;

@Controller
@RequestMapping("/registration")
public class UserRegistration {
    @Autowired
    AuthService userService;

    @ModelAttribute("user")
    public RegistrationUserDto registrationUserDto() {
        return new RegistrationUserDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") RegistrationUserDto registrationDto, Model model) {
        try {
            userService.createNewUser(registrationDto);
            return "redirect:/registration?success";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", "User with this username or email already exists.");
            return "registration";
        } catch (PasswordsNotMatchingException e) {
            model.addAttribute("passwordMismatch", "Passwords do not match.");
            return "registration";
        }
    }
}