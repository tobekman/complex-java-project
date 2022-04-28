package se.iths.complexjavaproject.service;

import se.iths.complexjavaproject.entity.Order;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.entity.OrderItems;
import se.iths.complexjavaproject.repository.OrderRepository;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public Optional<Order> getOrderById(Long id){return orderRepository.findById(id);}

    public void deleteOrder(Long id){Order result = orderRepository.findById(id).orElseThrow(EntityExistsException::new);
        orderRepository.deleteById(result.getId());}

    public Iterable<Order> getAllOrders(){return orderRepository.findAll();}
}
