package store.book.thymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.book.auth.exceptions.BookError;
import store.book.books.dtos.BookDto;
import store.book.books.entities.BookEntity;
import store.book.books.services.BookService;

@Controller
@RequestMapping("/admin/books/update")
public class UpdateBook {
    @Autowired
    BookService bookService;

    @ModelAttribute("book")
    public BookDto bookDto() {
        return new BookDto();
    }

    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        BookEntity book = bookService.findBookById(id);
        if (book != null) {
            model.addAttribute("book", convertToDto(book));
            return "update-book";
        }
        return "redirect:/admin?error";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") BookDto bookDto, Model model) {
        try {
            bookDto.setId(id); // Встановлюємо ID книги для оновлення
            bookService.saveBook(bookDto);
            return "redirect:/admin?success";
        } catch (Exception e) {
            model.addAttribute("error", "Book error");
            return "update-book";
        }
    }
    private BookDto convertToDto(BookEntity book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        return dto;
    }
}