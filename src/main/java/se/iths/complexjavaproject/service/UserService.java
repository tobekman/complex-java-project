package se.iths.complexjavaproject.service;


import se.iths.complexjavaproject.entity.User;
import org.springframework.stereotype.Service;

import se.iths.complexjavaproject.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

}