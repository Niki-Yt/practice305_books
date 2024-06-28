package store.book.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.book.auth.entities.RoleEntity;
import store.book.auth.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleEntity getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
