package se.iths.complexjavaproject.service;


import se.iths.complexjavaproject.entity.Order;
import se.iths.complexjavaproject.entity.User;
import org.springframework.stereotype.Service;

import se.iths.complexjavaproject.repository.UserRepository;

import javax.persistence.EntityExistsException;
import java.util.Optional;

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


    public Optional<User> getOrderById(Long id){return userRepository.findById(id);}

    public void deleteOrder(Long id){User result = userRepository.findById(id).orElseThrow(EntityExistsException::new);
        userRepository.deleteById(result.getId());}

    public Iterable<User> getAllOrders(){return userRepository.findAll();}
}

