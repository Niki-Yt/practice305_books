package store.book.thymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.book.auth.services.UserService;
import store.book.auth.utils.JwtTokenUtils;
import store.book.books.entities.BookEntity;
import store.book.books.services.BookService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String home() {
        return "admin";
    }

}
