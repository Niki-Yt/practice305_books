package store.book.thymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import store.book.auth.services.UserService;
import store.book.auth.utils.JwtTokenUtils;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/about")
public class AboutController {
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Autowired
    UserService userService;
    @GetMapping
    public String home() {
        return "about";
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, String>> getUserInfo(@RequestHeader("Authorization") String token) {
        String token1 = token.substring(7);
        String username = jwtTokenUtils.getUsername(token1);
        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        return ResponseEntity.ok(response);
    }
}
