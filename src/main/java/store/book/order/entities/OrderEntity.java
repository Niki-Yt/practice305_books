package store.book.order.entities;

import store.book.auth.entities.UserEntity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_history_id", nullable = false)
    private OrderHistoryEntity orderHistory;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderItemEntity> orderItems;

    // Setters and Getters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public OrderHistoryEntity getOrderHistory() {
        return orderHistory;
    }
    public void setOrderHistory(OrderHistoryEntity orderHistory) {
        this.orderHistory = orderHistory;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Set<OrderItemEntity> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Set<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }
}