package store.book.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.book.auth.dtos.RegistrationUserDto;
import store.book.auth.entities.UserEntity;
import store.book.auth.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //отримання користувачів
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<UserEntity> findById(Long id) {return userRepository.findById(id);}
    public ArrayList<UserEntity> getAllUsers() {
        ArrayList user = new ArrayList<>();
        userRepository.findAll().forEach(user::add);
        return user;
    }
    //реалізація повернення по username для spring boot
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Користувач '%s' не знайдений", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
    //створення нового користувача
    public UserEntity createNewUser(RegistrationUserDto registrationUserDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationUserDto.getUsername());
        user.setEmail(registrationUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(user);
    }

}