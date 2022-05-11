package se.iths.complexjavaproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.DTO.UserDTO;
import se.iths.complexjavaproject.entity.DTO.mapper.Mapper;
import se.iths.complexjavaproject.entity.Order;
import se.iths.complexjavaproject.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.complexjavaproject.exception.EntityNotFoundException;
import se.iths.complexjavaproject.service.UserService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    public UserController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        Optional<User> foundUser = userService.getUserById(id);
        UserDTO userDTO;
        if(foundUser.isPresent()) {
            User newUser = foundUser.get();
            userDTO = (UserDTO) mapper.toDto(newUser);
        } else {
            throw new EntityNotFoundException("User with id: " + id + " is not found in database.");
        }

        return new ResponseEntity<>(userDTO, HttpStatus.FOUND);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) throws FileNotFoundException {
        User foundUser = userService.getUserById(id).orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " is not found in database."));
        userService.deleteUser(foundUser.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Iterable<UserDTO>> findAllUsers() {
        Iterable<User> allUsers = userService.getAllUsers();
        List<UserDTO> usersToDTO = new ArrayList<>();

        for (User user : allUsers) {
            usersToDTO.add((UserDTO) mapper.toDto(user));
        }

        return new ResponseEntity<>(usersToDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user){
        if(userService.getUserById(user.getId()).isEmpty()){
            throw new EntityNotFoundException("User with id does not exist - please create new user");
        }
        else{
            userService.createUser(user);
            UserDTO userDTO = (UserDTO) mapper.toDto(user);
            return new ResponseEntity<>(userDTO,HttpStatus.OK);
        }

    }
}
