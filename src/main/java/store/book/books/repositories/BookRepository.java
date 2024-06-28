package store.book.books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.book.books.entities.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
