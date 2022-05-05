package se.iths.complexjavaproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.entity.DTO.UserDTO;
import se.iths.complexjavaproject.entity.DTO.mapper.Mapper;
import se.iths.complexjavaproject.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.iths.complexjavaproject.exception.UserNotFoundException;
import se.iths.complexjavaproject.service.UserService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    public UserController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable Long id){
        Optional<User> foundUser = userService.getUserById(id);

        if(foundUser.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " is not found in database.");
        }

        Optional<UserDTO> userDTO = (Optional<UserDTO>) mapper.toDto(foundUser);

        return new ResponseEntity<>(userDTO, HttpStatus.FOUND);

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
    public ResponseEntity<Iterable<UserDTO>> findAllUsers() {
        Iterable<User> allUsers = userService.getAllUsers();
        List<UserDTO> usersToDTO = new ArrayList<>();

        for (User user : allUsers) {
            usersToDTO.add((UserDTO) mapper.toDto(user));
        }

        return new ResponseEntity<>(usersToDTO, HttpStatus.OK);
    }
}
