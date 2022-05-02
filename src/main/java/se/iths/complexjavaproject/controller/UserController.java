package se.iths.complexjavaproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.complexjavaproject.service.UserService;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id){
        Optional<User> foundUser = userService.getUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.FOUND);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) throws FileNotFoundException {
        User foundUser = userService.getUserById(id).orElseThrow(FileNotFoundException::new);
        userService.deleteUser(foundUser.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<Iterable<User>> findAllUsers() {
        Iterable<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
