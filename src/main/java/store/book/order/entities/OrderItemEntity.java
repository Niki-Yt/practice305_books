package store.book.order.entities;

import store.book.books.entities.BookEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    // Setters and Getters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public OrderEntity getOrder() {
        return order;
    }
    public void setOrder(OrderEntity order) {
        this.order = order;
    }
    public BookEntity getBook() {
        return book;
    }
    public void setBook(BookEntity book) {
        this.book = book;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
