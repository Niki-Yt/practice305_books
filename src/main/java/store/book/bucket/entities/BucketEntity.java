package store.book.bucket.entities;

import jakarta.persistence.*;
import store.book.auth.entities.UserEntity;
import store.book.books.entities.BookEntity;

import java.util.Set;

@Entity
@Table(name = "buckets")
public class BucketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany
    @JoinTable(
            name = "buckets_books",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<BookEntity> books;

    // Setters and Getters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public Set<BookEntity> getBooks() {
        return books;
    }
    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}