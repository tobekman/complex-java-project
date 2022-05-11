package se.iths.complexjavaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.complexjavaproject.auth.jwt.JwtProvider;
import se.iths.complexjavaproject.auth.jwt.JwtResponse;
import se.iths.complexjavaproject.entity.User;
import se.iths.complexjavaproject.exception.EntityNotFoundException;
import se.iths.complexjavaproject.exception.UserAlreadyExistsException;
import se.iths.complexjavaproject.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    JwtProvider jwtProvider;
    UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("signup")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Optional<User> existingEmail = userService.getUserByEmail(user.getEmail());
        Optional<User> existingUsername = userService.getUserByUsername(user.getUsername());

        if(existingEmail.isPresent() || existingUsername.isPresent()) {
            throw new UserAlreadyExistsException("A user with this email and/or username already exists in database");
        }

        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
