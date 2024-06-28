package store.book.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import store.book.auth.dtos.JwtRequest;
import store.book.auth.dtos.JwtResponse;
import store.book.auth.dtos.RegistrationUserDto;
import store.book.auth.dtos.UserDto;
import store.book.auth.entities.UserEntity;
import store.book.auth.exceptions.PasswordsNotMatchingException;
import store.book.auth.exceptions.UserAlreadyExistsException;
import store.book.auth.exceptions.UserNotExistsException;
import store.book.auth.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    //створення bearer token-у
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                            authRequest.getPassword()));
        } catch (UserNotExistsException e) {
            throw new UserNotExistsException("Incorrect username or password");
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    //створення нового користувача
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new PasswordsNotMatchingException("Passwords do not match.");
        }
        if (userService.findByEmail(registrationUserDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists.");
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with this username already exists.");
        }
        UserEntity user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }
}