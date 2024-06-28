package store.book.thymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.book.auth.services.UserService;
import store.book.auth.utils.JwtTokenUtils;
import store.book.books.entities.BookEntity;
import store.book.books.services.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String home(Model model) {
        List<BookEntity> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "catalog";
    }
}
