package store.book.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.book.books.dtos.BookDto;
import store.book.books.entities.BookEntity;
import store.book.books.repositories.BookRepository;
import store.book.books.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping
    public BookEntity saveBook(@RequestBody BookDto book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookDto book) {
        book.setId(id);
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
