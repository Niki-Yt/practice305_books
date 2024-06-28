package store.book.auth.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import store.book.auth.entities.RoleEntity;


import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
