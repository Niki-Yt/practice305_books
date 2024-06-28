package store.book.order.entities;


import jakarta.persistence.*;
import store.book.auth.entities.UserEntity;

import java.util.Set;

@Entity
@Table(name = "order_histories")
public class OrderHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "orderHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderEntity> orders;

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
    public Set<OrderEntity> getOrders() {
        return orders;
    }
    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }
}