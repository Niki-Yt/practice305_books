package store.book.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.book.order.entities.OrderHistoryEntity;

import java.util.Optional;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {
    Optional<OrderHistoryEntity> findByUserId(Long userId);
}
