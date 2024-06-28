package store.book.books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.book.books.dtos.BookDto;
import store.book.books.entities.BookEntity;
import store.book.books.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity saveBook(BookDto bookDto) {
        BookEntity book = convertToEntity(bookDto);
        return bookRepository.save(book);
    }

    public BookEntity findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    private BookEntity convertToEntity(BookDto bookDto) {
        BookEntity book = new BookEntity();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        return book;
    }
}
