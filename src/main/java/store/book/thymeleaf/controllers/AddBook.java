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
import store.book.auth.dtos.RegistrationUserDto;
import store.book.auth.entities.UserEntity;
import store.book.auth.exceptions.BookError;
import store.book.auth.exceptions.PasswordsNotMatchingException;
import store.book.auth.exceptions.UserAlreadyExistsException;
import store.book.auth.repositories.UserRepository;
import store.book.auth.services.AuthService;
import store.book.books.dtos.BookDto;
import store.book.books.entities.BookEntity;
import store.book.books.repositories.BookRepository;
import store.book.books.services.BookService;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/books/add")
public class AddBook {
    @Autowired
    BookService bookService;

    @ModelAttribute("book")
    public BookDto bookDto() {
        return new BookDto();
    }

    @GetMapping
    public String home() {
        return "add-book";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") BookDto bookDto, Model model) {
        try {
            bookService.saveBook(bookDto);
            return "redirect:/admin?success";
        } catch (BookError e) {
            model.addAttribute("error", "Book error");
            return "add-book";
        }
    }
}