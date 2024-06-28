package store.book.auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import store.book.bucket.entities.BucketEntity;
import lombok.Data;

import jakarta.persistence.*;
import store.book.order.entities.OrderHistoryEntity;

import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BucketEntity bucket;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<RoleEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderHistoryEntity> orderHistories;

    // Setters and Getters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public BucketEntity getBucket() {
        return bucket;
    }
    public void setBucket(BucketEntity bucket) {
        this.bucket = bucket;
    }
    public Collection<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }
    public Set<OrderHistoryEntity> getOrderHistories() {
        return orderHistories;
    }
    public void setOrderHistories(Set<OrderHistoryEntity> orderHistories) {
        this.orderHistories = orderHistories;
    }
}