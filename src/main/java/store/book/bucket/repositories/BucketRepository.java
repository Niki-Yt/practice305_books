package store.book.bucket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.book.bucket.entities.BucketEntity;

@Repository
public interface BucketRepository extends JpaRepository<BucketEntity, Long> {
}
