package se.iths.complexjavaproject.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import se.iths.complexjavaproject.entity.Role;
import se.iths.complexjavaproject.entity.User;
import org.springframework.stereotype.Service;

import se.iths.complexjavaproject.repository.RoleRepository;
import se.iths.complexjavaproject.repository.UserRepository;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roleToAdd = roleRepository.findByName("ROLE_USER");
        user.addRole(roleToAdd);
        return userRepository.save(user);
    }


    public Optional<User> getUserById(Long id){return userRepository.findById(id);}

    public void deleteUser(Long id){User result = userRepository.findById(id).orElseThrow(EntityExistsException::new);
        userRepository.deleteById(result.getId());}

    public Iterable<User> getAllUsers(){return userRepository.findAll();}

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}

