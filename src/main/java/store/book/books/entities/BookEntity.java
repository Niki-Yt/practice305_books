package store.book.books.entities;

import jakarta.persistence.*;
import store.book.bucket.entities.BucketEntity;
import store.book.order.entities.OrderItemEntity;

import java.util.Set;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private Double price;

    @ManyToMany(mappedBy = "books")
    private Set<BucketEntity> buckets;

    @OneToMany(mappedBy = "book")
    private Set<OrderItemEntity> orderItems;

    // Setters and Getters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Set<BucketEntity> getBuckets() {
        return buckets;
    }
    public void setBuckets(Set<BucketEntity> buckets) {
        this.buckets = buckets;
    }
    public Set<OrderItemEntity> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Set<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }
}