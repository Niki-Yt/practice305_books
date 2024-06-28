package store.book.thymeleaf.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.book.auth.dtos.JwtRequest;
import store.book.auth.dtos.JwtResponse;
import store.book.auth.entities.UserEntity;
import store.book.auth.repositories.UserRepository;
import store.book.auth.services.AuthService;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository; // Inject UserRepository to fetch user roles

    @ModelAttribute("user")
    public JwtRequest authRequest() {
        return new JwtRequest();
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String createAuthToken(@ModelAttribute("user") JwtRequest authRequest, Model model, HttpSession session) {
        try {
            ResponseEntity<?> response = authService.createAuthToken(authRequest);
            JwtResponse jwtResponse = (JwtResponse) response.getBody();

            // Fetch user details including roles
            String username = authRequest.getUsername();
            UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new BadCredentialsException("User not found: " + username));

            // Add the token, username, and roles to the session
            session.setAttribute("token", jwtResponse.getToken());
            session.setAttribute("username", username);
            session.setAttribute("roles", user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()));

            return "redirect:/home"; // Redirect to home page on successful authentication
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Incorrect username or password");
            return "login";
        }
    }
}