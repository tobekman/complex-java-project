package se.iths.complexjavaproject.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.Address;
import se.iths.complexjavaproject.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.complexjavaproject.service.UserService;

import java.io.FileNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getAddressById(@PathVariable Long id){
        Optional<User> foundUser = userService.getUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.FOUND);

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id) throws FileNotFoundException {
        User foundUser = userService.getUserById(id).orElseThrow(FileNotFoundException::new);
        userService.deleteUser(foundUser.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> findAllUsers() {
        Iterable<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
